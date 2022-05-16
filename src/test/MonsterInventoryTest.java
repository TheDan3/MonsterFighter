package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import main.*;
import monsters.Chunky;

class MonsterInventoryTest {
	
	private GameEnvironment game;
	private MonsterInventory myMonsters;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		myMonsters = game.getPlayer().getMonsters();
	}
	
	
	@Test
	public void testAddMonster1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		myMonsters.add(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster);
		assertEquals(testMonsterList, myMonsters.getList());
	}
	
	
	@Test
	public void testAddMonster2() throws InventoryFullException {
		// Inventory full
		Monster testMonster = new Chunky(game);
		for (int i = 0; i < myMonsters.getMaxSize(); i++) {			
			myMonsters.add(testMonster);
		}
		try {    		
			myMonsters.add(testMonster);
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Monster inventory is full!");
		}
	}

	
	@Test
	public void testRemoveMonster1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		myMonsters.add(testMonster);
		myMonsters.remove(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		assertEquals(testMonsterList, myMonsters.getList());
	}
	
	
	@Test
	public void testRemoveMonster2() throws InventoryFullException {
		// Multiple items of the same type
		Monster testMonster1 = new Chunky(game);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster1);
		myMonsters.add(testMonster2);
		myMonsters.add(testMonster2);
		myMonsters.remove(testMonster2);
		myMonsters.remove(testMonster1);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster2);
		assertEquals(testMonsterList, myMonsters.getList());
	}

	
	@Test
	void testMonstersFull() throws InventoryFullException {
		Monster testMonster = new Chunky(game);
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		assertTrue(myMonsters.isFull());
	}

}
