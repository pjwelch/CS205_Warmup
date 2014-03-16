/* Peg.java - Patrick Welch

Peg class

defines a peg color and integer value with respect to that color as defined below 
 * red = 1, green = 2, blue = 3, orange = 4, purple = 5, yellow = 6, white = 7 *

/*

Methods:
	- getValue()
	- getColor()
*/

public class Peg{

	private int value;
	private char color;

// constructor() - creates a peg with an assigned color
	public Peg(char color){
		
		this.color = Character.toUpperCase(color);
		
		setValue();

	}// end Peg()

//setValue() - sets a corresponding value to the color entered 
	public void setValue(){

		switch(color){
			
			case 'R': value = 1;
				break;

			case 'G': value = 2;
				break;

			case 'B': value = 3;
				break;

			case 'O': value = 4;
				break;

			case 'P': value = 5;
				break;

			case 'Y': value = 6;
				break;

			case 'W': value = 7; 

		}// end switch
	}// end setValue()

// getValue() - returns integer value of color
	public int getValue(){

		return value;

	}// end getValue()

// getColor() -  returns char value of color 
	public char getColor(){

		return color;

	}//end getColor()



}