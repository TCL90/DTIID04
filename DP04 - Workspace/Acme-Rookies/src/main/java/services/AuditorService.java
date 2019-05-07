
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

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Auditor;
import domain.Box;
import domain.Customisation;
import domain.Message;
import domain.SocialProfile;
import forms.AuditorForm;

@Service
@Transactional
public class AuditorService {

	@Autowired
	public AuditorRepository	auditorRepository;

	@Autowired
	public ActorService			actorService;

	@Autowired
	public CustomisationService	customisationService;

	@Autowired
	public SocialProfileService	socialprofileService;

	@Autowired
	private MessageService		messageService;


	//Constructor
	public AuditorService() {
		super();
	}

	public Auditor create() {

		Auditor result;
		result = new Auditor();
		result.setComputed(false);

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.AUDITOR);
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

		// Auditor

		return result;
	}

	public Auditor findByPrincipal() {
		Auditor res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Auditor findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.auditorRepository.findOne(a.getId());
	}

	public Auditor findOne(final Auditor Auditor) {
		Auditor p;
		try {
			p = this.auditorRepository.findOne(Auditor.getId());
		} catch (final Exception e) {
			return null;
		}
		return p;
	}

	public Collection<Auditor> findAll() {
		return this.auditorRepository.findAll();
	}

	public Auditor save(final Auditor Auditor) {
		Assert.notNull(Auditor);
		Assert.isTrue(!Auditor.getIsBanned());

		final String pnumber = Auditor.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			Auditor.setPhoneNumber(cc.concat(pnumber));

		if (Auditor.getId() != 0) {
			Assert.isTrue(this.actorService.checkAuditor());

			// Modified Auditor must be logged Auditor
			final Auditor logAuditor;
			logAuditor = this.findByPrincipal();
			Assert.notNull(logAuditor);
			Assert.notNull(logAuditor.getId());

		} else {

			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			Auditor.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = Auditor.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = Auditor.getUserAccount();
			cuenta.setPassword(hash);
			Auditor.setUserAccount(cuenta);
		}
		// Restrictions
		Auditor res;

		res = this.auditorRepository.save(Auditor);
		return res;
	}

	public Auditor findByUserAccount(final UserAccount userAccount) {
		Auditor res;
		Assert.notNull(userAccount);

		res = this.auditorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Auditor findOne(final int AuditorId) {
		Auditor c;

		Assert.notNull(AuditorId);
		Assert.isTrue(AuditorId != 0);
		c = this.auditorRepository.findOne(AuditorId);

		return c;
	}

	public Auditor reconstruct(final AuditorForm auditorForm, final BindingResult binding) {
		final Auditor auditor = this.create();

		//Assert.isTrue(AuditorForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.AUDITOR);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = auditorForm.getUserAccount();
		ua.setAuthorities(aus);
		ua.setStatus(true);

		auditor.setAddress(auditorForm.getAddress());
		auditor.setEmail(auditorForm.getEmail());
		auditor.setName(auditorForm.getName());
		auditor.setPhoneNumber(auditorForm.getPhoneNumber());
		auditor.setPhoto(auditorForm.getPhoto());
		auditor.setSurname(auditorForm.getSurname());
		auditor.setUserAccount(ua);
		auditor.setVat(auditorForm.getVat());

		auditor.setHolderName(auditorForm.getHolderName());
		auditor.setMakeName(auditorForm.getMakeName());
		auditor.setNumber(auditorForm.getNumber());
		auditor.setExpirationMonth(auditorForm.getExpirationMonth());
		auditor.setExpirationYear(auditorForm.getExpirationYear());
		auditor.setCvv(auditorForm.getCvv());

		auditor.setIsBanned(false);

		this.validator.validate(auditor, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return auditor;
	}


	@Autowired
	private Validator	validator;


	public Auditor reconstruct(final Auditor auditor, final BindingResult binding) {
		Auditor res;

		//Check authority
		final Authority a = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.AUDITOR);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (auditor.getId() == 0)
			res = auditor;
		else
			res = this.auditorRepository.findOne(auditor.getId());
		res.setName(auditor.getName());
		res.setEmail(auditor.getEmail());
		res.setSurname(auditor.getSurname());
		res.setAddress(auditor.getAddress());
		res.setPhoneNumber(auditor.getPhoneNumber());
		res.setPhoto(auditor.getPhoto());

		res.setHolderName(auditor.getHolderName());
		res.setMakeName(auditor.getMakeName());
		res.setNumber(auditor.getNumber());
		res.setExpirationMonth(auditor.getExpirationMonth());
		res.setExpirationYear(auditor.getExpirationYear());
		res.setCvv(auditor.getCvv());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void leave() {
		final Auditor logAuditor = this.findByPrincipal();

		logAuditor.setAddress("Unknown");
		logAuditor.setIsBanned(true);
		logAuditor.setEmail("unknown@unknown.com");
		logAuditor.setName("Unknown");
		logAuditor.setPhoneNumber("Unknown");
		logAuditor.setPhoto("http://www.unknown.com");

		logAuditor.setSocialProfiles(null);
		logAuditor.setSurname("Unknown");

		logAuditor.setHolderName("Unknown");
		logAuditor.setMakeName("Unknown");
		logAuditor.setCvv(123);
		logAuditor.setExpirationMonth(1);
		logAuditor.setExpirationYear(9999);
		logAuditor.setNumber("4532134223318979");

		final UserAccount ua = logAuditor.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		if (logAuditor.getSocialProfiles() != null)
			for (final SocialProfile sp : logAuditor.getSocialProfiles())
				this.socialprofileService.deleteLeave(sp);
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logAuditor.setUserAccount(ua);
	}

	public void flush() {
		this.auditorRepository.flush();
	}

	public void flagSpamProccess() {
		Assert.isTrue(this.actorService.checkAuditor());
		final List<Actor> a1 = new ArrayList<Actor>(this.actorService.findAll());
		for (final Actor a : a1) {
			final List<Box> b1 = new ArrayList<Box>(a.getBoxes());
			if (!b1.isEmpty()) {
				final Box box = b1.get(0);
				for (final Message m : box.getMessages()) {
					final List<Customisation> lc = new ArrayList<Customisation>(this.customisationService.findAll());
					for (final String word : lc.get(0).getSpamWords())
						if ((m.getSubject().toUpperCase() + " " + m.getBody().toUpperCase() + " " + m.getTag().toUpperCase()).contains(word)) {
							m.setFlagSpam(true);
							this.messageService.save(m);
							break;
						}
				}
			}
		}
		this.flagSpamActorProcess();
	}
	public void flagSpamActorProcess() {
		Assert.isTrue(this.actorService.checkAuditor());
		final List<Actor> a1 = new ArrayList<Actor>(this.actorService.findAll());
		for (final Actor a : a1) {
			a.setComputed(true);
			Double d1 = 0.0;
			if (this.actorService.flagSpamMessagesCount(a.getId()) != null)
				d1 = this.actorService.flagSpamMessagesCount(a.getId());

			if (d1 >= 0.1) {
				a.setFlagSpam(true);
				this.actorService.save(a);
			}
		}
	}

}
