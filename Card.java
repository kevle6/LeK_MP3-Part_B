/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Card class will create a Card instance with a value and suit.
The suits and values will be represented with constant integers for consistent suits and values.
Cards will be printed out with their respective value and suit.
*/

public class Card {
    // int member variables to define value and suit of card
    private int m_value;
    private int m_suit;

    // constants defined with ints to represent suits
    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;

    // constants defined with ints to represent face card value
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    // Default Constructor
    public Card() {
        // Cards are assigned to 2 of Hearts by default
        m_value = 2;
        m_suit = HEARTS;
    }

    // Overloaded Constructor
    public Card(int value, int suit) {
        // If the card is given a valid value and valid suit
        if ( (2 <= value) && (value <= 14) && (0 <= suit) && (suit <= 3) ) {
            m_value = value;
            m_suit = suit;
        }
        // Else, set Card to default values
        else {
            m_value = 2;
            m_suit = HEARTS;
        }
    }

    // Copy Constructor
    public Card(Card c) {
        m_value = c.m_value;
        m_suit = c.m_suit;
    }

    // toString method
    public String toString() {
        String s = "";
        // switch statement to display correct value
        switch (m_value) {
            case JACK:
                s += "Jack of ";
                break;
            case QUEEN:
                s += "Queen of ";
                break;
            case KING:
                s += "King of ";
                break;
            case ACE:
                s += "Ace of ";
                break;
            default:
                s += m_value+" of ";
                break;
        }

        // switch statement to display correct suit
        switch (m_suit) {
            case HEARTS:
                s += "Hearts";
                break;
            case SPADES:
                s += "Spades";
                break;
            case CLUBS:
                s += "Clubs";
                break;
            case DIAMONDS:
                s += "Diamonds";
                break;
            default:
                break;
        }
        return s;
    }

    // equals method, checks only value
    public boolean equals(Object o) {
        // if not a Card instance
        if (!(o instanceof Card)) {
            return false;
        }

        // if the same memory address
        else if (this == o) {
            return true;
        }

        // In the case that either suit or value is the same
        Card c = (Card) o;
        return ( (m_suit == c.m_suit) || (m_value == c.m_value) );
    }

    // Accessors
    public int getValue() {
        return m_value;
    }
    public int getSuit() {
        return m_suit;
    }

    // Mutators
    public void setValue(int value) {
        m_value = value;
    }
    public void setSuit(int suit) {
        m_suit = suit;
    }
}
