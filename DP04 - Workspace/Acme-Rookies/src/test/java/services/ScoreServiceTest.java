
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ScoreServiceTest extends AbstractTest {

	//SUT
	@Autowired
	AdministratorService	administratorService;

	@Autowired
	CompanyService			companyService;


	/**
	 * TESTING REQUIREMENT #4.3
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN AdministratorService: 66.8%
	 * COVERED DATA IN THIS TEST: 12%
	 * */

	@Test
	public void executeScoreProcess() {

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #4.3
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"admin", null
			},

			/**
			 * TESTING REQUIREMENT #4.3
			 * NEGATIVE TEST (YOU CAN NOT EXECUTE IT IF YOU ARE NOT AN ADMIN)
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{

				"hacker1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void template2(final String username, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.administratorService.computeCompanyScore();
			Assert.isTrue(this.companyService.findOne(this.getEntityId("company1")).getScore() == 0.01);
			Assert.isTrue(this.companyService.findOne(this.getEntityId("company2")).getScore() == 0.0);
			this.administratorService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
