package pieces;

import java.util.Scanner;

import pieces.Chessboard;

public class Controller {
	
	static Scanner user_input = new Scanner(System.in);

	
	public static void main(String[] args) {
		Chessboard myChessboard = new Chessboard();


		while (myChessboard.getGameRunning()) {

			myChessboard.printBoard();
			myChessboard.move();

		}
	}

}
