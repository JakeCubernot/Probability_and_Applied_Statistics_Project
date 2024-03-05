import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.SwingConstants;

import Cards.Card;
import Cards.Energy;
import Cards.Pokemon;
import Cards.Trainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.GridLayout;

/* 
 * Sources for this class:
 * https://eclipse.dev/windowbuilder/
 * https://eclipse.dev/windowbuilder/documentation.php
 * http://www.java2s.com/Tutorial/Java/0260__Swing-Event/Rightclicktotriggerthepopupmenu.htm
 * https://www.youtube.com/watch?v=tFwp589MAFk
 * https://pkmncards.com/
 */
public class PokemonCardGameGUI extends PokemonCardGame {

	// ================ VARIABLES ================
	private JFrame frame;
	private boolean coinFlipWinnerIsPlayer1;
	private boolean firstTurnIsPlayer1;
	private boolean activeCardReady = false;
	private boolean firstPlayerReady = false;
	private boolean gameStarted = false;
	private boolean playerHasAlreadyAttacked = false;
	private boolean firstRoundHasOccured = false;
	
	private PokemonCardGame player1 = new PokemonCardGame();
	private PokemonCardGame player2 = new PokemonCardGame();
	
	private Pokemon player1ActivePokemon;
	private Pokemon player2ActivePokemon;
	
	private PokemonCardGame nestPlayer;
	
	// ================ MENU FEATURES ================
	private JMenu addEnergyMenu;
	private JMenu attackMenu;
	private JMenu useTrainerMenu;
	private JPanel nestBallPanel;
	private JLabel yourPrizeCardLabel;
	private JLabel enemyPrizeCardLabel;
	private JLabel enemyParalzedLabel;
	private JLabel enemyHealthLabel;
	private JLabel yourHealthLabel;
	private JLabel yourParalzedLabel;
	private JPanel player1WinnerPanel;
	private JPanel player2WinnerPanel;
	private Panel playerTurnPanel;
	
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
		
		// ================ PANELS AND JPANELS ================
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
		
		playerTurnPanel = new Panel();
		playerTurnPanel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(playerTurnPanel, "name_238037374701900");
		playerTurnPanel.setLayout(null);
		
		Panel playerActivePanel = new Panel();
		playerActivePanel.setBackground(new Color(255, 255, 255));
		playerActivePanel.setBounds(10, 343, 983, 78);
		playerTurnPanel.add(playerActivePanel);
		
		Panel playerBenchPanel = new Panel();
		playerBenchPanel.setBackground(new Color(255, 255, 255));
		playerBenchPanel.setBounds(10, 427, 983, 78);
		playerTurnPanel.add(playerBenchPanel);
		
		Panel playerHandPanel = new Panel();
		playerHandPanel.setBackground(new Color(255, 255, 255));
		playerHandPanel.setBounds(10, 512, 983, 78);
		playerTurnPanel.add(playerHandPanel);
		
		Panel enemyDeckPanel = new Panel();
		enemyDeckPanel.setBackground(new Color(255, 255, 255));
		enemyDeckPanel.setBounds(444, 11, 549, 66);
		playerTurnPanel.add(enemyDeckPanel);
		
		Panel enemyBenchPanel = new Panel();
		enemyBenchPanel.setBackground(new Color(255, 255, 255));
		enemyBenchPanel.setBounds(444, 88, 549, 66);
		playerTurnPanel.add(enemyBenchPanel);
		
		Panel enemyActivePanel = new Panel();
		enemyActivePanel.setBackground(new Color(255, 255, 255));
		enemyActivePanel.setBounds(152, 11, 286, 143);
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
		
		JPanel pokemonStatusBoard = new JPanel();
		pokemonStatusBoard.setBackground(new Color(255, 255, 255));
		pokemonStatusBoard.setBounds(10, 160, 219, 177);
		playerTurnPanel.add(pokemonStatusBoard);
		pokemonStatusBoard.setLayout(new GridLayout(6, 0, 0, 0));
		
		player1WinnerPanel = new JPanel();
		frame.getContentPane().add(player1WinnerPanel, "name_879322685136000");
		player1WinnerPanel.setLayout(null);
		
		player2WinnerPanel = new JPanel();
		frame.getContentPane().add(player2WinnerPanel, "name_879327298943400");
		player2WinnerPanel.setLayout(null);
		
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
		playerTurnLabel.setBounds(983, 603, 20, 0);
		playerTurnPanel.add(playerTurnLabel);
		
		nestBallPanel = new JPanel();
		nestBallPanel.setBounds(239, 160, 459, 177);
		playerTurnPanel.add(nestBallPanel);
		nestBallPanel.setLayout(null);
		nestBallPanel.setVisible(false);
		
		JLabel nestBallTextLabel = new JLabel("Choose a Pokemon from your deck to add to your Bench:");
		nestBallTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nestBallTextLabel.setBounds(0, 0, 459, 35);
		nestBallPanel.add(nestBallTextLabel);
		
		enemyPrizeCardLabel = new JLabel("Prize Cards Remaining for Enemy: *****");
		enemyPrizeCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(enemyPrizeCardLabel);
		
		enemyParalzedLabel = new JLabel("Enemy's Pokemon Is Paralyzed: *****");
		enemyParalzedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(enemyParalzedLabel);
		
		enemyHealthLabel = new JLabel("Enemy's Pokemon's Health: *****");
		enemyHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(enemyHealthLabel);
		
		yourHealthLabel = new JLabel("Your Pokemon's Health: *****");
		yourHealthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(yourHealthLabel);
		
		yourParalzedLabel = new JLabel("Your Pokemon Is Paralyzed: *****");
		yourParalzedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(yourParalzedLabel);
		
		yourPrizeCardLabel = new JLabel("Prize Cards Remaining for You: *****");
		yourPrizeCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pokemonStatusBoard.add(yourPrizeCardLabel);
		
		JLabel player1WonLabel = new JLabel("Player 1 Has Won!");
		player1WonLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		player1WonLabel.setBounds(10, 11, 983, 578);
		player1WonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		player1WinnerPanel.add(player1WonLabel);
		
		JLabel player2WonLabel = new JLabel("Player 2 Has Won!");
		player2WonLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		player2WonLabel.setBounds(10, 11, 983, 578);
		player2WonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		player2WinnerPanel.add(player2WonLabel);
		
		// ================ BUTTONS ================
		JButton btnNewButton = new JButton("Heads");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinTossPanel.setVisible(false);
				coinTossResultPanel.setVisible(true);
				coinFlipWinnerIsPlayer1 = player1PicksHeadsGUI(true, coinTossResultLabel, coinTossWinnerLabel);
			}
		});
		btnNewButton.setBounds(318, 242, 163, 76);
		coinTossPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tails");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coinTossPanel.setVisible(false);
				coinTossResultPanel.setVisible(true);
				coinFlipWinnerIsPlayer1 = player1PicksHeadsGUI(false, coinTossResultLabel, coinTossWinnerLabel);
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
					if (firstPlayerReady == false) {
						player1OpeningTurnPanel.setVisible(false);
						player2OpeningTurnPanel.setVisible(true);
						firstPlayerReady = true;
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
					if (firstPlayerReady == false) {
						player2OpeningTurnPanel.setVisible(false);
						player1OpeningTurnPanel.setVisible(true);
						firstPlayerReady = true;
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
		
		JButton researchPikachuButton = new JButton("Add Pikachu");
		researchPikachuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < nestPlayer.deck.size(); i++) {
					if (nestPlayer.deck.get(i).getName().equals("Pikachu")) {
						nestPlayer.bench.add(nestPlayer.deck.get(i));
						nestPlayer.deck.remove(i);
						break;
					}
				}
				nestBallPanel.setVisible(false);
				addCardToBenchPanel(playerHandPanel, playerActivePanel, playerBenchPanel, playerTurnLabel, nestPlayer, 0);
			}
		});
		researchPikachuButton.setBounds(10, 46, 119, 120);
		nestBallPanel.add(researchPikachuButton);
		
		JButton researchCaterpieButton = new JButton("Add Caterpie");
		researchCaterpieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < nestPlayer.deck.size(); i++) {
					if (nestPlayer.deck.get(i).getName().equals("Caterpie")) {
						nestPlayer.bench.add(nestPlayer.deck.get(i));
						nestPlayer.deck.remove(i);
						break;
					}
				}
				nestBallPanel.setVisible(false);
				addCardToBenchPanel(playerHandPanel, playerActivePanel, playerBenchPanel, playerTurnLabel, nestPlayer, 0);
			}
		});
		researchCaterpieButton.setBounds(151, 46, 119, 120);
		nestBallPanel.add(researchCaterpieButton);
		
		JButton researchCharmanderButton = new JButton("Add Charmander");
		researchCharmanderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < nestPlayer.deck.size(); i++) {
					if (nestPlayer.deck.get(i).getName().equals("Charmander")) {
						nestPlayer.bench.add(nestPlayer.deck.get(i));
						nestPlayer.deck.remove(i);
						break;
					}
				}
				nestBallPanel.setVisible(false);
				addCardToBenchPanel(playerHandPanel, playerActivePanel, playerBenchPanel, playerTurnLabel, nestPlayer, 0);
			}
		});
		researchCharmanderButton.setBounds(295, 46, 143, 120);
		nestBallPanel.add(researchCharmanderButton);
		
		JButton endTurnGameBoardButton = new JButton("End Turn");
		endTurnGameBoardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerHasAlreadyAttacked = false;
				if(nestPlayer == player1) {
					player1ActivePokemon.setIsParalyzed(false);
					if (firstRoundHasOccured) {
						player2.hand.add(player2.deck.get(0));
						player2.deck.remove(0);
					}
					playerTurn(player2, player1, playerActivePanel, playerBenchPanel, playerHandPanel, 
							enemyActivePanel, enemyBenchPanel, enemyDeckPanel, playerTurnLabel);
					checkIfWinner();
					addCardToActivePanel(playerHandPanel, playerActivePanel, playerBenchPanel, playerTurnLabel, player2, 0);
					firstRoundHasOccured = true;
				} else {
					player2ActivePokemon.setIsParalyzed(false);
					if (firstRoundHasOccured) {
						System.out.println("adding 1 card");
						player1.hand.add(player1.deck.get(0));
						player1.deck.remove(0);
					}
					playerTurn(player1, player2, playerActivePanel, playerBenchPanel, playerHandPanel, 
							enemyActivePanel, enemyBenchPanel, enemyDeckPanel, playerTurnLabel);
					checkIfWinner();
					addCardToActivePanel(playerHandPanel, playerActivePanel, playerBenchPanel, playerTurnLabel, player1, 0);
					firstRoundHasOccured = true;
				}
			}
		});
		endTurnGameBoardButton.setBounds(830, 216, 163, 66);
		playerTurnPanel.add(endTurnGameBoardButton);
		
		// ================ MENU ================
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		frame.setJMenuBar(menuBar);
		
		JMenu activeMenu = new JMenu("Active");
		menuBar.add(activeMenu);
		
		JMenu handMenu = new JMenu("Hand");
		menuBar.add(handMenu);
		
		useTrainerMenu = new JMenu("Use Trainer Card");
		handMenu.add(useTrainerMenu);
		
		addEnergyMenu = new JMenu("Add Energy To Active");
		activeMenu.add(addEnergyMenu);
		
		attackMenu = new JMenu("Attack");
		activeMenu.add(attackMenu);
		
		JMenu viewCardsMenu = new JMenu("View Cards");
		menuBar.add(viewCardsMenu);
		
		JMenu pokemonCardsMenu = new JMenu("Pokemon Cards");
		viewCardsMenu.add(pokemonCardsMenu);
		
		JMenu trainerCardsMenu = new JMenu("Trainer Cards");
		viewCardsMenu.add(trainerCardsMenu);
		
		JMenu energyCardsMenu = new JMenu("Energy Cards");
		viewCardsMenu.add(energyCardsMenu);
		
		JMenu pikachuCardItem = new JMenu("Pikachu");
		pokemonCardsMenu.add(pikachuCardItem);
		
		// ================ VIEW CARD IMAGE LABELS ================
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Pikachu.jpg"));
		pikachuCardItem.add(lblNewLabel);
		JMenu caterpieCardItem = new JMenu("Caterpie");
		pokemonCardsMenu.add(caterpieCardItem);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Caterpie.jpg"));
		caterpieCardItem.add(lblNewLabel_3);
		JMenu charmanderCardItem = new JMenu("Charmander");
		pokemonCardsMenu.add(charmanderCardItem);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Charmander.jpg"));
		charmanderCardItem.add(lblNewLabel_2);
		
		JMenu nestBallCardItem = new JMenu("Nest Ball");
		trainerCardsMenu.add(nestBallCardItem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Nest Ball.jpg"));
		nestBallCardItem.add(lblNewLabel_1);
		JMenu professorsResearchCardItem = new JMenu("Professor's Research");
		trainerCardsMenu.add(professorsResearchCardItem);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Professor's Research.jpg"));
		professorsResearchCardItem.add(lblNewLabel_4);
		JMenu superPotionCardItem = new JMenu("Super Potion");
		trainerCardsMenu.add(superPotionCardItem);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Super Potion.jpg"));
		superPotionCardItem.add(lblNewLabel_5);
		
		JMenu lightningCardItem = new JMenu("Lightning");
		energyCardsMenu.add(lightningCardItem);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Lightning Energy.jpg"));
		lightningCardItem.add(lblNewLabel_6);
		JMenu grassCardItem = new JMenu("Grass");
		energyCardsMenu.add(grassCardItem);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Grass Energy.jpg"));
		grassCardItem.add(lblNewLabel_7);
		JMenu fireCardItem = new JMenu("Fire");
		energyCardsMenu.add(fireCardItem);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("F:\\Probability_and_Applied_Statistics_Project\\Pokemon Card Game\\Card Images\\Fire Energy.jpg"));
		fireCardItem.add(lblNewLabel_8);
		
	}
	
	// ================ FUNCTIONS ================

	public void playerTurn(PokemonCardGame currentPlayer, PokemonCardGame enemyPlayer, Panel currentActivePanel, 
			Panel currentBenchPanel, Panel currentHandPanel, Panel enemyActivePanel, Panel enemyBenchPanel, 
			Panel enemyHandPanel, JLabel playerTurnLabel) {
		gameStarted = true;
		updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, currentBenchPanel, currentHandPanel, 
				enemyActivePanel, enemyBenchPanel, enemyHandPanel, playerTurnLabel);
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
	        
	        Card cardName = player.bench.get(i);
	        
	        cardLabel.setIcon(setCardImage(cardName));
	        cardLabel.setBounds(0, 0, 100, 150);
	        cardLabel.setVerticalAlignment(SwingConstants.TOP);
	        cardPanel.add(cardLabel);
	        benchPanel.add(cardPanel);
	        
	        int cardIndex = i;
	        cardPanel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if (player.active.size() == 0) {
	            		player.active.add(player.bench.get(cardIndex));
	            		player.bench.remove(cardIndex);
	            		addCardToActivePanel(handPanel, activePanel, benchPanel, instructionsText, player, cardIndex);
	            		addCardToBenchPanel(handPanel, activePanel, benchPanel, instructionsText, player, selectedCardIndex);
	            	}
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
	        if (i == 0) {
	        	cardPanel.setPreferredSize(new Dimension(100, 150));
	        } else {
	        	cardPanel.setPreferredSize(new Dimension(50, 100));
	        }
	        
	        JLabel cardLabel = new JLabel();
	        
	        Card cardName = player.active.get(i);
	        
	        cardLabel.setIcon(setCardImage(cardName));
	        if (i == 0) {
	        	cardLabel.setBounds(0, 0, 100, 150);
	        } else {
	        	cardLabel.setBounds(0, 0, 50, 100);
	        }
	        cardLabel.setVerticalAlignment(SwingConstants.TOP);
	        cardPanel.add(cardLabel);
	        activePanel.add(cardPanel);
	        activeCardReady = true;
	        
	        
	    }
	    activePanel.revalidate();
	    activePanel.repaint();
	    
	    instructionsText.setText("");
	}
	
	public void addCardsToHandPanel(Panel handPanel, Panel activePanel, Panel benchPanel, JLabel instructionsText, PokemonCardGame player) {
	    handPanel.removeAll();
	    
	    for (int i = 0; i < player.hand.size(); i++) {
	        Panel cardPanel = new Panel();
	        cardPanel.setName("Hand #" + i);
	        cardPanel.setPreferredSize(new Dimension(100, 150));
	        
	        JLabel cardLabel = new JLabel();
	        
	        Card cardName = player.hand.get(i);
	        
	        cardLabel.setIcon(setCardImage(cardName));
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
	        });
	        if (!gameStarted) {
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
	    }
	    handPanel.revalidate();
	    handPanel.repaint();
	}
	
	public boolean player1PicksHeadsGUI(boolean playerCoinChoice, Label coinFlipResultLabel, Label coinFlipWinnerLabel) {
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
	
	public boolean activePokemonFainted() {
		boolean pokemonFainted = false;
		if(player1ActivePokemon.getHP() < 1) {
			player2ActivePokemon.setHP(1);
			player1.discard.addAll(player1.active);
			player1.active.clear();
			player2.hand.add(player2.prize.get(0));
			player2.prize.remove(0);
			pokemonFainted = true;
		}
		if(player2ActivePokemon.getHP() < 1) {
			player2ActivePokemon.setHP(1);
			player2.discard.addAll(player2.active);
			player2.active.clear();
			player1.hand.add(player2.prize.get(0));
			player1.prize.remove(0);
			pokemonFainted = true;
		}
		return pokemonFainted;
	}
	
	public void checkIfWinner() {
	    if (activePokemonFainted()) {
	        if (player1.bench.isEmpty() && !hasPokemonInHand(player1)) {
	            System.out.println("Player 2 Won");
	            playerTurnPanel.setVisible(false);
	            player2WinnerPanel.setVisible(true);
	        }
	        if (player2.bench.isEmpty() && !hasPokemonInHand(player2)) {
	            System.out.println("Player 1 Won");
	            playerTurnPanel.setVisible(false);
	            player1WinnerPanel.setVisible(true);
	        }
	    }
	    if (player1.prize.size() == 0) {
	    	System.out.println("Player 1 Won");
	    	playerTurnPanel.setVisible(false);
	    	player1WinnerPanel.setVisible(true);
	    }
	    if (player2.prize.size() == 0) {
	    	System.out.println("Player 2 Won");
	    	playerTurnPanel.setVisible(false);
	    	player2WinnerPanel.setVisible(true);
	    }
	    if (player1.deck.size() == 0) {
	    	System.out.println("Player 2 Won");
	    	playerTurnPanel.setVisible(false);
	    	player2WinnerPanel.setVisible(true);
	    }
	    if (player2.deck.size() == 0) {
	    	System.out.println("Player 1 Won");
	    	playerTurnPanel.setVisible(false);
	    	player1WinnerPanel.setVisible(true);
	    }
	}

	private boolean hasPokemonInHand(PokemonCardGame player) {
	    for (int i = 0; i < player.hand.size(); i++) {
	        if (player.hand.get(i) instanceof Pokemon) {
	            return true;
	        }
	    }
	    return false;
	}

	
	private ImageIcon setCardImage(Card card) {
	    ImageIcon cardImage;
	    String imagePath = card.getCardImage();
	    
	    if (imagePath != null && !imagePath.isEmpty()) {
	        cardImage = new ImageIcon(getClass().getResource(imagePath));
	    } else {
	        cardImage = new ImageIcon(getClass().getResource("/Card Images/Pikachu.jpg"));
	    }
	    
	    Image image = cardImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
	    ImageIcon scaledCardImage = new ImageIcon(image);
	    return scaledCardImage;
	}
	
	private void updateMenuBar(PokemonCardGame currentPlayer, PokemonCardGame enemyPlayer, Panel currentActivePanel, 
	        Panel currentBenchPanel, Panel currentHandPanel, Panel enemyActivePanel, Panel enemyBenchPanel, 
	        Panel enemyHandPanel, JLabel playerTurnLabel) {
		if (gameStarted) {
        	addEnergyMenu.removeAll();
        	useTrainerMenu.removeAll();
        	attackMenu.removeAll();
        	
        	Pokemon currentPlayerActivePokemon;
        	Pokemon enemyPlayerActivePokemon;
        	if (currentPlayer == player1) {
        		currentPlayerActivePokemon = player1ActivePokemon;
        		enemyPlayerActivePokemon = player2ActivePokemon;
        	} else {
        		currentPlayerActivePokemon = player2ActivePokemon;
        		enemyPlayerActivePokemon = player1ActivePokemon;
        	}
        	
        	if (!playerHasAlreadyAttacked && !currentPlayerActivePokemon.getIsParalyzed()) {
            	for (int i = 0; i < currentPlayerActivePokemon.getNumberOfAttacks(); i++) {
        			JMenuItem pokemonAttack = new JMenuItem(currentPlayerActivePokemon.getAttackNames()[i]);
        			int attackIndex = i;
                    pokemonAttack.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	playerHasAlreadyAttacked = true;
                        	if (attackIndex == 0) {
                        		currentPlayerActivePokemon.attackOne(enemyPlayerActivePokemon);
                        	} else {
                        		if (currentPlayerActivePokemon.getAttackNames()[1] == "Ember") {
                        			currentPlayerActivePokemon.attackTwo(enemyPlayerActivePokemon);
                        			currentPlayer.discard.add(currentPlayer.active.get(1));
                        			currentPlayer.active.remove(currentPlayer.active.get(1));
                        		} else {
                        			currentPlayerActivePokemon.attackTwo(enemyPlayerActivePokemon);
                        		}
                        	}
                        	updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, 
                        			currentBenchPanel, currentHandPanel, enemyActivePanel, enemyBenchPanel, 
                        			enemyHandPanel, playerTurnLabel);
                        }
                    });
                    attackMenu.add(pokemonAttack);
        		}
        	}
        	
            for (int i = 0; i < currentPlayer.hand.size(); i++) {
                if (currentPlayer.hand.get(i) instanceof Energy) {
                    JMenuItem energyCard = new JMenuItem("Energy card at index " + (i + 1));
                    int energyIndex = i;
                    energyCard.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	currentPlayer.active.add(currentPlayer.hand.get(energyIndex));
                        	currentPlayer.hand.remove(energyIndex);
                        	updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, 
                        			currentBenchPanel, currentHandPanel, enemyActivePanel, enemyBenchPanel, 
                        			enemyHandPanel, playerTurnLabel);
                        }
                    });
                    addEnergyMenu.add(energyCard);
                }
                if (currentPlayer.hand.get(i) instanceof Trainer) {
	                JMenuItem trainerCard = new JMenuItem("Trainer card at index " + (i + 1));
	                int trainerIndex = i;
	                if (currentPlayer.hand.get(i).getName().equals("Nest Ball")) {
	                    trainerCard.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            useNestBall(currentPlayer, trainerIndex);
	                            updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, 
	                                            currentBenchPanel, currentHandPanel, enemyActivePanel, enemyBenchPanel, 
	                                            enemyHandPanel, playerTurnLabel);
	                        }
	                    });
	                } else if (currentPlayer.hand.get(i).getName().equals("Professor's Research")) {
	                    trainerCard.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            useProfessorsResearch(currentPlayer, trainerIndex);
	                            updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, 
	                                            currentBenchPanel, currentHandPanel, enemyActivePanel, enemyBenchPanel, 
	                                            enemyHandPanel, playerTurnLabel);
	                        }
	                    });
	                } else {
	                    trainerCard.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            useSuperPotion(currentPlayer, trainerIndex);
	                            updateGameBoard(currentPlayer, enemyPlayer, currentActivePanel, 
	                                            currentBenchPanel, currentHandPanel, enemyActivePanel, enemyBenchPanel, 
	                                            enemyHandPanel, playerTurnLabel);
	                        }
	                    });
	                }

	                useTrainerMenu.add(trainerCard);
                }
            }
        }
	}
	
	private void updateGameBoard(PokemonCardGame currentPlayer, PokemonCardGame enemyPlayer, Panel currentActivePanel, 
	        Panel currentBenchPanel, Panel currentHandPanel, Panel enemyActivePanel, Panel enemyBenchPanel, 
	        Panel enemyHandPanel, JLabel playerTurnLabel) {
		nestPlayer = currentPlayer;
		
		if (currentPlayer == player1) {
			player1ActivePokemon = (Pokemon) currentPlayer.active.get(0);
			player2ActivePokemon = (Pokemon) enemyPlayer.active.get(0);
			yourPrizeCardLabel.setText("Prize Cards Remaining for You: " + player1.prize.size());
			enemyPrizeCardLabel.setText("Prize Cards Remaining for Enemy: " + player2.prize.size());
			yourParalzedLabel.setText("Your Pokemon Is Paralyzed: " + player1ActivePokemon.getIsParalyzed());
			enemyParalzedLabel.setText("Enemy's Pokemon Is Paralyzed: " + player2ActivePokemon.getIsParalyzed());
			yourHealthLabel.setText("Your Pokemon's Health: " + player1ActivePokemon.getHP());
			enemyHealthLabel.setText("Enemy's Pokemon's Health: " + player2ActivePokemon.getHP());
		} else {
			player2ActivePokemon = (Pokemon) currentPlayer.active.get(0);
			player1ActivePokemon = (Pokemon) enemyPlayer.active.get(0);
			yourPrizeCardLabel.setText("Prize Cards Remaining for You: " + player2.prize.size());
			enemyPrizeCardLabel.setText("Prize Cards Remaining for Enemy: " + player1.prize.size());
			yourParalzedLabel.setText("Your Pokemon Is Paralyzed: " + player2ActivePokemon.getIsParalyzed());
			enemyParalzedLabel.setText("Enemy's Pokemon Is Paralyzed: " + player1ActivePokemon.getIsParalyzed());
			yourHealthLabel.setText("Your Pokemon's Health: " + player2ActivePokemon.getHP());
			enemyHealthLabel.setText("Enemy's Pokemon's Health: " + player1ActivePokemon.getHP());
		}
		
		updateMenuBar(currentPlayer, enemyPlayer, currentActivePanel, currentBenchPanel, currentHandPanel, enemyActivePanel,
				enemyBenchPanel, enemyHandPanel, playerTurnLabel);
	    
	    addCardsToHandPanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer);
	    addCardsToHandPanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer);
	    
	    addCardToBenchPanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer, 0);
	    addCardToBenchPanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer, 0);
	    
	    addCardToActivePanel(currentHandPanel, currentActivePanel, currentBenchPanel, playerTurnLabel, currentPlayer, 0);
	    addCardToActivePanel(enemyHandPanel, enemyActivePanel, enemyBenchPanel, playerTurnLabel, enemyPlayer, 0);
	}
	
	// ================ TRAINER CARD ABILITIES ================
	private void useNestBall(PokemonCardGame currentPlayer, int trainerIndex) {
		nestBallPanel.setVisible(true);
		currentPlayer.hand.remove(trainerIndex);
	}
	
	private void useProfessorsResearch(PokemonCardGame currentPlayer, int trainerIndex) {
		currentPlayer.discard.addAll(hand);
    	currentPlayer.hand.clear();
    	for (int i = 0; i < 7; i++) {
            currentPlayer.hand.add(currentPlayer.deck.get(i));
        }
	}
	
	private void useSuperPotion(PokemonCardGame currentPlayer, int trainerIndex) {
		currentPlayer.discard.add(currentPlayer.hand.get(trainerIndex));
    	currentPlayer.hand.remove(trainerIndex);
		if (currentPlayer == player1) {
			player1ActivePokemon.setHP(player1ActivePokemon.getHP() + 30);
		} else {
			player2ActivePokemon.setHP(player2ActivePokemon.getHP() + 30);
		}
	}
}
