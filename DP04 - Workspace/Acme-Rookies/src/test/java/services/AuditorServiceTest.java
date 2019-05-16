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

import domain.Auditor;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest{
	
	//SUT
		@Autowired
		private AuditorService	auditorService;


		/**
		 * TESTING REQUIREMENT #7.1 (Register as a auditor)
		 * POSITIVE TEST
		 * COVERED INSTRUCTIONS IN THIS TEST: 100%
		 * COVERED INSTRUCTIONS IN CompanyService: 30.1%
		 * COVERED INSTRUCTIONS IN ActorService: 35.6%
		 * COVERED DATA IN THIS TEST: 40%
		 * */

		@Test
		public void createAuditor() {
			final UserAccount ua = new UserAccount();
			ua.setPassword("auditor5");
			ua.setUsername("auditor5");
			final Collection<Authority> coll = new ArrayList<Authority>();
			final Authority a = new Authority();
			a.setAuthority(Authority.AUDITOR);
			coll.add(a);
			ua.setAuthorities(coll);
			final Auditor nc = this.auditorService.create();
			nc.setAddress("Sample address");
			nc.setIsBanned(false);
			nc.setEmail("newAuditor@gmail.com");
			nc.setFlagSpam(false);
			nc.setName("Sample");
			nc.setPhoneNumber("+34 1231456789");
			nc.setPhoto("http://www.sample.com");
			nc.setSurname("Sample surname");
			nc.setUserAccount(ua);
			nc.setVat("55555555R");
			nc.setCvv(123);
			nc.setExpirationMonth(12);
			nc.setExpirationYear(2019);
			nc.setHolderName("sampleHolderName");
			nc.setMakeName("VISA");
			nc.setNumber("4929094533598606");

			final UserAccount ua2 = new UserAccount();
			ua2.setPassword("auditor6");
			ua2.setUsername("auditor6");
			final Collection<Authority> coll2 = new ArrayList<Authority>();
			final Authority a2 = new Authority();
			a.setAuthority(Authority.COMPANY);
			coll2.add(a2);
			ua2.setAuthorities(coll2);
			final Auditor nc2 = this.auditorService.create();
			nc2.setAddress("Sample address");
			nc2.setIsBanned(false);
			nc2.setEmail("sad");
			nc2.setFlagSpam(false);
			nc2.setName(null);
			nc2.setPhoneNumber("+34 1231456789");
			nc2.setPhoto("sample");
			nc2.setSurname("");
			nc2.setUserAccount(ua2);
			nc2.setVat("55555555R");
			nc2.setCvv(123);
			nc2.setExpirationMonth(23);
			nc2.setExpirationYear(1);
			nc2.setHolderName("sampleHolderName");
			nc2.setMakeName("AA");
			nc2.setNumber("123");

			final Object testingData[][] = {

				/**
				 * TESTING REQUIREMENT #7.1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
				 * */
				{
					nc, null
				},

				/**
				 * TESTING REQUIREMENT #7.1
				 * NEGATIVE TEST: YOU CANNOT REGISTER WITH SOME NULL VALUES
				 * (Expected ConstraintViolationException)
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 10%
				 * */
				{
					nc2, ValidationException.class
				},
			};

			for (int i = 0; i < testingData.length; i++)
				this.template2((Auditor) testingData[i][0], (Class<?>) testingData[i][1]);
		}
		protected void template2(final Auditor lr, final Class<?> expected) {

			Class<?> caught;

			caught = null;

			//this.startTransaction();

			try {
				this.auditorService.save(lr);
				this.auditorService.flush();
			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			super.checkExceptions(expected, caught);

			//this.commitTransaction();
		}

		/**
		 * THIS TEST IS FOR TESTING THE REQUIREMENT #8.2 (EDIT PERSONAL DATA)
		 * COVERED INSTRUCTIONS IN THIS TEST: 100%
		 * COVERED INSTRUCTIONS IN CompanyService: 30.1%
		 * COVERED INSTRUCTIONS IN ActorService: 35.6%
		 * * COVERED DATA IN THIS TEST: 50%
		 * */

		@Test
		public void editCompany() {

			final List<Auditor> auditors = (List<Auditor>) this.auditorService.findAll();

			final Auditor audito1 = auditors.get(0);

			audito1.setAddress("Sample address");
			audito1.setEmail("newCompany@gmail.com");
			audito1.setName("Sample");
			audito1.setPhoneNumber("+34 123145689");
			audito1.setPhoto("http://www.sample.com");
			audito1.setSurname("Sample surname");

			final Auditor audito2 = auditors.get(1);

			audito2.setAddress(null);
			audito2.setIsBanned(false);
			audito2.setEmail(null);
			audito2.setFlagSpam(false);
			audito2.setName(null);
			audito2.setPhoneNumber("+34 1231456789");
			audito2.setPhoto("http://www.sample.com");
			audito2.setSurname("Sample surname");

			final Object testingData[][] = {

				/**
				 * TESTING REQUIREMENT #1
				 * POSITIVE TEST
				 * COVERED INSTRUCTIONS: 100%
				 * COVERED DATA: 25%
				 * */
				{
					"auditor1", audito1, null
				}, {
					"auditor2", audito2, ConstraintViolationException.class
				},
			};

			for (int i = 0; i < testingData.length; i++)
				this.template((String) testingData[i][0], (Auditor) testingData[i][1], (Class<?>) testingData[i][2]);
		}
		protected void template(final String username, final Auditor comp, final Class<?> expected) {

			Class<?> caught;

			caught = null;

			this.startTransaction();

			try {
				this.authenticate(username);

				this.auditorService.save(comp);

				this.auditorService.flush();
				this.unauthenticate();

			} catch (final Throwable oops) {
				caught = oops.getClass();
			}

			super.checkExceptions(expected, caught);
			this.rollbackTransaction();
		}

}
