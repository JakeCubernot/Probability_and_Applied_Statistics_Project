public class Pokemon extends Card {

    private int hp;
    private boolean isParalyzed = false;

    public int getHP() {
        return hp;
    }

    public void setHP(int userInputHp) {
        hp = userInputHp;
    }

    public void setIsParalyzed(boolean isParalyzed) {
        this.isParalyzed = isParalyzed;
    }

    public boolean getIsParalyzed() {
        return isParalyzed;
    }
    
}
