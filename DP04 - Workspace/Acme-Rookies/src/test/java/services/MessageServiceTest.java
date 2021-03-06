
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Rookie;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private MessageService	messageService;

	@Autowired
	private RookieService	hs;

	@Autowired
	private BoxService		bs;


	/**
	 * TESTING REQUIREMENT #23 (Messages)
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN MessageService: 7.1%
	 * COVERED INSTRUCTIONS IN BoxService: 15.8%
	 * COVERED DATA IN THIS TEST: 40%
	 * */

	@Test
	public void createMessage() {
		this.authenticate("rookie1");
		final Message m = this.messageService.create();
		final int idrookie = super.getEntityId("rookie1");
		final Rookie h = this.hs.findOne(idrookie);
		m.setRecipient(h);
		final int idrookie2 = super.getEntityId("rookie2");
		final Rookie h2 = this.hs.findOne(idrookie2);
		m.setRecipient(h2);
		m.setBody("test message");
		m.setBroadcast(false);
		m.setFlagSpam(false);
		m.setMoment(new Date());
		m.setSubject("test");
		m.setTag("TEST");

		this.unauthenticate();

		this.authenticate("rookie1");
		final Message m2 = this.messageService.create();
		m.setRecipient(h2);
		m.setBody("test message");
		m.setBroadcast(false);
		m.setFlagSpam(false);
		m.setMoment(new Date());
		m.setSubject("test");
		m.setTag("TEST");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #23
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 21%
			 * */
			{
				"rookie2", m, null
			},

			/**
			 * TESTING REQUIREMENT #23
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 21%
			 * 
			 */

			{
				"rookie2", m2, NullPointerException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Message) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final Message p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.bs.sendMessage(p);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
