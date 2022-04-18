package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class IncreaseCritRateTest {
	
	private GameEnvironment game;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	void testUse1() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		monster.setCritRate(0);
		Item item = new IncreaseCritRate(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getItemList());
		assertEquals(IncreaseCritRate.getCritIncrease(), monster.getCritRate());
	}

	
	@Test
	void testUse2() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster crit rate is only partially increased
		Monster monster = new Chunky(game);
		monster.setCritRate(monster.getMaxCritRate() - (IncreaseCritRate.getCritIncrease() / 2));
		Item item = new IncreaseCritRate(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getItemList());
		assertEquals(monster.getMaxCritRate(), monster.getCritRate());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is already at max crit rate
		Monster monster = new Chunky(game);
		monster.setCritRate(1);
		Item item = new IncreaseCritRate(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Crit Rate is already maxed out!");
		}
		
	}
	
	
	@Test
	void testUse4() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky(game);
		Item item = new IncreaseCritRate(game);
		game.getMyMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}