/* Mastermind.java - Victor Troiano

*/

import java.util.*;

enum Difficulty{
		NORMAL,HARD
}


public class Mastermind{

	private String colors = "RGBYPO";
	private int codeLength = 4;
	private Board gameBoard;
	private Row codeRow;
	private Difficulty difficulty;
	private boolean gameOver = false;
	private static int BOARD_LENGTH = 10;
	private String pname;
	private Scanner scanner = new Scanner(System.in);

	//Constructor(): Accept difficult and player name and display basic output
	public Mastermind(Difficulty difficulty, String pname){
		this.difficulty=difficulty;
		this.pname=pname;

		String gameOutput = "";
		gameOutput+="/*\n";
		gameOutput+="/*>> NEW MASTERMIND GAME STARTED\n";
		gameOutput+="/*\n";
		gameOutput+="/* Player:     " + pname + "\n";
		gameOutput+="/* Difficulty: " + difficulty + "\n";
	//	gameOutput+="/*\n";
		gameOutput+="/*<<>><<>><<>><<>><<>><<>><<>><<>>";

		System.out.println(gameOutput);
		
	}// end Mastermind()

	//run(): initiate board and run the actual game loop. return # of turns it took to win
	public int run(){

		//Construct new board object with generated code row
		gameBoard = new Board(this.getCode(), BOARD_LENGTH);
		boolean winner = false;

		System.out.println(gameBoard.toString());
		//Loop for each turn
		int i=0;
		for (i=0; i<BOARD_LENGTH; i++) {
			
			//Action to take if game has not ended
			if (!gameOver) {

				//Get the next guest from user input
				String nextGuess = this.getGuess();

				//prematurely quit game
				if(nextGuess.equalsIgnoreCase("quit")){
					gameOver=true;
					break;
				}// end if

				//Add the guess to the gameboard, return true if the user has guessed correctly
				winner = gameBoard.addRow(nextGuess);

				//Print the gameboard out
				System.out.println(gameBoard.toString());

				//Check to see if no rows remain and set gameover if that is the case
				if(gameBoard.rowsRemaining()==0){
					gameOver=true;
				}// end if

				if (winner) {
					gameOver = true;
				}// end if

			}else{
				break;
			}// end if..else
		}// end for

		//Return number of turns required to win, 0 for failed
		if(!winner){
			return -1;
		}else{
			return i;
		}// end if..else
	}//end: run()

	//getCode(): return a randomly shuffled code of the length required by the difficulty
	public Row getCode(){
		if (difficulty == Difficulty.HARD){
			colors += "W";
			codeLength = 5;
		}// end if
	
		String code=shuffle(colors);

		code=code.substring(0, codeLength);

		codeRow = new Row(code);
		return codeRow;
	}//end: getCode()

	//shuffle(): shuffle the input string and output it.
	public String shuffle(String input){

		//create a list of characters for an input string
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }//end for

        //assign list of characters a random value to determine color
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }//end while
       
       return output.toString();
    }//end: shuffle();

    //getHint(): Get a hint of the code string and return it
    public String getHint(){

    	//Create a random number between 0 and the length of the code
    	int rand = (int)(Math.random()*codeLength);
    	
    	//Get the string of the code and convert it to char array
    	String codeString = codeRow.toString();
    	char[] codeStringArray = codeString.toCharArray();

    	//Loop over char code array and add "x" to the hint string unless the index matches the random number
    	String hint="";
    	for(int i=0; i<codeLength; i++){
    		if(i!=rand){
    			hint+="x";
    		}else{
    			hint+=codeStringArray[i];
    		}// end if..else
    	}// end for

    	return hint;
    }//end: getHint()

    //getGuess(): get user input for each turn. Can either be row guess, hint request or quit request
	public String getGuess(){

		//Print out the color options so the user knows which they can select from
		System.out.println("/*\n/*[Peg Color Options: " + colors + "]");

		System.out.println(">>Enter a guess (type 'quit' to give up or 'hint' for a help):");

		String guess = scanner.nextLine();
		boolean valid = false;



		while (!valid){

			valid = true;

			//If the user types quit, exit out of this loop and pass "quit" string up the chain
			if(guess.equalsIgnoreCase("quit")){
				return "quit";
			}// end if

			//If user types 'hint', give them a hit, reset valid to false and re-prompt them for input
			if(guess.equalsIgnoreCase("hint")){
				System.out.println("Your hint: "+ this.getHint());
				valid=false;
				
				System.out.println(">>Enter a guess (type 'quit' to give up or 'hint' for a help):");
				guess = scanner.nextLine();


			}
			else{//Neither quit/hint is typed, much validate guess input.


				//Check the length requiredment
				if(guess.length() != codeLength){
					valid=false;
				}// end if

				//Check that each color guessed is one of the colors available
				for(int i = 0; i < guess.length(); i++){

					if (!colors.contains("" + Character.toUpperCase(guess.charAt(i)))){

						valid = false;
					}//end if
				}// end for

				//If the guess was not valid, inform the user and ask for input again
				if(!valid){
					System.out.println("/* Your guess is invalid! Make sure it is the correct length and uses the correct colors!");
					guess = scanner.nextLine();
				}// end if
			}//end if..else
		}//end while
		
		return guess;
	}//end: getGuess();
}