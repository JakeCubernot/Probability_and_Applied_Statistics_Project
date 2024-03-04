package Cards;

public class Charmander extends Pokemon implements PokemonCardInterface {
	
	public Charmander() {
		super("Charmander", "/Card Images/Charmander.jpg", 2, new String[]{"Scratch", "Ember"});
		setMaxHP(50);
        setHP(50);
    }
	
	// Attack One: Scratch
    public void attackOne(Pokemon enemyPokemon) {
        enemyPokemon.setHP(enemyPokemon.getHP() - 10);
    }
    
    // Attack Two: Ember
    public void attackTwo(Pokemon enemyPokemon) {
        enemyPokemon.setHP(enemyPokemon.getHP() - 30);
    }
}
