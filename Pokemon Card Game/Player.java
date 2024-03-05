import java.util.ArrayList;
import java.util.Collections;
import Cards.Card;
import Cards.Energy;
import Cards.Pokemon;
import Cards.Trainer;

public class Player {
	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> prizePile = new ArrayList<Card>();
	ArrayList<Card> discardPile = new ArrayList<Card>();
	
	public Player() {
        for (int i = 0; i < 20; i++) {
        	deck.add(new Pokemon());
            deck.add(new Energy());
            deck.add(new Trainer());
        }
        Collections.shuffle(deck);
        System.out.println("deck size: " + deck.size());
	}
	
}
