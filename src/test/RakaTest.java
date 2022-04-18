package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AverageJoe;
import main.GameEnvironment;
import main.InvalidTargetException;
import main.InvalidValueException;
import main.Monster;
import main.Raka;

class RakaTest {

	private GameEnvironment game;
	private Raka raka;
	private Monster target;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		raka = new Raka(game);
		target = new AverageJoe(game);
	}

	@Test
	public void testHeal1() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to max health  
		target.takeDamage(raka.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth(), target.getHealth());
	}
	
	@Test 
	public void testHeal2() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to less than max health 
		target.takeDamage(target.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth()-4, target.getHealth());
	}
	
	@Test
	public void testHeal3() throws InvalidTargetException, InvalidValueException {
		//Raka tries to heal fainted ally 
		try {
			target.takeDamage(target.getHealth());
			raka.healAllies(target);
		}
		catch(InvalidTargetException e) {
			assertEquals(0, target.getHealth());
			assertEquals(e.getMessage(), "Target is fainted!");
		}
	}
	
	@Test
	public void testBuff1() throws InvalidTargetException { 
		//Raka buffs ally damage 
		raka.increaseDamage(target);
		assertEquals(raka.getDamageBefore()+raka.getBuffAmount(), target.getDamage());
	}
	
	@Test 
	public void testBuff2() throws InvalidTargetException, InvalidValueException { 
		//Raka tries to buff fainted ally
		target.takeDamage(target.getHealth());
		try {
			raka.increaseDamage(target);
		}
		catch(InvalidTargetException e) {
			assertEquals(20, target.getDamage());
			assertEquals(e.getMessage(), "Target is fainted!");
		}
	}

}