
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Item;
import domain.Provider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ItemServiceTest extends AbstractTest {

	//SUT
	@Autowired
	private ItemService		itemService;

	@Autowired
	private ProviderService	providerService;


	/**
	 * TESTING REQUIREMENT #10.1 A PROVIDER CAN CREATE ITEMS.
	 * POSITIVE TEST
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN itemService: 89.4%
	 * COVERED DATA IN THIS TEST: 75%
	 * */
	@Test
	public void createItem() {
		final int providerId = super.getEntityId("provider1");
		final Provider actual = this.providerService.findOne(providerId);
		this.authenticate("provider1");
		final Item item = this.itemService.create();
		item.setDescription("description");
		item.setLink("http://www.link.com");
		item.setPhotos(new ArrayList<String>());
		item.setName("testing");
		item.setProvider(actual);

		final Item item2 = this.itemService.create();
		item2.setDescription("description");
		item2.setLink("http://www.link.com");
		item2.setPhotos(new ArrayList<String>());
		item2.setName("");
		item2.setProvider(actual);

		final Item item3 = this.itemService.create();
		item3.setDescription("description");
		item3.setLink("not a link");
		item3.setPhotos(new ArrayList<String>());
		item3.setName("test");
		item3.setProvider(actual);

		final Item item4 = this.itemService.create();
		item4.setDescription("");
		item4.setLink("http://www.link.com");
		item4.setPhotos(new ArrayList<String>());
		item4.setName("test");
		item4.setProvider(actual);

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 40%
			 * */
			{
				"provider1", item, null
			},

			/**
			 * // * TESTING REQUIREMENT #10.1
			 * // * NEGATIVE TEST, YOU CANNOT CREATE AN ITEM WITH NO NAME
			 * // * COVERED INSTRUCTIONS: 100%
			 * // * COVERED DATA: 40%
			 * // *
			 */

			{
				"provider1", item2, IllegalArgumentException.class
			},

			/**
			 * TESTING REQUIREMENT #10.1
			 * NEGATIVE TEST: YOU CANNOT CREATE AN ITEM WITH INVALID LINK
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 4.%
			 * */
			{
				"provider1", item3, ConstraintViolationException.class
			},

			/**
			 * TESTING REQUIREMENT #10.1
			 * NEGATIVE TEST: YOU CANNOT CREATE AN ITEM WITH NO DESCRIPTION
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 10%
			 * */
			{
				"provider1", item4, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template2((String) testingData[i][0], (Item) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	/**
	 * TESTING REQUIREMENT #10.1 A PROVIDER CAN EDIT HIS OR HER ITEMS
	 * POSITIVE AND NEGATIVE TESTS
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN ItemService: 89.4%
	 * COVERED DATA IN THIS TEST: 75%
	 * */

	@Test
	public void editItem() {
		this.authenticate("provider1");
		final int itemId1 = super.getEntityId("item1");
		final Item item1 = this.itemService.findOne(itemId1);

		item1.setName("The new name");

		final int itemId2 = super.getEntityId("item4");
		final Item item2 = this.itemService.findOne(itemId2);
		item2.setDescription("");

		final int itemId3 = super.getEntityId("item2");
		final Item item3 = this.itemService.findOne(itemId3);
		item3.setName("");

		final int itemId4 = super.getEntityId("item3");
		final Item item4 = this.itemService.findOne(itemId4);
		item4.setLink("");

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 40%
			 * */
			{
				"provider1", item1, null
			},

			/**
			 * // * TESTING REQUIREMENT #10.1
			 * // * NEGATIVE TEST: YOU CANT EDIT A TEST WITH NO DESCRIPTION
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 40%
			 * 
			 */

			{
				"provider1", item2, IllegalArgumentException.class
			},

			/**
			 * TESTING REQUIREMENT #10.1
			 * NEGATIVE TEST: YOU CANNOT CREATE AN ITEM WITH NO NAME
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 40%
			 * */
			{
				"provider1", item3, IllegalArgumentException.class
			},

			/**
			 * TESTING REQUIREMENT #10.1
			 * NEGATIVE TEST: YOU CANNOT CREATE AN ITEM WITH NO LINK
			 * (Expected IllegalArgumentException)
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 40%
			 * */
			{
				"provider1", item4, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEd((String) testingData[i][0], (Item) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	/**
	 * TESTING REQUIREMENT #10.1 A PROVIDER CAN DELETE HIS OR HER ITEMS
	 * POSITIVE AND NEGATIVE TESTS
	 * COVERED INSTRUCTIONS IN THIS TEST: 100%
	 * COVERED INSTRUCTIONS IN ItemService: 89.4%
	 * COVERED DATA IN THIS TEST: 75%
	 * */

	@Test
	public void deleteItem() {
		this.authenticate("provider1");
		final int itemId1 = super.getEntityId("item1");
		final Item item1 = this.itemService.findOne(itemId1);

		final int itemId2 = super.getEntityId("item2");
		final Item item2 = this.itemService.findOne(itemId2);

		this.unauthenticate();

		final Object testingData[][] = {

			/**
			 * TESTING REQUIREMENT #10.1
			 * POSITIVE TEST
			 * COVERED INSTRUCTIONS: 100%
			 * COVERED DATA: 50%
			 * */
			{
				"provider1", item1, null
			},

			/**
			 * // * TESTING REQUIREMENT #10.1
			 * // * NEGATIVE TEST, PROVIDER2 IS NOT THE OWNER OF THE ITEM2.
			 * // * COVERED INSTRUCTIONS: 100%
			 * // * COVERED DATA: 50%
			 * // *
			 */

			{
				"provider2", item2, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateD((String) testingData[i][0], (Item) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void template2(final String username, final Item p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.itemService.save(p);
			this.itemService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	protected void templateEd(final String username, final Item p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			final Item check = this.itemService.save(p);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	protected void templateD(final String username, final Item p, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.itemService.delete(p.getId());
			this.itemService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
}
