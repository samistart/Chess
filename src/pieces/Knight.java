package pieces;


public class Knight extends pieces.AbstractPiece {

	public Knight(boolean isWhite) {
		super(isWhite);
			}

	@Override
	public void draw() {
		if (isWhite){
			System.out.print("\u2658");
		}
		else{
			System.out.print("\u265E");
		}		
	}
	
	private static Boolean lShapedPath(int srcRow, int srcCol,
			int destRow, int destCol) {
		// returns true if the path is L-shaped
		// arguments are initial and final coordinates of move in chessboard
		// array
		// good for checking if a move is valid
		return ((Math.abs(srcRow - destRow) == 2 && Math.abs(srcCol
				- destCol) == 1)
				|| (Math.abs(srcRow - destRow) == 1 && Math.abs(srcCol
						- destCol) == 2));
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		return lShapedPath(srcRow, srcCol, destRow, destCol);
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
