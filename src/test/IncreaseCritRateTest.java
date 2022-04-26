package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class IncreaseCritRateTest {
	
	private GameEnvironment game;
	private Player player;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
	}

	
	@Test
	void testUse1() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		monster.setCritRate(0);
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems().getList());
		assertEquals(IncreaseCritRate.getCritIncrease(), monster.getCritRate());
	}

	
	@Test
	void testUse2() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Monster crit rate is only partially increased
		Monster monster = new Chunky(game);
		monster.setCritRate(monster.getMaxCritRate() - (IncreaseCritRate.getCritIncrease() / 2));
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems().getList());
		assertEquals(monster.getMaxCritRate(), monster.getCritRate());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Monster is already at max crit rate
		Monster monster = new Chunky(game);
		monster.setCritRate(1);
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Crit Rate is already maxed out!");
		}
		
	}
	
	
	@Test
	void testUse4() throws InventoryFullException, NotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky(game);
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (NotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
