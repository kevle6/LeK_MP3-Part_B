/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Player class creates a Player for Crazy Eights that will take a turn in the game
*/

import java.util.LinkedList;
import java.util.Random;
public class Player {

  // MEMBER VARIABLES
  private int m_playerNumber; // Player number
  private LinkedList<Card> m_playerHand = new LinkedList<Card>(); // Player's hand of cards
  private Dealer m_dealer; // Reference to dealer
  private LinkedList<Card> m_starterPile; // Reference to starterPile
  private Random m_randomObject; // Random number for every player to avoid creating unneccessary amounts of Random objects

  // OVERLOADED CONSTRUCTOR
  public Player(int playerNumber, Dealer dealer, LinkedList<Card> starterPile) {
    m_playerNumber = playerNumber;
    m_playerHand = dealer.deals(5); // Each player gets 5 cards
    m_dealer = dealer;
    m_starterPile = starterPile;
    m_randomObject = new Random();
  }

  // METHODS

  // Method for player to find card and play the card
  public Card takeTurn() {
    Card lastCard = m_starterPile.get(m_starterPile.size() - 1); // the card the player needs to match
    int i_playableCard = 0; // to know index of playable card
    int i_eightCard = 0; // index of an eight card (if it exists)
    boolean hasPlayableCard = false;
    boolean hasEightCard = false;
    Card currentCard;
    Card cardToPlay = new Card();
    int probability = m_randomObject.nextInt(10);
  
    // Look through all cards
    for (int i = 0; i < m_playerHand.size(); ++i) {
      // get current card
      currentCard = m_playerHand.get(i);
      // if there is no 8 card found yet and current card is an 8
      if ((!hasEightCard) && (currentCard.getValue() == 8) ) {
        // assign it to i_eightCard
        i_eightCard = i;
        hasEightCard = true;
      }
      // The player does not currently have a playable card, but they have a card that equals top card
      else if ( (!hasPlayableCard) && (currentCard.equals(lastCard)) ) {
        i_playableCard = i;
        hasPlayableCard = true;
      }
    }
    // If you have DO NOT have an eight card, and no playable card, then return no card
    if ((!hasEightCard) && (!hasPlayableCard)) {
      return null;
    }
    // if they have an 8 card but no playable card, play the 8 card
    else if ( (hasEightCard) && (!hasPlayableCard) ){
      cardToPlay = m_playerHand.get(i_eightCard);
      m_playerHand.remove(i_eightCard);
      return cardToPlay;
    }
    // if they do not have an 8 card but they have a playable card
    else if ( (!hasEightCard) && (hasPlayableCard) ) {
      cardToPlay = m_playerHand.get(i_playableCard);
      m_playerHand.remove(i_playableCard);
      return cardToPlay;
    }
    // if they have an 8 card and a playable card
    else if ( (hasEightCard) && (hasPlayableCard) ) {
      // 10% chance they will play the 8 card
      if (probability == 8) {
        cardToPlay = m_playerHand.get(i_eightCard);
        m_playerHand.remove(i_eightCard);
        return cardToPlay;
      }
      // else, play the playable card
      else {
        cardToPlay = m_playerHand.get(i_playableCard);
        m_playerHand.remove(i_playableCard);
        return cardToPlay;
      }
    }
    return cardToPlay;
  }


  // ACCESSORS
  public int getPlayerNum() {
    return m_playerNumber;
  }

  public LinkedList<Card> getPlayerHand() {
    return m_playerHand;
  }

  // METHODS

  // newSuit will generate a random suit with 25% chance for each suit
  public int newSuit() {
    int selectedSuit = m_randomObject.nextInt(4);
    return selectedSuit;
  }

  // size method
  public int size() {
    return m_playerHand.size();
  }

}
