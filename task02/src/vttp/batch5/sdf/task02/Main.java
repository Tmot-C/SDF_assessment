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

		String filePath = args[0];
		String fileTest = "task02\\TTT\\board3.txt";
		
		File file = new File(fileTest);
		FileReader fr = new FileReader(file); 
        BufferedReader br = new BufferedReader(fr);
		String inLine = "";

		

		Sign[][] board = new Sign[3][3];

		int row = 0;
		int column = 0;

		while ((inLine = br.readLine()) !=null){
			String[] rowEntry = inLine.split("");

			for (column= 0; column < rowEntry.length; column++) {

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


		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++){
				System.out.print(board[i][j].toString());
				if (j == 2)
					System.out.println();
					
			}
		}
			
				

		for (int key : legalSpots.keySet()) {
			ArrayList<Integer> columnList=legalSpots.get(key);
			for (int i = 0; i < columnList.size(); i++) {
				List<Boolean> OwinList = new ArrayList<Boolean>();
				Sign[][] cloneBoard = board;
				cloneBoard[key][columnList.get(i)] = Sign.X;
				boolean Xwin = checkWin(key, columnList.get(i), cloneBoard, Sign.X);
				
				if (Xwin == true){
					System.out.printf("y = %d, x = %d, utility = 1\n", key, columnList.get(i) );
					cloneBoard[key][columnList.get(i)] = Sign.BLANK;
				}

				else{
					for (int key2 : legalSpots.keySet()){
						ArrayList<Integer> columnList2=legalSpots.get(key2);
						for (int j = 0; j < columnList2.size(); j++){
							if (cloneBoard[key2][columnList2.get(j)] == Sign.BLANK) {
								cloneBoard[key2][columnList2.get(j)] = Sign.O;
								boolean Owin = checkWin(key2, columnList2.get(j), cloneBoard, Sign.O);
								if (Owin == true){
									OwinList.add(Owin);
								}		
								cloneBoard[key2][columnList2.get(j)] = Sign.BLANK;
							}
						} 
					}
					if (OwinList.contains(true)) {
						System.out.printf("y = %d, x = %d, utility = -1,\n", key, columnList.get(i) );
						cloneBoard[key][columnList.get(i)] = Sign.BLANK;
					}
					else{
						System.out.printf("y = %d, x = %d, utility = 0,\n", key, columnList.get(i) );
						cloneBoard[key][columnList.get(i)] = Sign.BLANK;
					}

				}

			}
			
		}
	}

	

	public static boolean checkWin(int x, int y, Sign[][] board, Sign user){
        boolean won = false;

        for(int i = 0; i < 3; i++){
            if(board[x][i] != user)
                break;
            if(i == 3-1){
				won = true;
				return won;
            }
        }
        
        //check row
        for(int i = 0; i < 3; i++){
            if(board[i][y] != user)
                break;
            if(i == 3-1){
				won = true;
				return won;
            }
        }
        
        //check diag
        if(x == y){
            //we're on a diagonal
            for(int i = 0; i < 3; i++){
                if(board[i][i] != user)
                    break;
                if(i == 3-1){
					won = true;
					return won;
                }
            }
        }
            
        //check anti diag
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