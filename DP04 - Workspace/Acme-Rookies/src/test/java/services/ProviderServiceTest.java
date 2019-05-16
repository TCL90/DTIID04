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

import domain.Provider;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ProviderServiceTest extends AbstractTest{
	
			@Autowired
			private ProviderService	providerService;


			/**
			 * TESTING REQUIREMENT #7.1 (Register as a provider)
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS IN THIS TEST: 100%
			 * COVERED INSTRUCTIONS IN CompanyService: 30.1%
			 * COVERED INSTRUCTIONS IN ActorService: 35.6%
			 * COVERED DATA IN THIS TEST: 40%
			 * */

			@Test
			public void createAuditor() {
				final UserAccount ua = new UserAccount();
				ua.setPassword("provider5");
				ua.setUsername("provider5");
				final Collection<Authority> coll = new ArrayList<Authority>();
				final Authority a = new Authority();
				a.setAuthority(Authority.PROVIDER);
				coll.add(a);
				ua.setAuthorities(coll);
				final Provider nc = this.providerService.create();
				nc.setAddress("Sample address");
				nc.setIsBanned(false);
				nc.setEmail("newProvider@gmail.com");
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
				nc.setMake("make");

				final UserAccount ua2 = new UserAccount();
				ua2.setPassword("provider6");
				ua2.setUsername("provider6");
				final Collection<Authority> coll2 = new ArrayList<Authority>();
				final Authority a2 = new Authority();
				a.setAuthority(Authority.PROVIDER);
				coll2.add(a2);
				ua2.setAuthorities(coll2);
				final Provider nc2 = this.providerService.create();
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
				nc.setMake("make");

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
					this.template2((Provider) testingData[i][0], (Class<?>) testingData[i][1]);
			}
			protected void template2(final Provider lr, final Class<?> expected) {

				Class<?> caught;

				caught = null;

				//this.startTransaction();

				try {
					this.providerService.save(lr);
					this.providerService.flush();
				} catch (final Throwable oops) {
					caught = oops.getClass();
				}

				super.checkExceptions(expected, caught);

				//this.commitTransaction();
			}

			/**
			 * THIS TEST IS FOR TESTING THE REQUIREMENT #9.3 (EDIT PERSONAL DATA)
			 * COVERED INSTRUCTIONS IN THIS TEST: 100%
			 * COVERED INSTRUCTIONS IN CompanyService: 30.1%
			 * COVERED INSTRUCTIONS IN ActorService: 35.6%
			 * * COVERED DATA IN THIS TEST: 50%
			 * */

			@Test
			public void editCompany() {

				final List<Provider> providers = (List<Provider>) this.providerService.findAll();

				final Provider provid1 = providers.get(0);

				provid1.setAddress("Sample address");
				provid1.setEmail("newProvider@gmail.com");
				provid1.setName("Sample");
				provid1.setPhoneNumber("+34 123145689");
				provid1.setPhoto("http://www.sample.com");
				provid1.setSurname("Sample surname");
				provid1.setMake("make");

				final Provider provid2 = providers.get(1);

				provid2.setAddress(null);
				provid2.setIsBanned(false);
				provid2.setEmail(null);
				provid2.setFlagSpam(false);
				provid2.setName(null);
				provid2.setPhoneNumber("+34 1231456789");
				provid2.setPhoto("http://www.sample.com");
				provid2.setSurname("Sample surname");
				provid2.setMake("make");

				final Object testingData[][] = {

					/**
					 * TESTING REQUIREMENT #1
					 * POSITIVE TEST
					 * COVERED INSTRUCTIONS: 100%
					 * COVERED DATA: 25%
					 * */
					{
						"provider1", provid1, null
					}, {
						"provider2", provid2, ConstraintViolationException.class
					},
				};

				for (int i = 0; i < testingData.length; i++)
					this.template((String) testingData[i][0], (Provider) testingData[i][1], (Class<?>) testingData[i][2]);
			}
			protected void template(final String username, final Provider comp, final Class<?> expected) {

				Class<?> caught;

				caught = null;

				this.startTransaction();

				try {
					this.authenticate(username);

					this.providerService.save(comp);

					this.providerService.flush();
					this.unauthenticate();

				} catch (final Throwable oops) {
					caught = oops.getClass();
				}

				super.checkExceptions(expected, caught);
				this.rollbackTransaction();
			}

}
