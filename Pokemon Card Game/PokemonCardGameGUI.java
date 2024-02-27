import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Panel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Label;
import javax.swing.JPanel;

/* 
 * Sources for this class:
 * https://eclipse.dev/windowbuilder/
 * https://eclipse.dev/windowbuilder/documentation.php
 * https://www.youtube.com/watch?v=tFwp589MAFk
 * https://pkmncards.com/
 */
public class PokemonCardGameGUI {

	// ================ VARIABLES ================
	private JFrame frame;
	private boolean coinFlipWinnerIsPlayer1;
	private boolean firstTurnIsPlayer1;
	private boolean activeCardReady = false;
	private boolean gameReady = false;
	
	private PokemonCardGame player1 = new PokemonCardGame();
	private PokemonCardGame player2 = new PokemonCardGame();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokemonCardGameGUI window = new PokemonCardGameGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokemonCardGameGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		// ================ PANELS ================
		Panel playGameMenuPanel = new Panel();
		playGameMenuPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(playGameMenuPanel, "name_232668382763100");
		playGameMenuPanel.setLayout(null);
		
		Panel coinTossPanel = new Panel();
		coinTossPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(coinTossPanel, "name_232842422180700");
		coinTossPanel.setLayout(null);
		
		Panel coinTossResultPanel = new Panel();
		coinTossResultPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(coinTossResultPanel, "name_233876974578700");
		coinTossResultPanel.setLayout(null);
		
		Panel playerTurnPanel = new Panel();
		playerTurnPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(playerTurnPanel, "name_238037374701900");
		playerTurnPanel.setLayout(null);
		
		Panel playerActivePanel = new Panel();
		playerActivePanel.setBounds(10, 364, 621, 78);
		playerTurnPanel.add(playerActivePanel);
		
		Panel playerBenchPanel = new Panel();
		playerBenchPanel.setBounds(10, 448, 621, 78);
		playerTurnPanel.add(playerBenchPanel);
		
		Panel playerHandPanel = new Panel();
		playerHandPanel.setBounds(10, 533, 621, 78);
		playerTurnPanel.add(playerHandPanel);
		
		Panel panel_1_1_1 = new Panel();
		panel_1_1_1.setBounds(90, 255, 461, 0);
		playerTurnPanel.add(panel_1_1_1);
		
		Panel enemyDeckPanel = new Panel();
		enemyDeckPanel.setBounds(694, 11, 299, 66);
		playerTurnPanel.add(enemyDeckPanel);
		
		Panel enemyBenchPanel = new Panel();
		enemyBenchPanel.setBounds(694, 88, 299, 66);
		playerTurnPanel.add(enemyBenchPanel);
		
		Panel enemyActivePanel = new Panel();
		enemyActivePanel.setBounds(398, 11, 286, 143);
		playerTurnPanel.add(enemyActivePanel);
		
		Panel player1OpeningTurnPanel = new Panel();
		player1OpeningTurnPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(player1OpeningTurnPanel, "name_238557460173400");
		player1OpeningTurnPanel.setLayout(null);
		
		Panel player1OpeningHandPanel = new Panel();
		player1OpeningHandPanel.setBackground(new Color(255, 255, 255));
		player1OpeningHandPanel.setBounds(10, 362, 983, 197);
		player1OpeningTurnPanel.add(player1OpeningHandPanel);
		
		Panel player1OpeningBenchPanel = new Panel();
		player1OpeningBenchPanel.setBackground(new Color(255, 255, 255));
		player1OpeningBenchPanel.setBounds(10, 184, 983, 169);
		player1OpeningTurnPanel.add(player1OpeningBenchPanel);
		
		Panel player1OpeningActivePanel = new Panel();
		player1OpeningActivePanel.setBackground(new Color(255, 255, 255));
		player1OpeningActivePanel.setBounds(10, 10, 983, 168);
		player1OpeningTurnPanel.add(player1OpeningActivePanel);
		
		Panel player2OpeningTurnPanel = new Panel();
		player2OpeningTurnPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(player2OpeningTurnPanel, "name_239054065389000");
		player2OpeningTurnPanel.setLayout(null);
		
		Panel player2OpeningHandPanel = new Panel();
		player2OpeningHandPanel.setBackground(new Color(255, 255, 255));
		player2OpeningHandPanel.setBounds(10, 359, 983, 190);
		player2OpeningTurnPanel.add(player2OpeningHandPanel);
		
		Panel player2OpeningBenchPanel = new Panel();
		player2OpeningBenchPanel.setBackground(Color.WHITE);
		player2OpeningBenchPanel.setBounds(10, 184, 983, 169);
		player2OpeningTurnPanel.add(player2OpeningBenchPanel);
		
		Panel player2OpeningActivePanel = new Panel();
		player2OpeningActivePanel.setBackground(Color.WHITE);
		player2OpeningActivePanel.setBounds(10, 10, 983, 168);
		player2OpeningTurnPanel.add(player2OpeningActivePanel);
		
		// ================ LABELS ================
		JLabel coinFlipLabel = new JLabel("<html><div style='text-align:center;'>Heads or tails?<br>"
				+ "Winner chooses who goes first!</div></html>");
		coinFlipLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		coinFlipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		coinFlipLabel.setBounds(330, 119, 303, 112);
		coinTossPanel.add(coinFlipLabel);
		
		JLabel gameTitleLabel = new JLabel("Pokemon Card Game");
		gameTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		gameTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleLabel.setBounds(246, 70, 477, 82);
		playGameMenuPanel.add(gameTitleLabel);
		
		Label coinTossResultLabel = new Label("coin toss result sample text");
		coinTossResultLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		coinTossResultLabel.setAlignment(Label.CENTER);
		coinTossResultLabel.setBounds(269, 69, 436, 54);
		coinTossResultPanel.add(coinTossResultLabel);
		
		Label coinTossWinnerLabel = new Label("coin toss winner sample text");
		coinTossWinnerLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		coinTossWinnerLabel.setAlignment(Label.CENTER);
		coinTossWinnerLabel.setBounds(27, 129, 926, 165);
		coinTossResultPanel.add(coinTossWinnerLabel);
		
		JLabel player1OpeningHandMenuLabel = new JLabel("opening hand menu label");
		player1OpeningHandMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		player1OpeningHandMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		player1OpeningHandMenuLabel.setBounds(10, 565, 824, 46);
		player1OpeningTurnPanel.add(player1OpeningHandMenuLabel);
		
		JLabel player2OpeningHandMenuLabel = new JLabel("opening hand menu label");
		player2OpeningHandMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		player2OpeningHandMenuLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		player2OpeningHandMenuLabel.setBounds(20, 555, 804, 56);
		player2OpeningTurnPanel.add(player2OpeningHandMenuLabel);
		
		JLabel playerTurnLabel = new JLabel("sample text");
		playerTurnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurnLabel.setBounds(727, 533, 225, 59);
		playerTurnPanel.add(playerTurnLabel);
		
		// ================ BUTTONS ================
		JButton btnNewButton = new JButton("Heads");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinTossPanel.setVisible(false);
				coinTossResultPanel.setVisible(true);
				coinFlipWinnerIsPlayer1 = player1PicksHeads(true, coinTossResultLabel, coinTossWinnerLabel);
			}
		});
		btnNewButton.setBounds(318, 242, 163, 76);
		coinTossPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tails");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinTossPanel.setVisible(false);
				coinTossResultPanel.setVisible(true);
				coinFlipWinnerIsPlayer1 = player1PicksHeads(false, coinTossResultLabel, coinTossWinnerLabel);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(482, 242, 163, 76);
		coinTossPanel.add(btnNewButton_1);
		
		Button playGameButton = new Button("Play Game");
		playGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameMenuPanel.setVisible(false);
				coinTossPanel.setVisible(true);
			}
		});
		playGameButton.setBounds(377, 188, 208, 67);
		playGameMenuPanel.add(playGameButton);
		
		Button exitGameButton = new Button("Exit");
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitGameButton.setBounds(377, 261, 208, 67);
		playGameMenuPanel.add(exitGameButton);
		
		Button button = new Button("Player 1 Goes First");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstTurnIsPlayer1 = player1First(true, coinFlipWinnerIsPlayer1);
				if (firstTurnIsPlayer1) {
					coinTossResultPanel.setVisible(false);
					player1OpeningTurnPanel.setVisible(true);
					openingHand(player1, player2);
					addCardsToHandPanel(player1OpeningHandPanel, player1OpeningActivePanel, player1OpeningBenchPanel, player1OpeningHandMenuLabel, player1);
				} else {
					coinTossResultPanel.setVisible(false);
					player2OpeningTurnPanel.setVisible(true);
					openingHand(player1, player2);
					addCardsToHandPanel(player2OpeningHandPanel, player2OpeningActivePanel, player2OpeningBenchPanel, player2OpeningHandMenuLabel, player2);
				}
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 18));
		button.setBounds(311, 327, 183, 54);
		coinTossResultPanel.add(button);
		
		Button button_1 = new Button("Player 2 Goes First");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstTurnIsPlayer1 = player1First(false, coinFlipWinnerIsPlayer1);
				if (firstTurnIsPlayer1) {
					coinTossResultPanel.setVisible(false);
					player1OpeningTurnPanel.setVisible(true);
					openingHand(player1, player2);
					addCardsToHandPanel(player1OpeningHandPanel, player1OpeningActivePanel, player1OpeningBenchPanel, player1OpeningHandMenuLabel, player1);
				} else {
					coinTossResultPanel.setVisible(false);
					player2OpeningTurnPanel.setVisible(true);
					openingHand(player1, player2);
					addCardsToHandPanel(player2OpeningHandPanel, player2OpeningActivePanel, player2OpeningBenchPanel, player2OpeningHandMenuLabel, player2);
				}
			}
		});
		button_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		button_1.setBounds(508, 327, 183, 54);
		coinTossResultPanel.add(button_1);
		
		JButton player1OpeningEndButton = new JButton("End Turn");
		player1OpeningEndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activeCardReady == true) {
					if (gameReady == false) {
						player1OpeningTurnPanel.setVisible(false);
						player2OpeningTurnPanel.setVisible(true);
						gameReady = true;
						activeCardReady = false;
						addCardsToHandPanel(player2OpeningHandPanel, player2OpeningActivePanel, player2OpeningBenchPanel, player2OpeningHandMenuLabel, player2);
					} else {
						System.out.println("Game Ready");
						player1OpeningTurnPanel.setVisible(false);
						playerTurnPanel.setVisible(true);
						playerTurn(player2, player1, playerActivePanel, playerBenchPanel, playerHandPanel, enemyActivePanel, enemyBenchPanel, enemyDeckPanel, playerTurnLabel);
					}
				} else {
					System.out.println("Active Not Set");
					player2OpeningTurnPanel.setVisible(true);
				}
			}
		});
		player1OpeningEndButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		player1OpeningEndButton.setBounds(836, 565, 157, 46);
		player1OpeningTurnPanel.add(player1OpeningEndButton);
		
		JButton player2OpeningEndButton = new JButton("End Turn");
		player2OpeningEndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activeCardReady == true) {
					if (gameReady == false) {
						player2OpeningTurnPanel.setVisible(false);
						player1OpeningTurnPanel.setVisible(true);
						gameReady = true;
						activeCardReady = false;
						addCardsToHandPanel(player1OpeningHandPanel, player1OpeningActivePanel, player1OpeningBenchPanel, player1OpeningHandMenuLabel, player1);
					} else {
						System.out.println("Game Ready");
						player2OpeningTurnPanel.setVisible(false);
						playerTurnPanel.setVisible(true);
						playerTurn(player1, player2, playerActivePanel, playerBenchPanel, playerHandPanel, enemyActivePanel, enemyBenchPanel, enemyDeckPanel, playerTurnLabel);
					}
				} else {
					System.out.println("Active Not Set");
				}
			}
		});
		player2OpeningEndButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		player2OpeningEndButton.setBounds(834, 555, 159, 56);
		player2OpeningTurnPanel.add(player2OpeningEndButton);
		
	}
	
	// ================ FUNCTIONS ================

	public void playerTurn(PokemonCardGame currentPlayer, PokemonCardGame enemyPlayer, Panel currentActivePanel, 
			Panel currentBenchPanel, Panel currentHandPanel, Panel enemyActivePanel, Panel enemyBenchPanel, Panel enemyHandPanel, JLabel playerTurnLabel) {
		
		addCardsToHandPanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer);
		addCardsToHandPanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer);
		
		addCardToBenchPanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer, 0);
		addCardToBenchPanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer, 0);
		
		addCardToActivePanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer, 0);
		addCardToActivePanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer, 0);
	}
	
	public void addCardToBenchPanel(Panel handPanel, Panel activePanel, Panel benchPanel, 
			JLabel instructionsText, PokemonCardGame player, int selectedCardIndex) {
		
		addCardsToHandPanel(handPanel, activePanel, benchPanel, instructionsText, player);
		addCardToActivePanel(handPanel, activePanel, benchPanel, instructionsText, player, selectedCardIndex);
		benchPanel.removeAll();
		
	    for (int i = 0; i < player.bench.size(); i++) {
	        Panel cardPanel = new Panel();
	        cardPanel.setName("Bench #" + i);
	        cardPanel.setPreferredSize(new Dimension(100, 150));
	        
	        JLabel cardLabel = new JLabel();
	        ImageIcon cardImage;
	        
	        String cardName = player.bench.get(i).getClass().toString();
	        switch (cardName) {
		        case "class Cards.Pikachu":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
		        case "class Cards.Caterpie":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Caterpie.jpg"));
		        	break;
		        case "class Cards.ProfessorsResearch":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Professor's Research.jpg"));
		        	break;
		        case "class Cards.NestBall":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Nest Ball.jpg"));
		        	break;
		        case "class Cards.GrassEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Grass Energy.jpg"));
		        	break;
		        case "class Cards.LightningEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Lightning Energy.jpg"));
		        	break;
		        case "class Cards.SuperPotion":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Super Potion.jpg"));
		        	break;
		        case "class Cards.FireEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Fire Energy.jpg"));
		        	break;
		        case "class Cards.Charmander":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Charmander.jpg"));
		        	break;
		        default:
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
	        }
	        
	        Image image = cardImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
	        ImageIcon scaledCardImage = new ImageIcon(image);
	        
	        cardLabel.setIcon(scaledCardImage);
	        cardLabel.setBounds(0, 0, 100, 150);
	        cardLabel.setVerticalAlignment(SwingConstants.TOP);
	        cardPanel.add(cardLabel);
	        benchPanel.add(cardPanel);
	        
	        cardPanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() - 5);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() + 5);
	            }
	        });
	    }
	    benchPanel.revalidate();
	    benchPanel.repaint();
	    
	    instructionsText.setText("Choose any other Pokemon to add to to your Bench!");
	}
	
	public void addCardToActivePanel(Panel handPanel, Panel activePanel, Panel benchPanel, JLabel instructionsText, PokemonCardGame player, int selectedCardIndex) {
		
		addCardsToHandPanel(handPanel, activePanel, benchPanel, instructionsText, player);
		activePanel.removeAll();
		
	    for (int i = 0; i < player.active.size(); i++) {
	    	
	        Panel cardPanel = new Panel();
	        cardPanel.setName("Active #" + i);
	        cardPanel.setPreferredSize(new Dimension(100, 150));
	        
	        JLabel cardLabel = new JLabel();
	        ImageIcon cardImage;
	        
	        String cardName = player.active.get(i).getClass().toString();
	        switch (cardName) {
		        case "class Cards.Pikachu":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
		        case "class Cards.Caterpie":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Caterpie.jpg"));
		        	break;
		        case "class Cards.ProfessorsResearch":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Professor's Research.jpg"));
		        	break;
		        case "class Cards.NestBall":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Nest Ball.jpg"));
		        	break;
		        case "class Cards.GrassEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Grass Energy.jpg"));
		        	break;
		        case "class Cards.LightningEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Lightning Energy.jpg"));
		        	break;
		        case "class Cards.SuperPotion":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Super Potion.jpg"));
		        	break;
		        case "class Cards.FireEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Fire Energy.jpg"));
		        	break;
		        case "class Cards.Charmander":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Charmander.jpg"));
		        	break;
		        default:
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
	        }
	        
	        Image image = cardImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
	        ImageIcon scaledCardImage = new ImageIcon(image);
	        
	        cardLabel.setIcon(scaledCardImage);
	        cardLabel.setBounds(0, 0, 100, 150);
	        cardLabel.setVerticalAlignment(SwingConstants.TOP);
	        cardPanel.add(cardLabel);
	        activePanel.add(cardPanel);
	        
	        activeCardReady = true;
	        
	        cardPanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() - 5);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() + 5);
	            }
	        });
	    }
	    activePanel.revalidate();
	    activePanel.repaint();
	    
	    instructionsText.setText("Choose any other Pokemon to add to to your Bench!");
	}
	
	public void addCardsToHandPanel(Panel handPanel, Panel activePanel, Panel benchPanel, JLabel instructionsText, PokemonCardGame player) {
	    handPanel.removeAll();
	    
	    for (int i = 0; i < player.hand.size(); i++) {
	        Panel cardPanel = new Panel();
	        cardPanel.setName("Hand #" + i);
	        cardPanel.setPreferredSize(new Dimension(100, 150));
	        
	        JLabel cardLabel = new JLabel();
	        ImageIcon cardImage;
	        
	        String cardName = player.hand.get(i).getClass().toString();
	        switch (cardName) {
		        case "class Cards.Pikachu":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
		        case "class Cards.Caterpie":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Caterpie.jpg"));
		        	break;
		        case "class Cards.ProfessorsResearch":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Professor's Research.jpg"));
		        	break;
		        case "class Cards.NestBall":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Nest Ball.jpg"));
		        	break;
		        case "class Cards.GrassEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Grass Energy.jpg"));
		        	break;
		        case "class Cards.LightningEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Lightning Energy.jpg"));
		        	break;
		        case "class Cards.SuperPotion":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Super Potion.jpg"));
		        	break;
		        case "class Cards.FireEnergy":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Fire Energy.jpg"));
		        	break;
		        case "class Cards.Charmander":
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Charmander.jpg"));
		        	break;
		        default:
		        	cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
		        	break;
	        }
	        
	        Image image = cardImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
	        ImageIcon scaledCardImage = new ImageIcon(image);
	        
	        cardLabel.setIcon(scaledCardImage);
	        cardLabel.setBounds(0, 0, 100, 150);
	        cardLabel.setVerticalAlignment(SwingConstants.TOP);
	        cardPanel.add(cardLabel);
	        handPanel.add(cardPanel);
	        
	        instructionsText.setText("Choose a Pokemon to add to to your Active!");
	        
	        cardPanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	int selectedCardIndex = e.getSource().toString().charAt(21) - 48;
	            	if (player.active.size() == 0) {
		            	if ((player.hand.get(selectedCardIndex).getClass().getSuperclass().toString()).compareTo("class Cards.Pokemon") == 0) {
		            		player.active.add(player.hand.get(selectedCardIndex));
		            		player.hand.remove(selectedCardIndex);
		            		addCardToActivePanel(handPanel, activePanel, benchPanel, instructionsText, player, selectedCardIndex);
		            	}
		            } else {
		            	if ((player.hand.get(selectedCardIndex).getClass().getSuperclass().toString()).compareTo("class Cards.Pokemon") == 0) {
		            		player.bench.add(player.hand.get(selectedCardIndex));
		            		player.hand.remove(selectedCardIndex);
		            		addCardToBenchPanel(handPanel, activePanel, benchPanel, instructionsText, player, selectedCardIndex);
		            	}
	            	}
	            }
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() - 5);
	            }
	            
	            @Override
	            public void mouseExited(MouseEvent e) {
	                cardPanel.setLocation(cardPanel.getX(), cardPanel.getY() + 5);
	            }
	        });
	    }
	    handPanel.revalidate();
	    handPanel.repaint();
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
	
	public boolean player1PicksHeads(boolean playerCoinChoice, Label coinFlipResultLabel, Label coinFlipWinnerLabel) {
        Random rng = new Random();
        int coinFlipResult = rng.nextInt(2) + 1;
        boolean coinIsHeads;
        if(coinFlipResult == 1) {
        	coinFlipResultLabel.setText("The coin is heads!");
            coinIsHeads = true;
        } else {
        	coinFlipResultLabel.setText("The coin is tails!");
            coinIsHeads = false;
        }
        
        boolean coinFlipWinnerIsPlayer1 = false;
        if (playerCoinChoice) {
            if (playerCoinChoice == coinIsHeads) {
                coinFlipWinnerLabel.setText("Player 1 has won the coin flip. Player 1 can now choose who goes first! "
                		+ "Player 1, who do you want to go first?");
                coinFlipWinnerIsPlayer1 = true;
            } else {
                coinFlipWinnerLabel.setText("Player 2 has won the coin flip. Player 2 can now choose who goes first! "
                		+ "Player 2, who do you want to go first?");
                coinFlipWinnerIsPlayer1 = false;
            }
        } else {
            if (playerCoinChoice == coinIsHeads) {
                coinFlipWinnerLabel.setText("Player 1 has won the coin flip. Player 1 can now choose who goes first! "
                		+ "Player 1, who do you want to go first?");
                coinFlipWinnerIsPlayer1 = true;
            } else {
                coinFlipWinnerLabel.setText("Player 2 has won the coin flip. Player 2 can now choose who goes first! "
                		+ "Player 2, who do you want to go first?");
                coinFlipWinnerIsPlayer1 = false;
            }
        }
        return coinFlipWinnerIsPlayer1;
    }
	
	public boolean player1First(boolean playerFirstTurnChoice, boolean coinFlipWinnerIsPlayer1) {
		boolean player1GoesFirst;
		if (coinFlipWinnerIsPlayer1) {
            if (playerFirstTurnChoice == true) {
                player1GoesFirst = true;
            } else {
                player1GoesFirst = false;
            }
        } else {
            if (playerFirstTurnChoice == true) {
                player1GoesFirst = true;
            } else {
                player1GoesFirst = false;
            }
        }
		return player1GoesFirst;
	}
}
