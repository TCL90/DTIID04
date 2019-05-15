
package services;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PersonalDataRepository;
import security.Authority;
import domain.Curricula;
import domain.Rookie;
import domain.PersonalData;

@Service
@Transactional
public class PersonalDataService {

	@Autowired
	private Validator				validator;

	@Autowired
	private PersonalDataRepository	personalDataRepository;

	@Autowired
	private RookieService			rookieService;

	@Autowired
	private CurriculaService		curriculaService;


	public List<PersonalData> findByCurriculaId(final int curriculaId) {
		this.checkConditions();

		final List<Curricula> lc = this.curriculaService.getCurriculasFromRookie();
		final List<PersonalData> res = this.personalDataRepository.findPersonalDataByCurriculaId(curriculaId);
		for (final PersonalData ed : res)
			Assert.isTrue(lc.contains(ed.getCurricula()));
		return res;
	}

	private void checkConditions() {
		final Rookie h = this.rookieService.findOnePrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.ROOKIE);
		Assert.isTrue(h.getUserAccount().getAuthorities().contains(a));
		Assert.isTrue(!h.getIsBanned());
	}

	public PersonalData create(final int curriculaId) {
		final Curricula c = this.curriculaService.findOne(curriculaId);
		Assert.isTrue(this.curriculaService.getCurriculasFromRookie().contains(c));
		Assert.isTrue(this.curriculaService.findOne(curriculaId).getIsCopy() == false);

		final PersonalData res = new PersonalData();
		res.setCurricula(c);
		res.setId(0);
		return res;
	}

	public PersonalData findOne(final int id) {
		this.checkConditions();
		final Rookie h = this.rookieService.findOnePrincipal();
		final PersonalData p = this.personalDataRepository.findOne(id);
		Assert.isTrue(h == p.getCurricula().getRookie());
		return p;
	}

	public void save(final PersonalData e) {
		this.checkConditions();
		Assert.isTrue(e.getCurricula().getRookie().getId() == this.rookieService.findOnePrincipal().getId());

		this.personalDataRepository.save(e);
	}

	public void delete(final PersonalData p) {
		this.checkConditions();
		Assert.isTrue(!p.getCurricula().getIsCopy());
		Assert.isTrue(this.personalDataRepository.findOne(p.getId()).getCurricula().getRookie() == this.rookieService.findOnePrincipal());

		this.personalDataRepository.delete(p);
	}

	public PersonalData reconstruct(final PersonalData pd, final BindingResult binding) {
		PersonalData res;
		if (pd.getId() == 0)
			res = pd;
		else {
			res = this.findOne(pd.getId());
			res.setFullName(pd.getFullName());
			res.setGitProfile(pd.getGitProfile());
			res.setLinkedInProfile(pd.getLinkedInProfile());
			res.setPhoneNumber(pd.getPhoneNumber());
			res.setStatement(pd.getStatement());

		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void flush() {
		this.personalDataRepository.flush();

	}

}
