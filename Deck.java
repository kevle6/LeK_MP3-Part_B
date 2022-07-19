/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Deck class will use the Card class to create all 52 unique cards
of each value and suit. It will then populate a LinkedList representing the Deck.
*/

import java.util.LinkedList;
import java.util.Random;

public class Deck {
    // LinkedList member variable representing the Deck of 52 Cards
    private LinkedList<Card> m_cards = new LinkedList<Card>();

    // Default Constructor
    public Deck() {
        for (int value = 2; value < 15; ++value) {
            for (int suit = 0; suit < 4; ++suit) {
                m_cards.add(new Card(value, suit));
            }
        }
    }

    // Copy Constructor
    public Deck(Deck d) {
        for (Card c : d.m_cards) {
            // Create a new Card instance with Copy Constructor
            m_cards.add(new Card(c));
        }
    }

    // toString method
    public String toString() {
        String s = "";
        for (Card c : m_cards) {
            s += c+"\n\n";
        }
        return s;
    }

    // size method
    public int size() {
        int size = 0;
        for (Card c : m_cards) {
            // Adds to size for every card in m_cards
            ++size;
        }
        return size;
    }

    // deal method
    public Card deal() {
        Random random = new Random();

        // Generate random number to find index
        int selection = random.nextInt(m_cards.size());

        // Get card at random index and store in temporary Card variable
        Card tempCard = new Card(m_cards.get(selection));

        // Remove Selected card
        m_cards.remove(selection);
        return tempCard;
    }

}
