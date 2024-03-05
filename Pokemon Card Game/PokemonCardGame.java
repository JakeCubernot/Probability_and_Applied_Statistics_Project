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
import java.util.Scanner;

import Cards.Card;
import Cards.Caterpie;
import Cards.Charmander;
import Cards.FireEnergy;
import Cards.GrassEnergy;
import Cards.LightningEnergy;
import Cards.NestBall;
import Cards.Pikachu;
import Cards.Pokemon;
import Cards.ProfessorsResearch;
import Cards.SuperPotion;

public class PokemonCardGame {

	public ArrayList<Card> deck = new ArrayList<Card>();
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> bench = new ArrayList<Card>();
    public ArrayList<Card> active = new ArrayList<Card>();
    public ArrayList<Card> prize = new ArrayList<Card>();
    public ArrayList<Card> discard = new ArrayList<Card>();
    private Scanner playerInput = new Scanner(System.in);

    public void buildDeck(int pokemonCardCount, int energyCardCount, int trainerCardCount) {
    	for (int i = 0; i < pokemonCardCount / 4; i++) {
            deck.add(new Pikachu());
            deck.add(new Caterpie());
            deck.add(new Charmander());
        }
    	deck.add(new Pikachu());
        deck.add(new Caterpie());
        deck.add(new Charmander());
        deck.add(new Pikachu());
        deck.add(new Caterpie());
    	
        for (int i = 0; i < energyCardCount / 4; i++) {
            deck.add(new LightningEnergy());
            deck.add(new GrassEnergy());
            deck.add(new FireEnergy());
        }
        deck.add(new LightningEnergy());
        deck.add(new GrassEnergy());
        deck.add(new FireEnergy());
        deck.add(new LightningEnergy());
        deck.add(new GrassEnergy());
        
        for (int i = 0; i < trainerCardCount / 4; i++) {
        	deck.add(new ProfessorsResearch());
            deck.add(new NestBall());
            deck.add(new SuperPotion());
        }
        deck.add(new ProfessorsResearch());
        deck.add(new NestBall());
        deck.add(new SuperPotion());
        deck.add(new ProfessorsResearch());
        deck.add(new SuperPotion());
        
        System.out.println(deck.size());
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

    public void viewHand() {
		String handToString = "Player's Hand: [";
		for (int i = 0; i < hand.size(); i++) {
			if (i == hand.size() - 1) {
				handToString += (hand.get(i) + "]");
			} else {
				handToString += (hand.get(i).toString() + ", ");
			}
		}
		System.out.println(handToString);
	}

    public void viewBench() {
		String benchToString = "Player's Bench: [";
		for (int i = 0; i < bench.size(); i++) {
			if (i == hand.size() - 1) {
				benchToString += (bench.get(i) + "]");
			} else {
				benchToString += (bench.get(i).toString() + ", ");
			}
		}
		System.out.println(benchToString);
	}

    public void viewActive() {
        String activeToString = "Player's Active: [";
		for (int i = 0; i < bench.size(); i++) {
			if (i == hand.size() - 1) {
				activeToString += (bench.get(i) + "]");
			} else {
				activeToString += (bench.get(i).toString() + ", ");
			}
		}
		System.out.println(activeToString);
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
    
    public boolean player1FirstCoinFlip() {
        System.out.print("Heads or tails player 1?\nEnter 1 for heads and 2 for tails: ");
        int playerCoinChoice = playerInput.nextInt();
        Random rng = new Random();
        int coinFlipResult = rng.nextInt(2) + 1;
        System.out.println(coinFlipResult);
        if(coinFlipResult == 1) {
            System.out.println("\nThe coin is heads!");
        } else {
            System.out.println("\nThe coin is tails!");
        }
        boolean coinFlipWinner = false;
        switch (playerCoinChoice) {
            case 1:
                if (playerCoinChoice == coinFlipResult) {
                    System.out.println("\nPlayer 1 has won the coin flip. Player 1 can now choose who goes first!");
                    coinFlipWinner = true;
                } else {
                    System.out.println("\nPlayer 2 has won the coin flip. Player 2 can now choose who goes first!");
                    coinFlipWinner = false;
                }
                break;
        
            case 2:
                if (playerCoinChoice == coinFlipResult) {
                    System.out.println("\nPlayer 1 has won the coin flip. Player 1 can now choose who goes first!");
                    coinFlipWinner = true;
                } else {
                    System.out.println("\nPlayer 2 has won the coin flip. Player 2 can now choose who goes first!");
                    coinFlipWinner = false;
                }
                break;
        }
        if (coinFlipWinner) {
            System.out.print("\nPlayer 1, who do you want to go first?\nEnter 1 for player 1 and 2 for player 2: ");
            int playerTurnChoice = playerInput.nextInt();
            if (playerTurnChoice == 1) {
                System.out.println("Player 1 chooses themselves to go first.\n");
                coinFlipWinner = true;
            } else {
                System.out.println("Player 1 chooses player 2 to go first.\n");
                coinFlipWinner = false;
            }
        } else {
            if ((rng.nextInt(2) + 1) == 1) {
                System.out.println("Player 2 chooses player 1 to go first.\n");
                coinFlipWinner = true;
            } else {
                System.out.println("Player 2 chooses themselves to go first.\n");
                coinFlipWinner = false;
            }
        }
        return coinFlipWinner;
    }

    public void restartOpeningHand() {
        deck.addAll(hand);
        hand.clear();
        Collections.shuffle(deck);
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

    public void playerTurn() {
        if (active.isEmpty()) {
            viewHand();
            System.out.print("Place a basic Pokemon card in your Active.\nSelect the card in your hand from 1 to " + hand.size() + ": ");
            int playerInput = this.playerInput.nextInt() - 1;
            placePokemonInActive(playerInput);
        }
        if (bench.isEmpty()) {
            for(int i = 0; i < 5; i++) {
                viewHand();
                System.out.print("If any, add additional basic Pokemon cards to the Bench.\nSelect the card in your hand from 1 to " 
                + hand.size() + ".\nIf there is no remaining basic Pokemon cards, enter 0.\nYou have " + (5 - i) + " slots avalible on your Bench: ");
                int playerInput = this.playerInput.nextInt() - 1;
                if (playerInput == -1) {
                    break;
                } else {
                    bench.add(hand.get(playerInput));
                    hand.remove(playerInput);
                    viewBench();
                }
            }
        }
        boolean playingTurn = true;
        while (playingTurn) {
            viewHand();
            viewBench();
            viewActive();
            System.out.print("1. Edit Active\n2. Edit Hand\n3. End Turn\nChoose one of the options: ");
            int playerMenuChoice = playerInput.nextInt();
            switch (playerMenuChoice) {
                case 1:
                    boolean usingActiveMenu = true;
                    while (usingActiveMenu) {
                        viewHand();
                        viewActive();
                        System.out.print("1. Add Energy Card to Active Card\n2. Return to Previous Menu\nChoose one of the options: ");
                        int activeMenuChoice = playerInput.nextInt();
                        switch (activeMenuChoice) {
                            case 1:
                                viewHand();
                                viewActive();
                                System.out.print("Select an Energy card to add to the Active card.\nSelect the card in your hand from 1 to " 
                                + hand.size() + ": ");
                                int energyCardChoice = playerInput.nextInt() - 1;
                                active.add(hand.get(energyCardChoice));
                                hand.remove(energyCardChoice);
                                break;
                            case 2:
                                usingActiveMenu = false;
                                System.out.println("\nReturning to Previous Menu...");
                                break;
                        }
                    }

                    break;
                case 2:
                    break;
                case 3:
                    playingTurn = false;
                    break;
            }
        }
        System.out.println("\nSwitching to player 2's turn.");
    }

    public String[][] pokemonMulligansProbability() {
    	String[][] resultMatrix = new String[60][2];
    	resultMatrix[0][0] = "Number of Pokemon Cards in a Deck of 60";
    	resultMatrix[0][1] = "Chance (%) of Drawing a Pokemon Card in Opening Hand";
    	int testCount = 10000;
        for(int i = 1; i < 60; i++) {
        	int pokemonCardCount = 0;
        	for(int j = 1; j < testCount; j++) {
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