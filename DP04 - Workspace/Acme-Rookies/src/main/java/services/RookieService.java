
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FinderRepository;
import repositories.RookieRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Box;
import domain.Customisation;
import domain.Finder;
import domain.Rookie;
import domain.SocialProfile;
import forms.RookieForm;

@Service
@Transactional
public class RookieService {

	@Autowired
	public RookieRepository		rookieRepository;

	@Autowired
	public ActorService			actorService;

	@Autowired
	public CustomisationService	customisationService;

	@Autowired
	public FinderService		finderService;

	@Autowired
	public FinderRepository		finderRepository;

	@Autowired
	public SocialProfileService	socialprofileService;


	//Constructor
	public RookieService() {
		super();
	}

	public Rookie create() {

		Rookie result;
		result = new Rookie();
		result.setComputed(false);

		final Collection<Box> predefined = new ArrayList<Box>();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.COMPANY);
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

		// Rookie
		final Finder fi = new Finder();
		result.setFinder(fi);

		return result;
	}

	public Rookie findByPrincipal() {
		Rookie res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Rookie findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.rookieRepository.findOne(a.getId());
	}

	public Rookie findOne(final Rookie Rookie) {
		return this.rookieRepository.findOne(Rookie.getId());
	}

	public Collection<Rookie> findAll() {
		return this.rookieRepository.findAll();
	}

	public Rookie save(final Rookie rookie) {
		Assert.notNull(rookie);
		Assert.isTrue(!rookie.getIsBanned());

		final String pnumber = rookie.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			rookie.setPhoneNumber(cc.concat(pnumber));

		if (rookie.getId() != 0) {
			Assert.isTrue(this.actorService.checkRookie());

			// Modified Rookie must be logged Rookie
			final Rookie logRookie;
			logRookie = this.findByPrincipal();
			Assert.notNull(logRookie);
			Assert.notNull(logRookie.getId());

		} else {

			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			rookie.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = rookie.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = rookie.getUserAccount();
			cuenta.setPassword(hash);
			rookie.setUserAccount(cuenta);

			final Finder find = new Finder();

			find.setMoment(new Date());
			final Finder find2 = this.finderRepository.save(find);

			rookie.setFinder(find2);
		}
		// Restrictions
		Rookie res;

		res = this.rookieRepository.save(rookie);
		return res;
	}

	public Rookie findByUserAccount(final UserAccount userAccount) {
		Rookie res;
		Assert.notNull(userAccount);

		res = this.rookieRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Rookie saveForTest(final Rookie bro) {

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

		return this.rookieRepository.save(bro);
	}
	public Rookie findOne(final int RookieId) {
		Rookie c;

		Assert.notNull(RookieId);
		Assert.isTrue(RookieId != 0);
		c = this.rookieRepository.findOne(RookieId);

		Assert.notNull(c);
		return c;
	}

	public Rookie reconstruct(final RookieForm rookieForm, final BindingResult binding) {
		final Rookie rookie = this.create();

		//Assert.isTrue(RookieForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.ROOKIE);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = rookieForm.getUserAccount();
		ua.setAuthorities(aus);
		ua.setStatus(true);

		rookie.setAddress(rookieForm.getAddress());
		rookie.setEmail(rookieForm.getEmail());
		rookie.setName(rookieForm.getName());
		rookie.setPhoneNumber(rookieForm.getPhoneNumber());
		rookie.setPhoto(rookieForm.getPhoto());
		rookie.setSurname(rookieForm.getSurname());
		rookie.setUserAccount(ua);
		rookie.setVat(rookieForm.getVat());

		rookie.setHolderName(rookieForm.getHolderName());
		rookie.setMakeName(rookieForm.getMakeName());
		rookie.setNumber(rookieForm.getNumber());
		rookie.setExpirationMonth(rookieForm.getExpirationMonth());
		rookie.setExpirationYear(rookieForm.getExpirationYear());
		rookie.setCvv(rookieForm.getCvv());

		final Finder find = new Finder();
		final Finder find2 = new Finder();
		find.setMoment(new Date());
		find2.setMoment(new Date());

		final Finder find3 = this.finderService.save(find);
		final Finder find4 = this.finderService.save(find2);

		rookie.setFinder(find3);
		rookieForm.setFinder(find4);

		rookie.setIsBanned(false);

		this.validator.validate(rookie, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return rookie;
	}


	@Autowired
	private Validator	validator;


	public Rookie reconstruct(final Rookie rookie, final BindingResult binding) {
		Rookie res;

		//Check authority
		final Authority a = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.ROOKIE);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (rookie.getId() == 0)
			res = rookie;
		else
			res = this.rookieRepository.findOne(rookie.getId());
		res.setName(rookie.getName());
		res.setEmail(rookie.getEmail());
		res.setSurname(rookie.getSurname());
		res.setAddress(rookie.getAddress());
		res.setPhoneNumber(rookie.getPhoneNumber());
		res.setPhoto(rookie.getPhoto());
		res.setVat(rookie.getVat());

		res.setHolderName(rookie.getHolderName());
		res.setMakeName(rookie.getMakeName());
		res.setNumber(rookie.getNumber());
		res.setExpirationMonth(rookie.getExpirationMonth());
		res.setExpirationYear(rookie.getExpirationYear());
		res.setCvv(rookie.getCvv());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Rookie getRookieByUserAccount(final int id) {
		return this.rookieRepository.findByUserAccountId(id);
	}

	public void leave() {
		final Rookie logRookie = this.findByPrincipal();

		logRookie.setAddress("Unknown");
		logRookie.setIsBanned(true);
		logRookie.setEmail("unknown@unknown.com");
		logRookie.setName("Unknown");
		logRookie.setPhoneNumber("Unknown");
		logRookie.setPhoto("http://www.unknown.com");

		logRookie.setSocialProfiles(null);
		logRookie.setSurname("Unknown");

		//		final Finder f = new Finder();
		//		final Finder find = this.finderService.save(f);
		//		logRookie.setFinder(find);

		logRookie.setHolderName("Unknown");
		logRookie.setMakeName("Unknown");
		logRookie.setCvv(123);
		logRookie.setExpirationMonth(1);
		logRookie.setExpirationYear(9999);
		logRookie.setNumber("4532134223318979");

		this.rookieRepository.flush();

		final UserAccount ua = logRookie.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		if (logRookie.getSocialProfiles() != null)
			for (final SocialProfile sp : logRookie.getSocialProfiles())
				this.socialprofileService.deleteLeave(sp);

		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logRookie.setUserAccount(ua);
	}

	public void flush() {
		this.rookieRepository.flush();
	}

}
