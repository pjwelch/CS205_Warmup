/* Board.java - Patrick Welch
*/

import java.util.ArrayList;

public class Board{

	private ArrayList<Row> rows;
	private ArrayList<Integer> hardhits;
	private ArrayList<Integer> softhits;
	private Row code;
	private int currentRow = 0;
	private Difficulty difficulty;
	private int rows_remaining;

// constructor() - creates Board
	public Board(Row code, int board_length){
		//Create code row with difficulty
		this.code=code;
		this.rows_remaining=board_length;

		rows = new ArrayList<Row>();
		hardhits=new ArrayList<Integer>();
		softhits=new ArrayList<Integer>();
	} // end Board()

// addRow() - adds guessed row to the board 
	public boolean addRow(String guessString){
		Row new_row= new Row(guessString);

		//Compare guess row with code row
		new_row.compare(code);

		//add the hard hit, soft hit and row data to an array list
		hardhits.add(new_row.getHardHits());
		softhits.add(new_row.getSoftHits());
		rows.add(new_row);	

		rows_remaining--;

		// if the Player has correctly guessed all colors (# hardhits = length of answer code)
		// the game will end
		if(new_row.getHardHits()==guessString.length()){
			return true;
		}else{
			return false;
		} // end if..else
	} // end addRow()

// rowsRemaining() - returns number of remaining rows
	public int rowsRemaining(){
		return rows_remaining;
	} // end rowsRemaining()

// toString() - outputs rows for the board
	public String toString(){

		//Change the blanks size so formatting stays consistent for different size
		String blanks="----";
		String guess =" Guess";

		// changes blank size for hard difficulty
		if(code.items.size()==5){
			blanks="-----";
			guess= " Guess ";
		} // end if

		// 
		String output = "/*\n/*>> Game Board\n";
		output+="/* Soft Hits |"+guess+"| Hard Hits";

		//Display guessed/filled rows
		for(int i=0; i<rows.size(); i++){

			output+="\n/*     "+ softhits.get(i)+"     | "+rows.get(i).toString() + " |    " + hardhits.get(i);
		} // end for

		//Display empty rows if there are any remaining
		if(rows_remaining>0){
			for(int j=0; j<rows_remaining; j++){
				output+="\n/*     -     | "+ blanks + " |    -";
			} // end for
		} // end if

		//Display number of rows/guesses left
		output+="\n/*\n/* **" + rows_remaining + " Guesses remain**";

		//if game is over, display the answer
		if(rows_remaining==0){
			output+="\n/*\n/* The secret code: " + code.toString(); 
		} // end if

		return output;
	} // end toString()
}