/*
Full name: Kevin Le
Student ID: 2406054
Chapman email: kevle@chapman.edu
Course number and section: CPSC 231-04
Assignment or exercise number: Programming Mastery Project 3B: Crazy Eights!
*/

/*
The Driver class simulates mutliple game sessions between two players.
It will output statistics of the game at the end of all the games.
*/

public class CrazyEightsDriver {

  public static void main(String[] args) {

    Game game = null; // Instantiate Game
    int numGames = Integer.parseInt(args[0]); // uses autounboxing
    for (int i = 1; i <= numGames; ++i) {
      System.out.println("\n\nBeginning of Game "+i+"\n\n");
      game = new Game(); // Instantiate Game
      game.play(); // Play game numGames times
      System.out.println("\n\nEnd of Game "+i+"\n\n");
    }

    game.outputStats(numGames); // Output results

  }
}
