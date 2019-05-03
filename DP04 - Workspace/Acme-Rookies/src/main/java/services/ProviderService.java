
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProviderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Box;
import domain.Customisation;
import domain.Provider;
import domain.SocialProfile;
import forms.ProviderForm;

@Service
@Transactional
public class ProviderService {

	@Autowired
	public ProviderRepository	providerRepository;

	@Autowired
	public ActorService			actorService;

	@Autowired
	public CustomisationService	customisationService;

	@Autowired
	public SocialProfileService	socialprofileService;


	//Constructor
	public ProviderService() {
		super();
	}

	public Provider create() {

		Provider result;
		result = new Provider();

		final Collection<Box> predefined = new ArrayList<Box>();
		result.setComputed(false);

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.PROVIDER);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);
		result.getUserAccount().setStatus(true);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Provider

		return result;
	}

	public Provider findByPrincipal() {
		Provider res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Provider findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.providerRepository.findOne(a.getId());
	}

	public Provider findOne(final Provider Provider) {
		return this.providerRepository.findOne(Provider.getId());
	}

	public Collection<Provider> findAll() {
		return this.providerRepository.findAll();
	}

	public Provider save(final Provider Provider) {
		Assert.notNull(Provider);
		Assert.isTrue(!Provider.getIsBanned());

		final String pnumber = Provider.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			Provider.setPhoneNumber(cc.concat(pnumber));

		if (Provider.getId() != 0) {
			Assert.isTrue(this.actorService.checkProvider());

			// Modified Provider must be logged Provider
			final Provider logProvider;
			logProvider = this.findByPrincipal();
			Assert.notNull(logProvider);
			Assert.notNull(logProvider.getId());

		} else {

			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			Provider.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = Provider.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = Provider.getUserAccount();
			cuenta.setPassword(hash);
			Provider.setUserAccount(cuenta);
		}
		// Restrictions
		Provider res;

		res = this.providerRepository.save(Provider);
		return res;
	}

	public Provider findByUserAccount(final UserAccount userAccount) {
		Provider res;
		Assert.notNull(userAccount);

		res = this.providerRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Provider saveForTest(final Provider bro) {

		// Restrictions
		Assert.isTrue(bro.getIsBanned() != true);

		if (bro.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = bro.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = bro.getUserAccount();
			cuenta.setPassword(hash);
			bro.setUserAccount(cuenta);

		}
		if (bro.getId() == 0) {

		}

		return this.providerRepository.save(bro);
	}
	public Provider findOne(final int ProviderId) {
		Provider c;

		Assert.notNull(ProviderId);
		Assert.isTrue(ProviderId != 0);
		c = this.providerRepository.findOne(ProviderId);

		Assert.notNull(c);
		return c;
	}

	public Provider reconstruct(final ProviderForm providerForm, final BindingResult binding) {
		final Provider provider = this.create();

		//Assert.isTrue(ProviderForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.PROVIDER);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = providerForm.getUserAccount();
		ua.setAuthorities(aus);
		ua.setStatus(true);

		provider.setAddress(providerForm.getAddress());
		provider.setEmail(providerForm.getEmail());
		provider.setName(providerForm.getName());
		provider.setPhoneNumber(providerForm.getPhoneNumber());
		provider.setPhoto(providerForm.getPhoto());
		provider.setSurname(providerForm.getSurname());
		provider.setUserAccount(ua);
		provider.setVat(providerForm.getVat());
		provider.setHolderName(providerForm.getHolderName());
		provider.setMakeName(providerForm.getMakeName());
		provider.setNumber(providerForm.getNumber());
		provider.setExpirationMonth(providerForm.getExpirationMonth());
		provider.setExpirationYear(providerForm.getExpirationYear());
		provider.setCvv(providerForm.getCvv());

		provider.setIsBanned(false);

		this.validator.validate(provider, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return provider;
	}


	@Autowired
	private Validator	validator;


	public Provider reconstruct(final Provider provider, final BindingResult binding) {
		Provider res;

		//Check authority
		final Authority a = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (provider.getId() == 0)
			res = provider;
		else
			res = this.providerRepository.findOne(provider.getId());
		res.setName(provider.getName());
		res.setEmail(provider.getEmail());
		res.setSurname(provider.getSurname());
		res.setAddress(provider.getAddress());
		res.setPhoneNumber(provider.getPhoneNumber());
		res.setPhoto(provider.getPhoto());
		res.setVat(provider.getVat());

		res.setHolderName(provider.getHolderName());
		res.setMakeName(provider.getMakeName());
		res.setNumber(provider.getNumber());
		res.setExpirationMonth(provider.getExpirationMonth());
		res.setExpirationYear(provider.getExpirationYear());
		res.setCvv(provider.getCvv());
		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void leave() {
		final Provider logProvider = this.findByPrincipal();

		logProvider.setAddress("Unknown");
		logProvider.setIsBanned(true);
		logProvider.setEmail("unknown@unknown.com");
		logProvider.setName("Unknown");
		logProvider.setPhoneNumber("Unknown");
		logProvider.setPhoto("http://www.unknown.com");
		logProvider.setSocialProfiles(null);
		logProvider.setSurname("Unknown");

		logProvider.setHolderName("Unknown");
		logProvider.setMakeName("Unknown");
		logProvider.setCvv(123);
		logProvider.setExpirationMonth(1);
		logProvider.setExpirationYear(9999);
		logProvider.setNumber("4532134223318979");

		final UserAccount ua = logProvider.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		if (logProvider.getSocialProfiles() != null)
			for (final SocialProfile sp : logProvider.getSocialProfiles())
				this.socialprofileService.deleteLeave(sp);
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logProvider.setUserAccount(ua);
	}

	public void flush() {
		this.providerRepository.flush();
	}
}
