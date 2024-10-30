package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		
		String fileTest = "task02\\TTT\\board4.txt";
		
		//filereaders setup
		File file = new File(fileTest);
		FileReader fr = new FileReader(file); 
        BufferedReader br = new BufferedReader(fr);
		
		String inLine = "";
		//initialise array of tictactoe signs, values hardcoded for this implementation
		Sign[][] board = new Sign[3][3];

		//initialise row counter as there is no for loop to do so.
		int row = 0;
		int column = 0;

		while ((inLine = br.readLine()) !=null){
			
			//Read in tictactoe characters as a string and split into a string array.
			String[] rowEntry = inLine.split("");

			for (column= 0; column < rowEntry.length; column++) {
				//assigning the associated enum entries into the tictactoe board
				if (rowEntry[column].equals("X")) {
					board[row][column] = Sign.X;
				}
				else if (rowEntry[column].equals("O")) {
					board[row][column] = Sign.O;
				}
				else if (rowEntry[column].equals(".")) {
					board[row][column] = Sign.BLANK;
				}
				
			}
			row++;
		}

		/*Map declaration and population, map holds the coordinates for remaining empty spots on board. 
		Key values hold the Y(row) coordinates, and the key is paired to a list of the corresponding X(column) coordinates*/
		Map <Integer, ArrayList<Integer>> legalSpots = new HashMap<Integer, ArrayList<Integer>>();
		for (row = 0; row < board.length; row ++) {
			legalSpots.put(row,new ArrayList<Integer>()); 
			
			for (column = 0 ; column < board[0].length; column++){
				
				if (board[row][column] == Sign.BLANK) {
					ArrayList<Integer> columnList=legalSpots.get(row);
					columnList.add(column); 
				}
			}
		}


		//Convert Sign type board back to text using enum values and print out for user to see.
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j].toString());
				if (j == 2)
					System.out.println();
					
			}
		}
			
				
		/*Disgusting implementation of win condition check. The loop begins to loop through the legalSpaces hashmap,
		and extracting the first empty spot. It then places an X in the empty spot and checks for a win.
		After that, the dictionary is looped through in a nested loop to get every OTHER empty space and places an O
		there to check for a win. If a potential win for O is found and X hasnt won, the utility score is printed as -1.
		 */
		for (int key : legalSpots.keySet()) {
			ArrayList<Integer> columnList=legalSpots.get(key);
			for (int i = 0; i < columnList.size(); i++) {
				List<Boolean> winListO = new ArrayList<Boolean>();
				board[key][columnList.get(i)] = Sign.X;
				boolean Xwin = checkWin(key, columnList.get(i), board, Sign.X);
				
				if (Xwin == true){
					//Print the coordinates and score, and then wipe any moves made.
					System.out.printf("y = %d, x = %d, utility = 1\n", key, columnList.get(i) );
					board[key][columnList.get(i)] = Sign.BLANK;
				}

				else{
					for (int key2 : legalSpots.keySet()){
						ArrayList<Integer> columnList2=legalSpots.get(key2);
						for (int j = 0; j < columnList2.size(); j++){
							if (board[key2][columnList2.get(j)] == Sign.BLANK) {
								board[key2][columnList2.get(j)] = Sign.O;
								boolean Owin = checkWin(key2, columnList2.get(j), board, Sign.O);
								if (Owin == true){
									winListO.add(Owin);
								}
								//wipe moves made for O each iteration		
								board[key2][columnList2.get(j)] = Sign.BLANK;
							}
						} 
					}
					if (winListO.contains(true)) {
						//ditto
						System.out.printf("y = %d, x = %d, utility = -1,\n", key, columnList.get(i) );
						board[key][columnList.get(i)] = Sign.BLANK;
					}
					else{
						//ditto
						System.out.printf("y = %d, x = %d, utility = 0,\n", key, columnList.get(i) );
						board[key][columnList.get(i)] = Sign.BLANK;
					}

				}

			}
			
		}
	}

	
	/*function for checking whether the indicated player has won. Due to personal oversight, function only
	reads in the coordinates for one spot, and then checks the rows/columns it can possibly win from for a win. Instead
	of reading every single row and column in the grid and checking for wins/potential wins for the opponent */
	public static boolean checkWin(int x, int y, Sign[][] board, Sign user){
        boolean won = false;

		//column check
        for(int i = 0; i < 3; i++){
            if(board[x][i] != user)
                break;
            if(i == 3-1){
				won = true;
				return won;
            }
        }
        
        //row check
        for(int i = 0; i < 3; i++){
            if(board[i][y] != user)
                break;
            if(i == 3-1){
				won = true;
				return won;
            }
        }
        
        //diagonal check
        if(x == y){
            for(int i = 0; i < 3; i++){
                if(board[i][i] != user)
                    break;
                if(i == 3-1){
					won = true;
					return won;
                }
            }
        }
            
        //anti-diagonal check
        if(x + y == 3 - 1){
            for(int i = 0; i < 3; i++){
                if(board[i][(3-1)-i] != user)
                    break;
                if(i == 3-1){
					won = true;
					return won;
                }
            }
        }
		return won;
        
    }

}