package Cards;
import java.util.Random;

public class Pikachu extends Pokemon implements PokemonCardInterface {
	
    public Pikachu() {
    	super("Pikachu", "/Card Images/Pikachu.jpg", 2, new String[]{"Gnaw", "Thunder Jolt"});
    	setMaxHP(40);
        setHP(40);
    }

    // Attack One: Gnaw
    @Override
    public void attackOne(Pokemon enemyPokemon) {
        enemyPokemon.setHP(enemyPokemon.getHP() - 10);
    }

    // Attack Two: Thunder Jolt
    @Override
    public void attackTwo(Pokemon enemyPokemon) {
        Random coinFlip = new Random();
        if (coinFlip.nextInt(2) == 0) {
            this.setHP(this.getHP() - 10);
            if (this.getHP() < 1) {
            	this.setHP(1);
            }
            enemyPokemon.setHP(enemyPokemon.getHP() - 30);
        } else {
            enemyPokemon.setHP(enemyPokemon.getHP() - 30);
        }
    }
}
