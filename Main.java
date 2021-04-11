package tutorial1;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		//System.out.print("Enter cells: ");
		
		String input = "         ";
		
		//int[][] data = new int[3][3];
		
		printGrid(input);
		
		System.out.print("Enter the coordinates: ");
		
		int xCoordinate =0;
		int yCoordinate = 0;
		int roundCouner = 0;
		String gameState = "";
		boolean goodInput = true;
		boolean play = true;
	
		
		while(play) {
			
			try {
				xCoordinate = sc.nextInt();
				yCoordinate = sc.nextInt();
				goodInput = true;
			} catch (Exception e) {
				System.out.println("You should enter numbers!");
				goodInput = false;
				sc.nextLine();
			}
			
			
			
			
			if(goodInput) {
				boolean goodRange = xCoordinate > 0 && xCoordinate < 4 && yCoordinate > 0 && yCoordinate < 4;
				int indexToChange = getIndexToChange(xCoordinate, yCoordinate);
				if(!goodRange) {
					
					System.out.println("Coordinates should be from 1 to 3!");
					continue;
					
				} else if(isUnderscore(indexToChange, input)) {
					if(roundCouner % 2 == 0) {
						input = getModifiedInput(indexToChange, input, 'X');
					} else {
						input = getModifiedInput(indexToChange, input, 'O');
					}
					printGrid(input);
					
					roundCouner++;
					gameState = getState(input);
					//System.out.println(gameState);
					play = !gameState.equals("X wins") && !gameState.equals("O wins") && !gameState.equals("Draw");
					
					if(play) {
						System.out.print("Enter the coordinates: ");
						}
					
				}
				else {
					System.out.println("This cell is occupied! Choose another one!");
					continue;
				}
			}
		}
		
		
		
		//String state = getState(input);
		
		System.out.println(gameState);

	}
	
	static String getModifiedInput(int index, String input, char player) {
	
			char[] newInputChars = input.toCharArray();
			newInputChars[index] = player;
			return String.valueOf(newInputChars);
			
		
	}
	
	static boolean isUnderscore(int index, String data) {
		if (data.charAt(index) == ' ') {
			
			//System.out.println("This cell is occupied! Choose another one!");
			return true;
			
		} else {
			
			return false;
			
		}
	}
	
	static int getIndexToChange(int xCord, int yCord) {
		return (xCord-1)*3+yCord-1;
	}
	
	
	static void printGrid (String input) {
		
		System.out.println("---------");
		
		for(int i = 0; i < 9; i=i+3)
		{
			System.out.print("| ");
			System.out.print(input.charAt(i) + " ");
			System.out.print(input.charAt(i+1) + " ");
			System.out.print(input.charAt(i+2) + " ");
			System.out.print("|");
			System.out.println();
			
		}
		
		System.out.println("---------");
		
	}
	
	static String getState(String input) {
		
		boolean xWins = input.charAt(0) == 'X' && input.charAt(0) == input.charAt(1) && input.charAt(2) == input.charAt(1) ||
				input.charAt(3) == 'X' && input.charAt(3) == input.charAt(4) && input.charAt(5) == input.charAt(4) ||
				input.charAt(6) == 'X' && input.charAt(6) == input.charAt(7) && input.charAt(8) == input.charAt(7) ||
				input.charAt(0) == 'X' && input.charAt(0) == input.charAt(3) && input.charAt(6) == input.charAt(3) ||
				input.charAt(1) == 'X' && input.charAt(1) == input.charAt(4) && input.charAt(7) == input.charAt(4) ||
				input.charAt(2) == 'X' && input.charAt(2) == input.charAt(5) && input.charAt(8) == input.charAt(5) ||
				input.charAt(0) == 'X' && input.charAt(0) == input.charAt(4) && input.charAt(8) == input.charAt(4) ||	
				input.charAt(2) == 'X' && input.charAt(2) == input.charAt(4) && input.charAt(6) == input.charAt(4) ;

		boolean oWins = input.charAt(0) == 'O' && input.charAt(0) == input.charAt(1) && input.charAt(2) == input.charAt(1) ||
				input.charAt(3) == 'O' && input.charAt(3) == input.charAt(4) && input.charAt(5) == input.charAt(4) ||
				input.charAt(6) == 'O' && input.charAt(6) == input.charAt(7) && input.charAt(8) == input.charAt(7) ||
				input.charAt(0) == 'O' && input.charAt(0) == input.charAt(3) && input.charAt(6) == input.charAt(3) ||
				input.charAt(1) == 'O' && input.charAt(1) == input.charAt(4) && input.charAt(7) == input.charAt(4) ||
				input.charAt(2) == 'O' && input.charAt(2) == input.charAt(5) && input.charAt(8) == input.charAt(5) ||
				input.charAt(0) == 'O' && input.charAt(0) == input.charAt(4) && input.charAt(8) == input.charAt(4) ||	
				input.charAt(2) == 'O' && input.charAt(2) == input.charAt(4) && input.charAt(6) == input.charAt(4) ;

		int oCount = 0;
		int xCount = 0;

		for(int i = 0; i < 9 ; i++ ) {
			if( input.charAt(i) == 'O' ) {

				oCount++;

			} else if ( input.charAt(i) == 'X' ) {

				xCount++;

			}

		}

		boolean impossibleMove = Math.abs(xCount - oCount) > 1 || xWins == true && oWins == true;


		if (impossibleMove) {

			return "Impossible";

		} else if (oWins) {

			return "O wins";

		} else if (xWins) {

			return "X wins";

		} else if (input.contains(" ")) {

			return "Game not finished";

		} else {

			return "Draw";

		}
		
	}
		
}
