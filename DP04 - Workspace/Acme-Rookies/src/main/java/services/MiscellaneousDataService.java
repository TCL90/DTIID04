
package services;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MiscellaneousDataRepository;
import security.Authority;
import domain.Curricula;
import domain.Rookie;
import domain.MiscellaneousData;

@Service
@Transactional
public class MiscellaneousDataService {

	@Autowired
	private Validator					validator;

	@Autowired
	private MiscellaneousDataRepository	miscellaneousDataRepository;

	@Autowired
	private RookieService				rookieService;

	@Autowired
	private CurriculaService			curriculaService;


	public List<MiscellaneousData> findByCurriculaId(final int curriculaId) {
		this.checkConditions();
		final List<Curricula> lc = this.curriculaService.getCurriculasFromRookie();
		final List<MiscellaneousData> res = this.miscellaneousDataRepository.findMiscellaneousDataByCurriculaId(curriculaId);
		for (final MiscellaneousData ed : res)
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

	public MiscellaneousData create(final int curriculaId) {
		final Curricula c = this.curriculaService.findOne(curriculaId);
		Assert.isTrue(this.curriculaService.getCurriculasFromRookie().contains(c));
		Assert.isTrue(this.curriculaService.findOne(curriculaId).getIsCopy() == false);

		final MiscellaneousData res = new MiscellaneousData();
		res.setCurricula(c);
		res.setId(0);
		return res;
	}

	public MiscellaneousData findOne(final int id) {
		this.checkConditions();
		final Rookie h = this.rookieService.findOnePrincipal();
		final MiscellaneousData m = this.miscellaneousDataRepository.findOne(id);
		Assert.isTrue(h == m.getCurricula().getRookie());
		return m;
	}

	public void save(final MiscellaneousData e) {
		this.checkConditions();
		Assert.isTrue(e.getCurricula().getRookie().getId() == this.rookieService.findOnePrincipal().getId());

		this.miscellaneousDataRepository.save(e);
	}

	public void delete(final MiscellaneousData p) {
		this.checkConditions();
		Assert.isTrue(p.getCurricula().getIsCopy() == false);
		Assert.isTrue(this.miscellaneousDataRepository.findOne(p.getId()).getCurricula().getRookie() == this.rookieService.findOnePrincipal());

		this.miscellaneousDataRepository.delete(p);
	}

	public MiscellaneousData reconstruct(final MiscellaneousData pd, final BindingResult binding) {
		MiscellaneousData res;
		if (pd.getId() == 0)
			res = pd;
		else {
			res = this.findOne(pd.getId());
			res.setAttachments(pd.getAttachments());
			res.setFreeText(pd.getFreeText());
		}
		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void flush() {
		this.miscellaneousDataRepository.flush();

	}

}
