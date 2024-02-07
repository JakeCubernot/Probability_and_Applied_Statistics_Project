public class TestPikachu {

    public static void main(String[] args) {
        Pikachu pikaMain = new Pikachu();
        Pikachu pikaTarget = new Pikachu();
        Bulbasaur newBulb = new Bulbasaur();

        System.out.println("Bulbasaur, USE LEECHSEED!");
        newBulb.leechSeed(pikaTarget);
        System.out.println("Pika Main Hp: " + pikaMain.getHp() + ". Bulbasaur Main Hp: " + newBulb.getHp());

        pikaMain.quickAttack(newBulb);
        
        while (pikaTarget.getHp() > 0) {
            System.out.println("Pikachu, USE QUICK ATTACK!");
            pikaMain.quickAttack(pikaTarget);
            System.out.println("Pika Target Hp: " + pikaTarget.getHp());
        }
    }
}