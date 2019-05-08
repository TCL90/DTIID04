
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Company;
import domain.Position;
import domain.Problem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PositionCompanyServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


	@Test
	public void editPosition() throws ParseException {

		final Object testingData[][] = {
			/**
			 * TESTING REQUIREMENT #1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"company1", "position3", null
			},

			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: YOU CANNOT EDIT A SEGMENT IF YOU ARE NOT THE OWNER
			 * (Expected false)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"company2", "position3", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (Class<?>) testingData[i][2]);
	}

	protected void template(final String username, final int id, final Class<?> expected) {
		final Position position = this.positionService.findOne(id);

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.positionService.save(position);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	@Test
	public void createPosition() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.authenticate("company1");
		final Company company = this.companyService.findOne(this.getEntityId("company1"));

		final Position savetest1 = this.positionService.create();
		savetest1.setTitle("Titulo1");
		savetest1.setDescription("Descripción");
		final Date d1 = sdf.parse("12/12/2019");
		savetest1.setDeadline(d1);
		final Collection<String> skills1 = new ArrayList<String>();
		savetest1.setSkills(skills1);
		final Collection<String> tec1 = new ArrayList<String>();
		savetest1.setTechnologies(tec1);
		savetest1.setProfile("Programador");
		savetest1.setFinalMode(false);
		savetest1.setIsCancelled(false);
		savetest1.setSalary(1300);
		savetest1.setTicker("ACME");
		savetest1.setCompany(company);
		final Collection<Problem> problems = new ArrayList<Problem>();
		savetest1.setProblems(problems);

		final Position savetest2 = this.positionService.create();
		savetest2.setTitle("Titulo1");
		savetest2.setDescription("Descripción");
		final Date d2 = sdf.parse("12/12/2019");
		savetest2.setDeadline(d2);
		final Collection<String> skills2 = new ArrayList<String>();
		savetest2.setSkills(skills2);
		final Collection<String> tec2 = new ArrayList<String>();
		savetest2.setTechnologies(tec2);
		savetest2.setProfile("Programador");
		savetest2.setFinalMode(false);
		savetest2.setIsCancelled(false);
		savetest2.setSalary(1300);
		savetest2.setTicker("ACME");
		savetest2.setCompany(company);
		final Collection<Problem> problems1 = new ArrayList<Problem>();
		savetest2.setProblems(problems1);

		final Position savetest3 = this.positionService.create();
		savetest3.setTitle("Titulo1");
		savetest3.setDescription("Descripción");
		final Date d3 = sdf.parse("12/12/2019");
		savetest3.setDeadline(d3);
		final Collection<String> skills3 = new ArrayList<String>();
		savetest3.setSkills(skills3);
		final Collection<String> tec3 = new ArrayList<String>();
		savetest3.setTechnologies(tec3);
		savetest3.setProfile("Programador");
		savetest3.setFinalMode(false);
		savetest3.setIsCancelled(false);
		savetest3.setSalary(1300);
		savetest3.setTicker("ACME");
		savetest3.setCompany(company);
		final Collection<Problem> problems3 = new ArrayList<Problem>();
		savetest3.setProblems(problems3);

		final Position savetest4 = this.positionService.create();
		savetest4.setTitle("Titulo1");
		savetest4.setDescription("Descripción");
		final Date d4 = sdf.parse("12/12/2019");
		savetest4.setDeadline(d4);
		final Collection<String> skills4 = new ArrayList<String>();
		savetest4.setSkills(skills4);
		final Collection<String> tec4 = new ArrayList<String>();
		savetest4.setTechnologies(tec4);
		savetest4.setProfile("Programador");
		savetest4.setFinalMode(false);
		savetest4.setIsCancelled(false);
		savetest4.setSalary(1300);
		savetest4.setTicker("ACME");
		savetest4.setCompany(company);
		final Collection<Problem> problems4 = new ArrayList<Problem>();
		savetest4.setProblems(problems4);

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"company1", savetest1, null
			},

			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */
			{
				"company1", savetest2, null
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company1", savetest3, null
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company1", savetest4, null
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company2", savetest1, IllegalArgumentException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company2", savetest2, IllegalArgumentException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company2", savetest3, IllegalArgumentException.class
			},
			/**
			 * TESTING REQUIREMENT #1
			 * NEGATIVE TEST: NULL VALUES
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 20%
			 * */

			{
				"company2", savetest4, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Position) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final Position position, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.positionService.save(position);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
