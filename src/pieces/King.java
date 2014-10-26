package pieces;
import pieces.AbstractPiece;


public class King extends AbstractPiece {

	public King(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if (isWhite) {
			System.out.print("\u2654");
		} else {
			System.out.print("\u265A");
		}
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return Math.abs(destRow - srcRow) <= 1
				|| Math.abs(destCol - srcCol) <= 1;
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
