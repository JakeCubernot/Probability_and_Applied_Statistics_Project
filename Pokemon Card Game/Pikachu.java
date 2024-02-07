public class Pikachu extends Pokemon {
    
    public Pikachu() {
        setHp(70);
    }

    public void quickAttack(Pokemon unfortunatePokemon) {
        // deal 10 damage
        int currentHp = unfortunatePokemon.getHp();
        int resultingHp = currentHp - 10;
        unfortunatePokemon.setHp(resultingHp);
    }

    public void electroBall() {
        // deal 60 damage
    }
}
