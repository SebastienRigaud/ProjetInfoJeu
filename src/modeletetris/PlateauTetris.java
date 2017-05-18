package modeletetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import librairie.Case;
import librairie.Couleur;
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;

public class PlateauTetris extends Plateau {
	
	private Integer score;
	private Piece nextPiece;
	private List<Case[][]> listCase;
	private List<Couleur> listCouleur;
	
	public PlateauTetris(){
		super();
		listCase = initPieces();
		int rand = (new Random()).nextInt(7);
		this.setPiece(new Piece(listCase.get(rand), 5 - listCase.get(rand).length/2, 0, listCouleur.get(rand), listCase.get(rand).length));
		this.setScore(0);
		this.setNextPiece();
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

	public void setNextPiece() {
		int rand = (new Random()).nextInt(7);
		this.nextPiece = new Piece(listCase.get(rand), 5 - listCase.get(rand).length/2, 0, listCouleur.get(rand),listCase.get(rand).length);
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
	
	public List<Case[][]> initPieces(){
		listCase = new ArrayList<Case[][]>();
		listCouleur = new ArrayList<Couleur>();
		int coordy = 0;
		Couleur color;

		color = Couleur.CYAN;
		Case[][] pieceBarre = new Case[][]{
			{null,null,null,null},
			{new Case(color),new Case(color),new Case(color),new Case(color)},
			{null,null,null,null},
			{null,null,null,null}};

		listCase.add(pieceBarre);
		listCouleur.add(color);
		
		color = Couleur.YELLOW;
		Case[][] pieceCarre = new Case[][]{
			{new Case(color),new Case(color)},
			{new Case(color),new Case(color)}
		};

		listCase.add(pieceCarre);
		listCouleur.add(color);

		color = Couleur.MAGENTA;
		Case[][] pieceT = new Case[][]{
			{new Case(color),new Case(color),new Case(color)},
			{null,new Case(color),null},
			{null,null,null}
		};

		listCase.add(pieceT);
		listCouleur.add(color);

		color = Couleur.ORANGE;
		Case[][] pieceL =new Case[][]{
			{new Case(color),new Case(color),new Case(color)},
			{new Case(color),null,null},
			{null,null,null}
		};

		listCase.add(pieceL);
		listCouleur.add(color);
		
		color = Couleur.BLUE;
		Case[][] pieceLInverse = new Case[][]{
			{new Case(color),new Case(color),new Case(color)},
			{null,null,new Case(color)},
			{null,null,null}
		};

		listCase.add(pieceLInverse);
		listCouleur.add(color);

		color = Couleur.GREEN;
		Case[][] pieceBiais = new Case[][]{
			{new Case(color),new Case(color),null},
			{null,new Case(color),new Case(color)},
			{null,null,null}
		};

		listCase.add(pieceBiais);
		listCouleur.add(color);

		color = Couleur.RED;
		Case[][]pieceBiaisInverse = new Case[][]{
			{null,new Case(color),new Case(color)},
			{new Case(color),new Case(color),null},
			{null,null,null}
		};

		listCase.add(pieceBiaisInverse);
		listCouleur.add(color);
		
		return listCase;
		
	}

}
