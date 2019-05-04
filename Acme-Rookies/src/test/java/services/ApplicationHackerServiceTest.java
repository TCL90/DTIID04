
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Application;
import domain.Hacker;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationHackerServiceTest extends AbstractTest {

	@Autowired
	private HackerService		hs;

	@Autowired
	private ApplicationService	as;

	@Autowired
	private PositionService		ps;


	//First test, apply to a position

	@Test
	public void createApplication() {
		final Application a1 = this.as.create();
		final int hid = super.getEntityId("hacker1");

		final Hacker h = this.hs.findOne(hid);
		a1.setHacker(h);
		final int position8 = super.getEntityId("position8");
		final Position p1 = this.ps.findOne(position8);
		a1.setPosition(p1);

		final Application a2 = this.as.create();
		a2.setHacker(h);
		final int position1 = super.getEntityId("position1");
		final Position p2 = this.ps.findOne(position1);
		a2.setPosition(p2);

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #10
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				a1, null
			},

			/**
			 * TESTING REQUIREMENT #10
			 * NEGATIVE TEST: YOU CANNOT APPLY TO A POSITION TWICE
			 * (Expected ValidationException.class)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				a2, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template("hacker1", (Application) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void template(final String username, final Application a, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		this.startTransaction();

		try {
			this.authenticate(username);

			this.as.firstSave(a);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		this.rollbackTransaction();
	}
}
