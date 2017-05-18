package modeletetris;

import librairie.Case;
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;

public class PlateauTetris extends Plateau {
	
	private Integer score;
	private Piece nextPiece;
	
	public PlateauTetris(){
		super();
		this.grille = new Grille(22,10);
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
		Grille new_grille = grille;
		boolean oneEmpty;
		Case[][] cases;
		for(int i = new_grille.getNbLignes()-1; i>=0; i--){
			oneEmpty = false;
			cases = new_grille.getCases();
			for(int j = 0; j<new_grille.getNbColonnes();j++){
				if(cases[i][j] == null){
					oneEmpty = true;
				}
			}
			if(!oneEmpty){
				//On baisse tout d'une ligne
				for(int i_reverse = i; i_reverse>0; i_reverse--){
					for(int j = 0; j<new_grille.getNbColonnes();j++){
						cases[i_reverse][j] = cases[i_reverse-1][j];
						
					}
				}
				new_grille.setCases(cases);
				setScore(score + 10);
				i++;
			}
		}
	}
	public boolean checkLose(Piece piece){
		for(int i=0; i<piece.getTaille(); i++){
			for(int j=0; j<piece.getTaille(); j++){
				if(piece.getCases()[j][i] != null 
						&& this.getGrille().getCases()[j+piece.getCoordy()][i+piece.getCoordx()] != null){
					return true;
				}
			}
		}
		return false;
	}

}
