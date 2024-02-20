import java.util.Random;

public class Caterpie extends Pokemon implements PokemonCardInterface {

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
