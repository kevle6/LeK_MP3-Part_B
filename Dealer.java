import java.util.LinkedList;

/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Dealer class is responsible for managing the cards
*/

public class Dealer {
    // member variable to represent deck at hand
    private Deck m_deck;

    // Default Constructor
    public Dealer() {
        m_deck = new Deck();
    }

    // deals method
    public LinkedList<Card> deals(int n) {
        LinkedList<Card> playerDeal = new LinkedList<Card>();
        // If the deck is empty, return empty LinkedList
        if (m_deck.size() == 0) {
          System.out.println("The dealer has no more cards!");
          return playerDeal;
        }
        // Adds n Cards from m_deck to LinkedList
        for (int i = 0; i < n; ++i) {
          playerDeal.add(m_deck.deal());
        }

        return playerDeal;
    }

    // size method
    public int size() {
      return m_deck.size();
    }

    // toString method
    public String toString() {
        return m_deck.toString();
    }
}
