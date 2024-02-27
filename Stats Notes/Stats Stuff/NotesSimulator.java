import java.util.ArrayList;
import java.util.Random;

public class NotesSimulator {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> bench;

    public NotesSimulator() {

    }

    public void buildDeck() {
        for(int i = 0; i < 10; i++) {
            deck.add(new Pokemon());
        }
        for(int i = 0; i < 10; i++) {
            deck.add(new Trainer());
        }
        for(int i = 0; i < 10; i++) {
            deck.add(new Energy());
        }
    }
    
    // this should be added to the trainer as a subclass, and make trainer interface, TrainerActions, with nestball overriding public playable
    //
    public void playNestBall() {
        ArrayList<Card> tempPokemon = new ArrayList<>();
        boolean done = false;
        int i = 0;
        while(!done) {
            if(deck.get(i) instanceof Pokemon) {
                tempPokemon.add(deck.get(i));
                deck.remove(i);
            } else {
                i++;
            }
        }
        System.out.println("Pokemon found so far: " + tempPokemon);
        System.out.println("Count: " + tempPokemon.size());

        Random rng = new Random();
        int saveRandomNumber = rng.nextInt(tempPokemon.size());
        bench.add(tempPokemon.get(saveRandomNumber));
        tempPokemon.remove(saveRandomNumber);

        for(int j = 0; j < tempPokemon.size(); i++) {
            deck.add(tempPokemon.get(i));
        }
    }

    public void run() {
        buildDeck();
        playNestBall();

        //Look at hand 
        //get card from hand
        //card.playable()
    }
    
}
