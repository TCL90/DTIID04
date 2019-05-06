
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import security.Authority;
import domain.Actor;
import domain.Item;
import domain.Provider;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository	itemRepository;

	@Autowired
	private ActorService	actorService;


	public Item create() {
		final Item res = new Item();
		final Collection<String> photos = new ArrayList<String>();
		res.setPhotos(photos);
		return res;
	}

	public Collection<Item> findAll() {
		return this.itemRepository.findAll();
	}

	public Item findOne(final int itemId) {
		return this.itemRepository.findOne(itemId);
	}

	public Item save(final Item item) {
		Assert.isTrue(item.getDescription() != null && item.getDescription() != "");
		Assert.isTrue(item.getName() != null && item.getName() != "");
		Assert.isTrue(item.getLink() != null && item.getLink() != "");
		return this.itemRepository.save(item);
	}

	public Collection<Item> findAllByProvider(final Actor actual) {
		return this.itemRepository.findAllByProvider(actual.getId());
	}

	public void delete(final int id) {
		final Actor actual = this.actorService.findByPrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));
		final Item item = this.findOne(id);

		final Provider prov = (Provider) actual;

		if (item.getProvider() != null)
			Assert.isTrue(prov.getId() == item.getProvider().getId());
		else
			item.setProvider(prov);
		this.itemRepository.delete(id);
	}

	public void flush() {
		this.itemRepository.flush();
	}

	/*
	 * public Item checkItemOwner(final Item item, final Provider provider) {
	 * if (item.getProvider() != null)
	 * Assert.isTrue(item.getProvider().getId() == provider.getId());
	 * else
	 * item.setProvider(provider);
	 * 
	 * return item;
	 * }
	 */
}
