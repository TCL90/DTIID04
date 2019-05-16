
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import services.RookieService;
import utilities.AbstractTest;
import domain.Rookie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AARookieServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private RookieService	rookieService;

	
	@Test
	public void editRookie() {

		final List<Rookie> companies = (List<Rookie>) this.rookieService.findAll();

		final Rookie compa1 = companies.get(0);

		compa1.setAddress("Sample address");
		compa1.setEmail("newRookie@gmail.com");
		compa1.setName("Sample");
		compa1.setPhoneNumber("+34 123145689");
		compa1.setPhoto("http://www.sample.com");
		compa1.setSurname("Sample surname");

		final Rookie com2 = companies.get(1);

		com2.setAddress(null);
		com2.setIsBanned(false);
		com2.setEmail(null);
		com2.setFlagSpam(false);
		com2.setName(null);
		com2.setPhoneNumber("+34 1231456789");
		com2.setPhoto("http://www.sample.com");
		com2.setSurname("Sample surname");

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 25%
			 * */
			{
				"rookie1", compa1, null
			}, {
				"rookie2", com2, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Rookie) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void template(final String username, final Rookie comp, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		this.startTransaction();

		try {
			this.authenticate(username);

			this.rookieService.save(comp);

			this.rookieService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
		this.rollbackTransaction();
	}

}
