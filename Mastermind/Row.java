/* Row.java - Michael Toth

Variables:
	- int hardhit
	- int softhit
	- ArrayList<Peg> items


Methods:
	- compare()
	- getHardHits()
	- getSoftHits()
	- toString()
*/

import java.util.ArrayList;

public class Row{

	private int hardhit; //right color right place
	private int softhit; //right color wrong place

	public ArrayList<Peg> items;

// constructor() - create Row with pegs
	public Row(String pegstring){
		items = new ArrayList<Peg>();
		//parse row string, create peg object and add to items array
		for (int i = 0; i < pegstring.length();i++){

			items.add(new Peg(pegstring.charAt(i)));

		}// end for

	}// end Row()

// compare() - compares the player's guess to the answer code and returns softhits & hardhits
	public void compare(Row code){

		//ArrayList<Peg> code_items=code.items;

		//Loop through each item in the code row
		for(int j=0; j<code.items.size(); j++){

			boolean hard=false;
			boolean soft=false;

			//For each code row item, loop through each guess row item
			for(int k=0; k<items.size(); k++){

				//get the guess and code values for the specific column
				int guessval=items.get(k).getValue();
				int codeval =code.items.get(j).getValue();

				//Determine if it is a hard hit or 
				if(guessval==codeval && j==k){
					hard=true;
				}else if(guessval==codeval && j!=k){
					soft=true;
				}// end if..else if

			}//end inner for

			//determine hardhits vs softhits
			if(hard){
				hardhit++;
			}else if(soft && !hard){
				softhit++;
			}// end if..else if

		}//end outer for

	}//end compare()

// getHardHits() - returns int hardhit
	public int getHardHits(){
		return hardhit;
	}// end getHardHits()

// getSoftHits() - returns int softhit
	public int getSoftHits(){
		return softhit;
	}// end getSoftHits()

// toString() - returns String output - gets Peg colors to display row
	public String toString(){

		String output ="";

		for(int i=0; i<items.size(); i++){
			Peg peg = items.get(i);
			output+=peg.getColor();
		}// end for

		return output;
	}// end toString()
}