package Chess;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;

public class ChessMatch {

	private Board board;

	public ChessMatch() {

		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {

				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validationSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMovie(source, target);
		return (ChessPiece)capturedPiece; 
		
	}
	
	private Piece  makeMovie(Position source, Position target) {
		
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	
	private void validationSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is  no piece on source position");
		}
		if(!board.piece(position).isThereAnyPossibleMovie()) {
			throw new ChessException("There is possible Movies for chose piece");
		}
	}
	
	public void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece cant move totarget position");
		}
	}
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());		
	}

	public void initialSetup() {     placeNewPiece('c', 2, new Rook(board, Color.WHITE));
    placeNewPiece('d', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 2, new Rook(board, Color.WHITE));
    placeNewPiece('e', 1, new Rook(board, Color.WHITE));
    placeNewPiece('d', 1, new King(board, Color.WHITE));

    placeNewPiece('c', 7, new Rook(board, Color.BLACK));
    placeNewPiece('c', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 7, new Rook(board, Color.BLACK));
    placeNewPiece('e', 8, new Rook(board, Color.BLACK));
    placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
