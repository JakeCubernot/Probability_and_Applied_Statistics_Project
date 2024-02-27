package Cards;
import java.util.Random;

import javax.swing.ImageIcon;

public class Caterpie extends Pokemon implements PokemonCardInterface {
	
	private String cardImageLocation = "/Card Images/Caterpie.jpg";
	
    public Caterpie() {
        setHP(40);
    }

    // Attack One: String Shot
    public void attackOne(Pokemon enemyPokemon) {
        Random coinFlip = new Random();
        if (coinFlip.nextInt(1) == 0) {
            enemyPokemon.setIsParalyzed(true);
            enemyPokemon.setHP(enemyPokemon.getHP() - 10);
        } else {
            enemyPokemon.setHP(enemyPokemon.getHP() - 10);
        }
    }

}
