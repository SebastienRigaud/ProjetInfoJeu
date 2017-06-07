package modeleP4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import librairie.Case;
import librairie.Couleur;
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;

public class PlateauP4 extends Plateau {
	
	private Piece nextPiece;
	private List<Case[][]> listCase;
	private List<Couleur> listCouleur;
	
	public PlateauP4(){
		super();
		listCase = initPieces();
		this.grille = new Grille(7,7);
		this.setPiece(new Piece(listCase.get(1), 4, 0, listCouleur.get(1), listCase.get(1).length));
		this.setNextPiece(new Piece());
	}
	
	public Piece getNextPiece() {
		return nextPiece;
	}

	public void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}

	public List<Case[][]> initPieces(){
		listCase = new ArrayList<Case[][]>();
		listCouleur = new ArrayList<Couleur>();
		int coordy = 0;
		Couleur color;

		color = Couleur.RED;
		Case[][] rouge = new Case[][]{
			{new Case(color)}};

		listCase.add(rouge);
		listCouleur.add(color);
		
		color = Couleur.YELLOW;
		Case[][] jaune = new Case[][]{
			{new Case(color)}};

		listCase.add(jaune);
		listCouleur.add(color);
		return listCase;
	}
	public void checkLines() {
		
	}
	public boolean checkLose(Piece piece){
		
		return false;
	}

}
