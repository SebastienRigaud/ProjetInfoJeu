package modeleP4;

import librairie.Case;
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;

public class PlateauP4 extends Plateau {
	
	private Integer score;
	private Piece nextPiece;
	
	public PlateauP4(){
		super();
		this.grille = new Grille(6,7);
		this.setScore(0);
		this.setNextPiece(new Piece());
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Piece getNextPiece() {
		return nextPiece;
	}

	public void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}


	public void checkLines() {
		
	}
	public boolean checkLose(Piece piece){
		
		return false;
	}

}
