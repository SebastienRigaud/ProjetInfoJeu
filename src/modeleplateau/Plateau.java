package modeleplateau;

import java.util.Observable;

public class Plateau extends Observable {
	
	private Piece piece;
	private Grille grille;
	
	public Plateau(){
		this.setPiece(new Piece());
		this.grille = new Grille();
	}

	public Grille getGrille() {
		return grille;
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

	public void addPieceToGrille() {
		Grille new_grille = this.getGrille();
		Case[][] new_cases = new_grille.getCases();
		Case[][] cases_piece = piece.getCases();
		int coordx = piece.getCoordx();
		int coordy = piece.getCoordy();
		for(int i = 0; i < piece.getTaille(); i++){
			for(int j = 0; j < piece.getTaille(); j++){
				if(cases_piece[j][i] != null){
					new_cases[coordy+j][coordx+i] = new Case(piece.getColor());					
				}
			}
		}
		new_grille.setCases(new_cases);
		this.setGrille(new_grille);
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
				//On baisse tous d'une ligne
				for(int i_reverse = i; i_reverse<new_grille.getNbLignes()-1; i_reverse++){
					for(int j = 0; j<new_grille.getNbColonnes();j++){
						cases[i_reverse][j] = cases[i_reverse-1][j];
					}
				}
				new_grille.setCases(cases);
				i++;
			}
		}
	}

}
