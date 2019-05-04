
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AuditServiceTest extends AbstractTest {

	//SUT
	@Autowired
	AuditService	auditService;

	@Autowired
	PositionService	positionService;


	/**
	 * TESTING REQUIREMENT #3.1 and #3.2 (Manage Audit:CREATE)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void createAudit() {
		this.authenticate("auditor1");
		final Audit au = this.auditService.create(this.getEntityId("position1"));
		au.setFinalMode(false);
		au.setScore(9.0);
		au.setText("Creo que deberían contar con un administrador de seguridad más");

		final Audit au2 = this.auditService.create((this.getEntityId("position2")));
		au2.setFinalMode(false);

		au2.setScore(9.0);
		au2.setText("");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #3.1 and #3.2
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"auditor1", au, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Audit) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	/**
	 * TESTING REQUIREMENT #3.2 (Manage Audit:EDIT)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void editAudit() {
		this.authenticate("auditor1");

		final Audit aue1 = this.auditService.findOne(this.getEntityId("audit2"));
		aue1.setText("Cambio");

		final Audit aue2 = this.auditService.findOne(this.getEntityId("audit2"));
		aue2.setFinalMode(false);
		aue2.setText("Cambio");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #15
			 * NEGATIVE TEST: YOU CANNOT EDIT AN Audit In final mode
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"auditor1", aue1, IllegalArgumentException.class
			}
		/**
		 * TESTING REQUIREMENT #3.2
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS: 100%
		 * COVERED DATA: 10%
		 * */

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Audit) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	/**
	 * TESTING REQUIREMENT #3.2 (Manage Audit: DELETE)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AuditService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	//	@Test
	//	public void deleteAudit() {
	//		this.authenticate("auditor1");
	//		final Audit a1 = this.auditService.findOne(this.getEntityId("audit2"));
	//
	//		final Audit a2 = this.auditService.findOne(this.getEntityId("audit2"));
	//		a2.setFinalMode(false);
	//
	//		this.unauthenticate();
	//
	//		final Object testingData[][] = {
	//
	//			/**
	//			 * TESTING REQUIREMENT #15
	//			 * NEGATIVE TEST: YOU CANNOT DELETE AN AUDIT WHICH IS IN FINAL MODE
	//			 * (Expected IllegalArgumentException)
	//			 * COVERED INSTRUCTIONS: 100%
	//			 * COVERED DATA: 10%
	//			 * */
	//			{
	//				"hacker1", a1, IllegalArgumentException.class
	//			},
	//
	//			/**
	//			 * TESTING REQUIREMENT #3.2
	//			 * POSITIVE TEST
	//			 * COVERED INSTRUCTIONS: 100%
	//			 * COVERED DATA: 10%
	//			 * */
	//			{
	//				"hacker1", a2, null
	//			}
	//
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.templateD((String) testingData[i][0], (Audit) testingData[i][1], (Class<?>) testingData[i][2]);
	//	}
	protected void template2(final String username, final Audit au, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.auditService.save(au);
			this.auditService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	protected void templateD(final String username, final Audit au, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.auditService.delete(au);
			this.auditService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
