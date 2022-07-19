/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Game class will create a game of one dealer, two players, and a starter pile.
The players will take turns placing cards until neither player can place a card or one player runs out of cards.
The winner will earn points based on the number of cards in the losing player's hand.
No points will be rewarded in a draw. A draw is when neither can play a card and they have the same number of remaining cards
*/

import java.util.LinkedList;
public class Game {

  // MEMBER VARIABLES
  private Dealer m_dealer;
  private Player m_player1;
  private Player m_player2;
  private LinkedList<Card> m_starterPile;

  // Statistics of the Game
  // static member variables for easy calculation for multiple games
  private static int m_numGamesWonP1 = 0; // How many games won by Player 1
  private static int m_numGamesWonP2 = 0; // How many games won by Player 2
  private static int m_numPointsP1 = 0; // Number of points total for Player 1
  private static int m_numPointsP2 = 0; // Number of points total for Player 2
  // When calculating stats, will output the losing player's average
  private static double m_avgLosingNumCards = 0; // Average number of cards held by losing player
  private static int m_numEmptyStock = 0; // Number of empty stocks
  private static double m_avgNumEights = 0; // Average number of 8's played

  LinkedList<Card> tempLinkedList; // temporary card placeholder when dealing
  Card tempCard; // temporay LinkedList to get tempCard

  // Default Constructor
  public Game() {
    m_dealer = new Dealer();
    m_starterPile = new LinkedList<Card>();
    tempLinkedList = m_dealer.deals(1);
    tempCard = tempLinkedList.get(0);
    m_starterPile.add(tempCard); // dealer adds starting card to pile
    System.out.println("The dealer places a starter card.");
    m_player1 = new Player(1, m_dealer, m_starterPile);
    m_player2 = new Player(2, m_dealer, m_starterPile);
  }

  // Method used to collect points from losing player and add it to winning player
  public void collectPointsFrom(Player losingPlayer) {
    int currCardValue;
    int losingNumCards = 0;
    int pointsWon = 0;

    // Checks every card in losingPlayer's hand and add to Player 2's points
    for ( Card c : losingPlayer.getPlayerHand() ) {
      ++losingNumCards;
      currCardValue = c.getValue();
      // If there is an 8 card
      if (currCardValue == 8) {
        pointsWon += 50;
      }
      // If there is a 10, J, Q, K card
      else if ( (10 <= currCardValue) && (currCardValue <= 13) ) {
        pointsWon += 10;
      }
      // If there is an ace card
      else if (currCardValue == 14) {
        pointsWon += 1;
      }
      // Any other card will be given its respective value
      else {
        pointsWon += currCardValue;
      }
    }
    // Number of losing cards added to statistic
    m_avgLosingNumCards += losingNumCards;

    // Adds the number of cards the losing player had to the losing player's statistic
    switch (losingPlayer.getPlayerNum()) {
      // If the losing player is Player 1
      case 1:
        m_numPointsP2 += pointsWon; // Player 2 gets the points
        break;

      // If the losing player is Player 2
      case 2:
        m_numPointsP1 += pointsWon; // Player 1 gets the points
        break;

      default:
        break;
    }

  }

  // Play method to play a game
  public void play() {
    boolean endGame = false; // indicates the end of the game
    boolean p1CanPlay = true; // player 1 can play
    boolean p2CanPlay = true; // player 2 can play
    Card player1CardPlayed; // The card player 1 plays
    Card player2CardPlayed; // The card player 2 plays
    int newSuit; // The new suit of an 8 card


    // Have player 1 and player 2 take turns until they cannot anymore
    do {

      // Player 1's turn
      player1CardPlayed = m_player1.takeTurn();
      // while the player does not have a card
      while (player1CardPlayed == null) {
        if (m_dealer.size() == 0) {
          p1CanPlay = false;
          break;
        }
        System.out.println("Dealer deals a card to player"+m_player1.getPlayerNum());
        m_player1.getPlayerHand().addAll(m_dealer.deals(1));
        player1CardPlayed = m_player1.takeTurn();
      }
      // If the player plays a card
      if (player1CardPlayed != null) {
        // If they play an 8
        if (player1CardPlayed.getValue() == 8) {
          ++m_avgNumEights; // Increment number of times an 8 was placed down
          newSuit = m_player1.newSuit(); // Choose suit of card
          player1CardPlayed.setSuit(newSuit); // Change suit of card
        }
        // Add card to starterPile
        System.out.println("Player 1 placed down a "+player1CardPlayed);
        m_starterPile.add(player1CardPlayed);

      }

      // Player 2's turn
      player2CardPlayed = m_player2.takeTurn();
      // while the player does not have a card
      while (player2CardPlayed == null) {
        if (m_dealer.size() == 0) {
          p2CanPlay = false;
          break;
        }
        System.out.println("Dealer deals a card to player"+m_player2.getPlayerNum());
        m_player2.getPlayerHand().addAll(m_dealer.deals(1));
        player2CardPlayed = m_player2.takeTurn();
      }
      // If player 2 plays a card
      if (player2CardPlayed != null) {
        // If they play an 8
        if (player2CardPlayed.getValue() == 8) {
          ++m_avgNumEights; // Increment number of times an 8 was placed down
          newSuit = m_player2.newSuit(); // Choose suit of card
          player2CardPlayed.setSuit(newSuit); // Change suit of card
        }
        // Add card to starterPile
        System.out.println("Player 2 placed down a "+player2CardPlayed);
        m_starterPile.add(player2CardPlayed);

      }
      // If the players cannot play anymore

      if ( (!p1CanPlay) && (!p2CanPlay) ) {
        endGame = true;
      }

    } while (!endGame);


    // CHECK AFTER GAME:

    // If the dealer ran out of cards
    if (m_dealer.size() == 0) {
      ++m_numEmptyStock;
    }

    // if both players have the same number of cards
    if (m_player1.size() == m_player2.size()) {
      System.out.println("\n            ------------------It's a draw!-------------------\n");
      // Points are not added and no one wins
      // does not "return;" to go to bottom of method and check for an emptyStock
      return;
    }
    // Checks when one of the players have no more cards
    // if Player 1 has less cards
    else if (m_player1.size() < m_player2.size()) {
      ++m_numGamesWonP1; // Player 1 wins
      this.collectPointsFrom(m_player2); // Calculate points
    }
    // if Player 2 has less cards
    else if (m_player2.size() < m_player1.size()) {
      ++m_numGamesWonP2; // Player 2 wins
      this.collectPointsFrom(m_player1); // Calculate points
    }

  }

  // Accessors
  public Dealer getDealer() {
    return m_dealer;
  }

  public Player getPlayer1() {
    return m_player1;
  }

  public Player getPlayer2() {
    return m_player2;
  }

  public LinkedList<Card> getStarterPile() {
    return m_starterPile;
  }

  // METHODS
  // outputStats will calculate the cumulative game session statistics
  public void outputStats(int n) {
    // Calculates average number of cards
    m_avgLosingNumCards /= (double)n;
    m_avgNumEights /= (double)n;

    // Prints out statistics
    System.out.println("\n\n    --------------------    \n");
    System.out.println("    Number of Games Player 1 Won: "+m_numGamesWonP1);
    System.out.println("    Number of Games Player 2 Won: "+m_numGamesWonP2);
    System.out.println("    Player 1's Points: "+m_numPointsP1);
    System.out.println("    Player 2's Points: "+m_numPointsP2);
    System.out.println("    Average Number of Cards held by Losing Player: "+m_avgLosingNumCards);
    System.out.println("    Average Number of Times an Eight was Played as a Wild Card: "+m_avgNumEights);
    System.out.println("\n    --------------------    \n\n");

  }

}
