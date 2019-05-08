
package services;

import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditRepository;
import domain.Audit;

@Service
@Transactional
public class AuditService {

	@Autowired
	PositionService	positionService;

	@Autowired
	AuditorService	auditorService;

	@Autowired
	ActorService	actorService;

	@Autowired
	AuditRepository	auditRepository;

	@Autowired
	Validator		validator;


	public List<Audit> findAllByAuditor(final Integer auditorId) {
		Assert.isTrue(this.actorService.checkAuditor());
		return this.auditRepository.findByAuditorId(auditorId);
	}

	public Audit findOne(final int auditId) {
		Assert.isTrue(this.actorService.checkAuditor());
		final Audit a = this.auditRepository.findOne(auditId);
		Assert.isTrue(this.findAllByAuditor(this.auditorService.findByPrincipal().getId()).contains(a));

		return a;
	}

	public Audit create(final Integer positionId) {
		final Audit res = new Audit();

		res.setPosition(this.positionService.findOne(positionId));
		res.setId(0);
		res.setFinalMode(false);
		res.setMoment(Calendar.getInstance().getTime());

		return res;
	}

	public Audit reconstruct(final Audit a, final BindingResult binding) {
		Audit res;
		if (a.getId() == 0) {
			res = a;
			res.setAuditor(this.auditorService.findByPrincipal());
		} else {
			res = this.findOne(a.getId());
			res.setFinalMode(a.getFinalMode());
			res.setScore(a.getScore());
			res.setText(a.getText());
		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void flush() {
		this.auditRepository.flush();
	}

	public void save(final Audit a) {
		Assert.isTrue(this.actorService.checkAuditor());
		if (a.getId() != 0)
			Assert.isTrue(this.findOne(a.getId()).getAuditor() == this.auditorService.findByPrincipal());
		Assert.isTrue(a.getAuditor() == this.auditorService.findByPrincipal());

		this.auditRepository.save(a);
	}

	public void delete(final Audit a1) {
		Assert.isTrue(this.actorService.checkAuditor());
		Assert.isTrue(a1.getFinalMode() == false);
		Assert.isTrue(this.findAllByAuditor(this.auditorService.findByPrincipal().getId()).contains(a1));

		this.auditRepository.delete(a1);
	}

	public Integer numOfAudits(final int id) {
		return this.auditRepository.numOfAudits(id);
	}
}
