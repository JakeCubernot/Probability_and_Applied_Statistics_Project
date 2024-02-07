public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        setHp(70);
    }

    public void leechSeed(Pokemon target) {
        int currentHp = target.getHp();
        int resultingHp = currentHp - 20;

        int thisCurrentHp = this.getHp();
        this.setHp(thisCurrentHp + 20);

        target.setHp(resultingHp);
    }

}
