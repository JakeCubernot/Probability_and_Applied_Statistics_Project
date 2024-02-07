// Let's write a monte carlo simulation. That means, using raw brute force, try to figure out
// something interesting.

// What if your deck had exactly 1 pokemon. How many times would you expect to have to
// "Mulligan" in order to have your only pokemon in your hand.

// What if your deck had 2? Etc.

// Write a simulation that shows the expected mulligans at 1 - 60 pokemons in your deck.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class PokemonCardGame {

    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<Card> hand = new ArrayList<Card>();
    int deckSize = 60;

    public PokemonCardGame() {
        deck.add(new Pokemon());

        for (int i = 1; i < deckSize; i++) {
        	deck.add(new Pokemon());
            deck.add(new Energy());
            deck.add(new Trainer());
        }
        Collections.shuffle(deck);
        
    }
    
    public PokemonCardGame(int pokemonCardCount, int nonPokemonCardCount) {
    	for (int i = 0; i < pokemonCardCount; i++) {
            deck.add(new Pokemon());
        }
        for (int i = 0; i < nonPokemonCardCount; i++) {
            deck.add(new Energy());
        }
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        Random rng = new Random();
        int cardIndex = rng.nextInt(deck.size());
        Card drawnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return drawnCard;
    }

    public void drawHand() {
        for (int i = 0; i < 7; i++) {
            hand.add(drawCard()); 
        }
    }

    public boolean evaluateOpeningHand() {
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i); // Fixed the index from 1 to i
            if (currentCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }
    
    public boolean pokemonCardProbability() {
        drawHand();
        return evaluateOpeningHand();
        
    }
    
    public String[][] pokemonMulligansProbability() {
    	String[][] resultMatrix = new String[60][2];
    	resultMatrix[0][0] = "Number of Pokemon Cards in a Deck of 60";
    	resultMatrix[0][1] = "Chance (%) of Drawing a Pokemon Card in Opening Hand";
    	int testCount = 10000;
        for(int i = 1; i < 60; i++) {
        	int pokemonCardCount = 0;
        	for(int j = 1; j < testCount; j++) {
        	    PokemonCardGame test = new PokemonCardGame(i, 60 - i);
        	    if (test.pokemonCardProbability()) {
        	        pokemonCardCount++;
        	    }
        	}
        	double pokemonCardProbability = ((double) pokemonCardCount / testCount) * 100.0;
        	resultMatrix[i][0] = String.valueOf(i);
        	resultMatrix[i][1] = String.valueOf(pokemonCardProbability + "%");

        }
        return resultMatrix;
    }
    
    // I got this method from https://springhow.com/java-write-csv/.
    public void writeCSVFile(String[][] matrixData) throws IOException {
    	
    	File csvFile = new File("mulligansData.csv");
    	FileWriter fileWriter = new FileWriter(csvFile);
    	
    	for (String[] data : matrixData) {
    	    StringBuilder line = new StringBuilder();
    	    for (int i = 0; i < data.length; i++) {
    	        line.append(data[i]);
    	        if (i != data.length - 1) {
    	            line.append(',');
    	        }
    	    }
    	    line.append("\n");
    	    fileWriter.write(line.toString());
    	}
    	fileWriter.flush();
    	fileWriter.close();
    }

    public void runMulligansTest() throws IOException {
    	
    	String[][] mulligansData = pokemonMulligansProbability();
    	writeCSVFile(mulligansData);
    }
    
}