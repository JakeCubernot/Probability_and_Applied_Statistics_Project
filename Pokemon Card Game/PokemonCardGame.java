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
import java.util.Collections;

import Cards.Card;
import Cards.Caterpie;
import Cards.Charmander;
import Cards.Energy;
import Cards.FireEnergy;
import Cards.GrassEnergy;
import Cards.LightningEnergy;
import Cards.NestBall;
import Cards.Pikachu;
import Cards.Pokemon;
import Cards.ProfessorsResearch;
import Cards.SuperPotion;
import Cards.Trainer;

public class PokemonCardGame {

	public ArrayList<Card> deck = new ArrayList<Card>();
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> bench = new ArrayList<Card>();
    public ArrayList<Card> active = new ArrayList<Card>();
    public ArrayList<Card> prize = new ArrayList<Card>();
    public ArrayList<Card> discard = new ArrayList<Card>();

    public void buildDeck(int pokemonCardCount, int energyCardCount, int trainerCardCount) {
        Pokemon[] pokemonCards = { new Pikachu(), new Caterpie(), new Charmander() };
        Energy[] energyCards = { new LightningEnergy(), new GrassEnergy(), new FireEnergy() };
        Trainer[] trainerCards = { new ProfessorsResearch(), new NestBall(), new SuperPotion() };

    	for (int i = 0; i < pokemonCardCount / pokemonCards.length; i++) {
            for (Pokemon pokemon : pokemonCards) {
                deck.add(pokemon);
            }
        }
        for (int i = 0; i < pokemonCardCount % pokemonCards.length; i++) {
            deck.add(pokemonCards[i]);
        }
        
        for (int i = 0; i < energyCardCount / energyCards.length; i++) {
            for (Energy energy : energyCards) {
                deck.add(energy);
            }
        }
        for (int i = 0; i < energyCardCount % energyCards.length; i++) {
            deck.add(energyCards[i]);
        }

        for (int i = 0; i < trainerCardCount / trainerCards.length; i++) {
            for (Trainer trainer : trainerCards) {
                deck.add(trainer);
            }
        }
        for (int i = 0; i < trainerCardCount % trainerCards.length; i++) {
            deck.add(trainerCards[i]);
        }

        Collections.shuffle(deck);
    }

    public void placePrizeCards() {
        for (int i = 0; i < 6; i++) {
            prize.add(deck.get(0)); 
            deck.remove(0);
        }
    }

    public Card drawCard() {
        Card drawnCard = deck.get(0);
        deck.remove(0);
        return drawnCard;
    }

    public void drawHand() {
        for (int i = 0; i < 7; i++) {
            hand.add(drawCard()); 
        }
    }
    
    public void placePokemonInActive(int handIndex) {
        active.add(deck.get(handIndex)); 
        hand.remove(handIndex);
    }

    public void placePokemonInBench(int handIndex) {
        active.add(deck.get(handIndex)); 
        hand.remove(handIndex);
    }

    public void openingHand(PokemonCardGame player1, PokemonCardGame player2) {
        player1.buildDeck(20, 20, 20);
        player2.buildDeck(20, 20, 20);
        player1.drawHand();
        player2.drawHand();
        boolean openingHandsReady = false;
        while(!openingHandsReady) {
            if (!player1.evaluateOpeningHand() && !player2.evaluateOpeningHand()) {
                player1.restartOpeningHand();
                player2.restartOpeningHand();
                player1.drawHand();
                player2.drawHand();
                System.out.println("Both players do not have a basic Pokemon in their hand. Start over!");
            } else if (player1.evaluateOpeningHand() && !player2.evaluateOpeningHand()) {
                player2.restartOpeningHand();
                player2.drawHand();
                player1.drawCard();
                System.out.println("Player 2 does not have a basic Pokemon in their hand. Player 2 must start over and player 1 draws one card!");
            } else if (!player1.evaluateOpeningHand() && player2.evaluateOpeningHand()) {
                player1.restartOpeningHand();
                player1.drawHand();
                player2.drawCard();
                System.out.println("Player 1 does not have a basic Pokemon in their hand. Player 1 must start over and player 2 draws one card!");
            } else {
                openingHandsReady = true;
                System.out.println("Both players have a basic Pokemon in their hand. The game can begin!");
            }
        }
        player1.placePrizeCards();
        player2.placePrizeCards();
        System.out.println("\nBoth players place 6 prize cards on the board.\n");
    }

    public void openingHand(PokemonCardGame player) {
        player.drawHand();
        boolean openingHandsReady = false;
        while (!openingHandsReady) {
            if (!player.evaluateOpeningHand()) {
                player.restartOpeningHand();
                player.drawHand();
            } else {
                openingHandsReady = true;
            }
        }
        player.placePrizeCards();
    }
    

    public void restartOpeningHand() {
        deck.addAll(hand);
        hand.clear();
        Collections.shuffle(deck);
    }

    public boolean evaluateOpeningHand() {
        for (int i = 0; i < hand.size(); i++) {
            Card currentCard = hand.get(i); 
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
    	int testCount = 1000000;
        for(int i = 1; i < 60; i++) {
        	int pokemonCardCount = 0;
        	for(int j = 1; j <= testCount; j++) {
        	    PokemonCardGame testDeck = new PokemonCardGame();
                testDeck.buildDeck(i, 60 - i, 0);
        	    if (testDeck.pokemonCardProbability()) {
        	        pokemonCardCount++;
        	    }
        	}
        	double pokemonCardProbability = ((double) pokemonCardCount / testCount) * 100.0;
        	resultMatrix[i][0] = String.valueOf(i);
        	resultMatrix[i][1] = String.valueOf(pokemonCardProbability + "%");

        }
        return resultMatrix;
    }
    
    public String[][] pokemonRareCandiesSimulation() {
        String[][] resultMatrix = new String[5][2];
        resultMatrix[0][0] = "Number of Rare Candies in a Deck of 60";
        resultMatrix[0][1] = "Chance (%) of Not Bricking";
        int testCount = 1000000;
        for (int i = 1; i <= 4; i++) {
            int candyInPrizeCount = 0;
            for (int j = 0; j < testCount; j++) {
                PokemonCardGame testDeck = new PokemonCardGame();
                testDeck.buildDeck(20, 40 - i, i);
                openingHand(testDeck);
                if(!isCandyBricked(testDeck)) {
                    candyInPrizeCount++;
                }
            }
            double rareCandiesProbability = ((double) candyInPrizeCount / testCount) * 100.0;
            resultMatrix[i][0] = String.valueOf(i);
            resultMatrix[i][1] = String.valueOf(rareCandiesProbability + "%"); 
        }
        return resultMatrix;
    }

    private boolean isCandyBricked(PokemonCardGame player) {
        for (int i = 0; i < player.prize.size(); i++) {
            if (player.prize.get(i) instanceof Trainer) {
                return false;
            }
        } return true;
    }
    
    // I got this method from https://springhow.com/java-write-csv/.
    public void writeCSVFile(String[][] matrixData, String fileName) throws IOException {
    	
    	File csvFile = new File(fileName);
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
    	writeCSVFile(mulligansData, "Mulligans Data.csv");
    }

    public void runRareCandiesSimulation() throws IOException {
    	String[][] rareCandiesData = pokemonRareCandiesSimulation();
    	writeCSVFile(rareCandiesData, "Rare Candies Data.csv");
    }
}