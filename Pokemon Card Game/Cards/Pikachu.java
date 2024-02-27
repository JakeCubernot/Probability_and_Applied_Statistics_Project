package Cards;
import java.util.Random;

public class Pikachu extends Pokemon implements PokemonCardInterface {
	
    public Pikachu() {
        setHP(70);
    }

    // Attack One: Gnaw
    public void attackOne(Pokemon enemyPokemon) {
        enemyPokemon.setHP(enemyPokemon.getHP() - 10);
    }

    // Attack Two: Thunder Jolt
    public void attackTwo(Pokemon enemyPokemon) {
        Random coinFlip = new Random();
        if (coinFlip.nextInt(1) == 1) {
            this.setHP(this.getHP() - 10);
            enemyPokemon.setHP(enemyPokemon.getHP() - 30);
        } else {
            enemyPokemon.setHP(enemyPokemon.getHP() - 30);
        }
    }
}
