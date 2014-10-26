package pieces;
import pieces.AbstractPiece;


public class Bishop extends AbstractPiece {

	public Bishop(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if (isWhite) {
			System.out.print("\u2657");
		} else {
			System.out.print("\u265D");
		}
	}

	private static Boolean diagonalPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// returns true if the path is diagonal
		// arguments are initial and final coordinates of move in chessboard
		// array
		// good for checking if a move is valid
		return ((Math.abs(srcRow - destRow) == Math.abs(srcCol
				- destCol)));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return diagonalPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 3;
	}

}
