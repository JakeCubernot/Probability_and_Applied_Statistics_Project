package Cards;
import java.util.Random;

public class Caterpie extends Pokemon implements PokemonCardInterface {
	
    public Caterpie() {
    	super("Caterpie", "/Card Images/Caterpie.jpg", 1, new String[]{"String Shot"});
    	setMaxHP(40);
        setHP(40);
    }

    // Attack One: String Shot
    @Override
    public void attackOne(Pokemon enemyPokemon) {
        Random coinFlip = new Random();
        if (coinFlip.nextInt(2) == 1) {
            enemyPokemon.setIsParalyzed(true);
            enemyPokemon.setHP(enemyPokemon.getHP() - 10);
        } else {
            enemyPokemon.setHP(enemyPokemon.getHP() - 10);
        }
    }
    
}
