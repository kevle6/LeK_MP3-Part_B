/* 
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu 
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3A: Let's Play Cards
*/

/*
The TestCards class is meant to test the functionality 
of the Card, Deck, and Dealer classes. 
*/

import java.util.LinkedList;

public class TestCards {
    public static void main(String[] args) {

        // Card Class
        System.out.println("\n\n    ----------TESTING THE CARD CLASS----------\n\n");
        
        // Default Constructor for Card
        Card defaultCard = new Card();
        System.out.println("Default Card: "+defaultCard+"\n\n");

        // Overloaded Constructor for Card
        Card myCard = new Card(Card.ACE, Card.SPADES);
        System.out.println("Overloaded Card (ACE, SPADES): "+myCard+"\n\n");

        // Card Mutators
        myCard.setValue(5);
        myCard.setSuit(Card.DIAMONDS);
        System.out.println("Modifying myCard...\n\n");
        System.out.println("myCard Modified: "+myCard+"\n\n");

        // Card Copy Constructor and Accessors
        System.out.println("Copying myCard...\n\n");
        Card myNewCard = new Card(myCard);
        System.out.print("Copy of myCard: ");
        System.out.println(myNewCard+"\n\n");

        // Card equals method
        System.out.println("Is defaultCard equal to myCard?");
        System.out.println(defaultCard.equals(myCard)+"\n\n");
        System.out.println("Is myCard equal to myCard?");
        System.out.println(myCard.equals(myCard)+"\n\n");



        // Deck Class
        System.out.println("    ----------TESTING THE DECK CLASS----------\n\n");

        // Default Constructor for Deck
        System.out.println("Creating new deck...\n\n");
        Deck deck1 = new Deck();

        // Deck deal method
        System.out.println("Cards dealt from deck1: \n\n");


        // Multiple Cards dealt
        Card randCard = new Card();
        for (int i = 1; i < 6; ++i) {
            randCard = deck1.deal();
            System.out.println("Card "+i+": "+randCard+"\n\n");
        }
        
        System.out.println("---------------------------------------------\n\n");

        // Copy Constructor and size method for Deck
        System.out.println("Copying deck1 (Size: "+deck1.size()+") to deck2... \n");
        Deck deck2 = new Deck(deck1);
        System.out.println("deck2:");

        // toString method
        System.out.println(deck2);
        System.out.println("\n    deck2 should be missing the same cards as deck1.\n\n");
        System.out.println("Size of deck1: "+deck1.size());
        System.out.println("Size of deck2: "+deck2.size());



        // Dealer class
        System.out.println("\n\n    ----------TESTING THE DEALER CLASS----------\n\n");

        // Default Constructor
        System.out.println("Creating new Dealer named Bill...\n");
        Dealer bill = new Dealer();

        // Generic card player
        System.out.println("Creating new Card Player named Jared...\n");
        LinkedList<Card> jared = new LinkedList<Card>();

        // deals method
        System.out.println("Bill the Dealer deals 8 cards to Jared.\n");
        jared = bill.deals(8);

        // size method
        System.out.println("The number of cards Bill has left: "+bill.size()+"\n\n");
        System.out.println("Jared's cards ("+jared.size()+"): ");
        for (int i = 0; i < jared.size(); ++i) {
            System.out.println(jared.get(i));
        }

        System.out.println("\n\n---------------------------\n\n");

        // toString method
        System.out.println("All of Bill's cards:\n");
        System.out.println(bill+"\n\n");

    }
}
