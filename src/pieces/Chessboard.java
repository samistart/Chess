package pieces;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

import pieces.AbstractPiece;

/**
 * An 8x8 Chessboard with a console display and string user input Has some basic
 * rules for checking if a move is valid or not Keeps track of the score of each
 * player by the value of pieces they take For best results construct a
 * Chessboard(), call move() and call printBoard().
 * 
 * @author SamiStart
 *
 */

public class Chessboard {

	private Boolean gameRunning;
	private AbstractPiece[][] chessboard = new AbstractPiece[numOfRowsAndCols][numOfRowsAndCols];// [row][column]
	Scanner user_input = new Scanner(System.in);
	private static final int numOfRowsAndCols = 8;
	private static int srcRow, srcCol, destRow, destCol;
	private static int whiteScore = 0, blackScore = 0;
	private static Boolean whitesTurnToMove;
	// Set to true if move is invalid. Asks for user input again in move()
	// method.
	private static Boolean invalidMove = false;
	// Holds string with the user input for move instructions
	String move;

	/**
	 * Constructs a Chessboard object and populates it with pieces Starts a
	 * chess game running.
	 */

	public Chessboard() {

		initialiseBoard(chessboard);
		gameRunning = true;

	}

	/**
	 * This gets attribute Boolean gameRunning if this is false then you should
	 * stop calling move() and printBoard() and close the Chessboard()
	 * 
	 * @return a Boolean that is false if the user wants to exit called
	 *         gameRunning
	 */
	public Boolean getGameRunning() {
		return this.gameRunning;
	}

	/**
	 * Populates the chessboard of AbstractPiece with the correct pieces and
	 * randomly assigns whether white or black moves first
	 * 
	 * @param chessboard
	 */
	private static void initialiseBoard(AbstractPiece[][] chessboard) {
		// a chessboard with 8x8 matrix of pieces
		// rows [0] and [1] are black
		// rows [6] and [7] are white

		for (int row = 0; row < chessboard.length; row++) {
			for (int col = 0; col < chessboard[row].length; col++) {
				if (row == 0) {
					switch (col) {
					case 0:
						chessboard[row][col] = new Rook(false);
						break;
					case 1:
						chessboard[row][col] = new Knight(false);
						break;
					case 2:
						chessboard[row][col] = new Bishop(false);
						break;
					case 3:
						chessboard[row][col] = new Queen(false);
						break;
					case 4:
						chessboard[row][col] = new King(false);
						break;
					case 5:
						chessboard[row][col] = new Bishop(false);
						break;
					case 6:
						chessboard[row][col] = new Knight(false);
						break;
					case 7:
						chessboard[row][col] = new Rook(false);
						break;
					}
				} else if (row == 1) {
					chessboard[row][col] = new Pawn(false);
				} else if (row == 6) {
					chessboard[row][col] = new Pawn(true);
				} else if (row == 7) {
					switch (col) {
					case 0:
						chessboard[row][col] = new Rook(true);
						break;
					case 1:
						chessboard[row][col] = new Knight(true);
						break;
					case 2:
						chessboard[row][col] = new Bishop(true);
						break;
					case 3:
						chessboard[row][col] = new Queen(true);
						break;
					case 4:
						chessboard[row][col] = new King(true);
						break;
					case 5:
						chessboard[row][col] = new Bishop(true);
						break;
					case 6:
						chessboard[row][col] = new Knight(true);
						break;
					case 7:
						chessboard[row][col] = new Rook(true);
						break;
					}
				} else {
					chessboard[row][col] = null;
				}
			}
		}

		// Randomly assign who starts first (black or white)
		Random rand = new Random();
		whitesTurnToMove = rand.nextBoolean();

	}

	/**
	 * Prints out the unicode for each character to the console using the draw()
	 * method from the relevant piece's class followed by tabs for tidiness.
	 * Prints numbers 1-8 alongside rows and letters a-h alongside columns
	 */
	public void printBoard() {

		// must take an 8x8 array of Chessmen (chess pieces),
		// i.e. a chessboard, as argument

		System.out.println("\ta\tb\tc\td\te\tf\tg\th");
		for (int row = 0; row < chessboard.length; row++) {
			System.out.print(8 - row + ".\t");
			for (int col = 0; col < chessboard[row].length; col++) {
				if (chessboard[row][col] != null) {
					chessboard[row][col].draw();
					System.out.print("\t");

				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Checks if a move is valid with 2 steps. Step 1: some general rule checks
	 * that any piece should obey. 
	 * Step 2: The specific isMoveValid() method from
	 * a piece's class that checks rules specific for that piece, e.g that a
	 * rook moves in straight lines.
	 * 
	 * @return True if valid, false if invalid.
	 */

	private boolean moveValid() {

		// invalid if the move origin or destination is outside the board

		if (srcRow < 0 || srcRow > 7 || srcCol < 0 || srcCol > 7 || destRow < 0
				|| destRow > 7 || destCol < 0 || destCol > 7) {
			System.out.println("Move is outside the board");
			return false;
		}

		// Invalid if origin is null
		if (chessboard[srcRow][srcCol] == null) {
			System.err.println("Origin is empty");
			return false;
		}

		// Invalid if player moves when it's not their turn
		if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
				|| (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
			System.err.println("It's not your turn");
			return false;
		}

		// return false if specific piece rules are not obeyed
		if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
				destCol)) {
			System.err.println("This piece doesn't move like that");
			return false;
		}

		// this statement stops the statement for checking if white lands on
		// white from performing isWhite() on a null space
		if (chessboard[destRow][destCol] == null) {
			return true;
		}

		// invalid if the white lands on white
		if (chessboard[srcRow][srcCol].isWhite
				&& chessboard[destRow][destCol].isWhite) {
			System.err.println("White cannot land on white");
			return false;
		}

		// invalid if the black lands on black
		if (!chessboard[srcRow][srcCol].isWhite
				&& !chessboard[destRow][destCol].isWhite) {
			System.err.println("Black cannot land on black");
			return false;
		}

		return true;

	}

	/**
	 * A private method called to update the score of whoever's turn it is after
	 * they take an opposing piece
	 */
	private void updateScore() {
		if (chessboard[destRow][destCol] == null) {
			return;
		}
		if (whitesTurnToMove) {
			whiteScore += chessboard[destRow][destCol].relativeValue();
		} else {
			blackScore += chessboard[destRow][destCol].relativeValue();

		}
	}

	/**
	 * Take user input for the instructions for move in the form
	 * "start coords to destination coords", e.g. "d2 to d3" and converts this
	 * string to array coordinates for the Chessboard. Checks if the move is
	 * valid using moveValid(). If valid moves piece to destination on
	 * Chessboard and updates score with updateScore(). If invalid prints error
	 * message and recursively calls itself.
	 */
	public void move() {

		System.out
				.println("___________________________________________________\n"
						+ "Score: White "
						+ whiteScore
						+ " | "
						+ blackScore
						+ " Black");

		if (invalidMove) {
			System.err.println("Move is invalid. Please try again:");
			// System.out.println("Move is invalid. Please try again:");
			invalidMove = false;
		}

		else if (whitesTurnToMove) {
			System.out
					.println("___________________________________________________\n"
							+ "White's turn to move\n"
							+ "___________________________________________________\n");
		} else {
			System.out
					.println("___________________________________________________\n"
							+ "Black's turn to move\n"
							+ "___________________________________________________\n");
		}

		move = user_input.nextLine();

		if (move.equalsIgnoreCase("exit")) {
			gameRunning = false;
			System.out.println("Thanks for playing.");
			return;
		}

		// convert to lower case
		String lowerCase = move.toLowerCase();
		// parse move string into components
		String[] components = lowerCase.split(" ");

		// if you assume that move is "e1 to e5" then
		// components[0].chartAt(0) = 'e'
		// components [0].charAt (1) = '1'

		// use chars in components to set the array coordinates of the
		// move origin and move destination

		srcRow = 7 - (components[0].charAt(1) - '1');
		srcCol = components[0].charAt(0) - 'a';
		destRow = 7 - (components[2].charAt(1) - '1');
		destCol = components[2].charAt(0) - 'a';

		if (moveValid()) {
			updateScore();
			// put piece in destination
			chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
			// empty the origin of the move
			chessboard[srcRow][srcCol] = null;
			whitesTurnToMove = !whitesTurnToMove;
		} else {
			invalidMove = true;
			move();

		}

	}

}
