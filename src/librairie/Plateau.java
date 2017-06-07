package librairie;


import java.util.Observable;


public class Plateau extends Observable {
	
	protected Piece piece;
	protected Grille grille;
	protected boolean isRunning;


	public Plateau(){
		this.grille = new Grille(22,10);
		this.setPiece(new Piece());
		this.setRunning(true);
				
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
		setChanged();
		notifyObservers();
	}



	public synchronized void setPiece(Piece piece) {
		if(!this.checkLose(piece)){
			this.piece = piece;
			setChanged();
			notifyObservers();			
		}else{
			this.piece = null;
		}
	}
	public synchronized Piece getPieceCourante() {
		return piece;
	}

	
	/**
	 * Ajoute une pièce dans la grille
	 */
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
	
	public boolean checkLose(Piece piece){
		return false;
	}

	/**
	 * Appelle la translation souhaitée.
	 * @param type - Le type de translation (Tiré de l'énumération Translation).
	 */
	public boolean movePiece(Translation type) {
		boolean b = true;
		switch(type) {
			case BAS:
				b = piece.translation(type, grille);
				break;
			case GAUCHE:
				b = piece.translation(type, grille);
				break;
			case DROITE:
				b = piece.translation(type, grille);
				break;
				
		}
		setChanged();
		notifyObservers();
		return b;
	}

	/**
	 * Appelle la rotation souhaitée
	 * @param type - Le type de rotation (Tiré de l'énumération Rotation)
	 */
	public void rotatePiece(Rotation type) {
		switch(type){
			case GAUCHE:
				piece.rotation(type, grille);
				break;
			case DROITE:
				piece.rotation(type, grille);
				break;
		}
		setChanged();
		notifyObservers();
		
	}

}
