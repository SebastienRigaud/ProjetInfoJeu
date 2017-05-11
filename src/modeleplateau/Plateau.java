package modeleplateau;

import java.util.Observable;

public class Plateau extends Observable {
	
	private Piece piece;
	private Grille grille;
	
	public Plateau(){
		this.setPiece(new Piece());
	}

	public Grille getGrille() {
		return new Grille();
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		setChanged();
		notifyObservers();
		
	}

	public Piece getPieceCourante() {
		return this.getPiece();
	}

}
