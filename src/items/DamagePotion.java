package items;

import main.*;

/**
 * Item that can be used on a monster to increase their damage by the given damage amount.
 * @author Farzad and Daniel
 */
public class DamagePotion extends Item {
    
	/**
	 * Fields
	 */
	private static int damageIncrease = 10;
	private static String name = "Damage Potion";
	private static String description = "Increase a monster's damage by " + damageIncrease + ".";
	private static int cost = 20;

	
	/**
	 * Constructors
	 */
	
	/**
	 * Create a new DamagePotion object.
	 * Set the value of game to the given GameEnvironment object.
	 * @param game the given GameEnvironment object.
	 */
	public DamagePotion (GameEnvironment game) {
		super(name, description, cost, game);
	};
	
	
	/**
	 * Getters and setters
	 */
	
	/**
	 * Get the value of damageIncrease
	 * @return the value of damageIncrease
	 */
	public static int getDamageIncrease() {
		return damageIncrease;
	}


	/**
	 * Set the value of damageIncrease
	 * @param damageIncrease the new value of damageIncrease
	 */
	public static void setDamageIncrease(int damageIncrease) {
		DamagePotion.damageIncrease = damageIncrease;
	}

	
	/**
     * Functional
     */

    /**
     * Increase the monster's damage by damageIncrease amount.
     * @param monster the given monster
     */
    public void use(Monster monster)
    {	
    	monster.setDamage(monster.getDamage() + damageIncrease);
    	player.getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the DamagePotion class.
     * @return item the new IncreaseDamge object.
     */
    public Item clone() {
    	return new DamagePotion(game);
    }

}