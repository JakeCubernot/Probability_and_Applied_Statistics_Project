package Cards;

public class Charmander extends Pokemon implements PokemonCardInterface {
		
	public Charmander() {
        setHP(70);
    }
	
    public void attackOne(Pokemon enemyPokemon) {
        enemyPokemon.setHP(enemyPokemon.getHP() - 10);
    }
}
