package Cards;
public class Pokemon extends Card {

    private int hp;
    private int maxHP;
    private int numberOfAttacks;
    private boolean isParalyzed = false;
    private String[] attackNames;
    
    public Pokemon() {
		super("Default Card", "Default URL");
	}

    public Pokemon(String name, String cardImageURL, int numberOfAttacks, String[] attackNames) {
        super(name, cardImageURL);
        this.numberOfAttacks = numberOfAttacks;
        this.attackNames = attackNames;
    }
    
    public int getHP() {
        return hp;
    }
    
    public int getNumberOfAttacks() {
    	return numberOfAttacks;
    }
    
    public boolean getIsParalyzed() {
        return isParalyzed;
    }
    
    public String[] getAttackNames() {
    	return attackNames;
    }
    
    public int getMaxHP() {
    	return maxHP;
    }

    public void setHP(int hp) {
        this.hp = hp;
        if (this.hp > maxHP) {
        	this.hp = maxHP;
        }
    }
    
    public void setMaxHP(int maxHP) {
    	this.maxHP = maxHP;
    }

    public void setIsParalyzed(boolean isParalyzed) {
        this.isParalyzed = isParalyzed;
    }
    
    public void attackOne(Pokemon enemyPokemon) {
    	// Default attack one function
    }
    
    public void attackTwo(Pokemon enemyPokemon) {
    	// Default attack two function
    }
    
}
