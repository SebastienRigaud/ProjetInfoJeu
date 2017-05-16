package librairie;


import java.util.Observable;

import javafx.beans.InvalidationListener;
import javafx.scene.paint.Color;



public class Piece {
	private Case[][] cases;
	private int coordx;
	private int coordy;
	private Color color;
	
	private int taille;
	

	public Piece(){
		int rand = (int) (Math.random() * 7)+1;
		coordy = 0;
		switch(rand){
			//Barre
			case 1:
				color = Color.CYAN;
				cases = new Case[][]{
					{null,null,null,null},
					{new Case(color),new Case(color),new Case(color),new Case(color)},
					{null,null,null,null},
					{null,null,null,null}
				};
				taille = 4;
				break;
			//Carré
			case 2:
				color = Color.YELLOW;
				cases = new Case[][]{
					{new Case(color),new Case(color)},
					{new Case(color),new Case(color)}
				};
				taille = 2;
				break;
			//T
			case 3:
				color = Color.MAGENTA;
				cases = new Case[][]{
					{new Case(color),new Case(color),new Case(color)},
					{null,new Case(color),null},
					{null,null,null}
				};
				taille = 3;
				break;
			//L
			case 4:
				color = Color.ORANGE;
				cases = new Case[][]{
					{new Case(color),new Case(color),new Case(color)},
					{new Case(color),null,null},
					{null,null,null}
				};
				taille = 3;
				break;
			//L inversé
			case 5:
				color = Color.BLUE;
				cases = new Case[][]{
					{new Case(color),new Case(color),new Case(color)},
					{null,null,new Case(color)},
					{null,null,null}
				};
				taille = 3;
				break;
			//Biais
			case 6:
				color = Color.RED;
				cases = new Case[][]{
					{new Case(color),new Case(color),null},
					{null,new Case(color),new Case(color)},
					{null,null,null}
				};
				taille = 3;
				break;
			//Biais inversé
			case 7:
				color = Color.GREEN;
				cases = new Case[][]{
					{null,new Case(color),new Case(color)},
					{new Case(color),new Case(color),null},
					{null,null,null}
				};
				taille = 3;
				break;
		}
		coordx = 7-taille;
	}
	
	public boolean translation(Translation t, Grille grille){
		Case[][] grilleJeu = grille.getCases();
		switch(t){
			case DROITE:
				//On regarde les cases les plus a droite
				int coordDroite;
				for(int i = 0; i<taille;i++){
					coordDroite = -1;
					for(int j = taille-1; j>1;j--){
						if(cases[i][j] != null && coordDroite == -1){
							coordDroite = j;
						}
					}
					if(coordDroite != -1){
						if(coordDroite+coordx+1 == grille.getNbColonnes() || grilleJeu[i+coordy][coordDroite+coordx+1] != null){
							return false;
						}
					}
				}
				coordx++;
				break;

			case GAUCHE:
				//On regarde les cases les plus a gauche
				int coordGauche;
				for(int i = 0; i<taille;i++){
					coordGauche = -1;
					for(int j = 0; j<taille;j++){
						if(cases[i][j] != null && coordGauche == -1){
							coordGauche = i;
						}
					}
					if(coordGauche != -1){
						if(coordGauche+coordx == 0 || grilleJeu[i+coordy][coordGauche+coordx-1] != null){
							return false;
						}
					}
				}
				coordx--;
				break;

			case BAS:
				//On regarde les cases les plus basses
				int coordBas;
				for(int i = 0; i<taille;i++){
					coordBas = -1;
					for(int j = taille-1; j>=0;j--){
						if(cases[j][i] != null && coordBas == -1){
							coordBas = j;
						}
					}
					if(coordBas != -1){
						if(coordBas+coordy == grille.getNbLignes()-1 || grilleJeu[coordy+coordBas+1][coordx+i] != null){
							return false;
						}
					}
				}
				coordy++;
				break;
		}
		return true;
	}
	
	public boolean rotation(Rotation r, Grille grille){
		Case[][] grilleJeu = grille.getCases();
		switch (r){
			case DROITE:
				
				for(int i=0; i<taille; i++){
					for(int j=0; j<taille; j++){
						if(cases[j][i] != null && grilleJeu[taille-1-i+coordy][j+coordx]!=null){
							return false;
						}
					}
				}
				cases = doRotationDroite(cases);
				break;
				
			case GAUCHE:
				for(int i=0; i<taille; i++){
					for(int j=0; j<taille; j++){
						if(cases[j][i] != null && grilleJeu[i+coordy][taille-1-j+coordx]!=null){
							return false;
						}
					}
				}
				cases = doRotationGauche(cases);
				break;
		}
		return true;
		
	}
	
	public Case[][] doRotationDroite(Case[][] input){

		int n =input.length;
		int m = input[0].length;
		Case [][] output = new Case [m][n];

		for (int i=0; i<n; i++)
			for (int j=0;j<m; j++)
				output [j][n-1-i] = input[i][j];
		return output;
	}
	
	public Case[][] doRotationGauche(Case[][] input){

		int n =input.length;
		int m = input[0].length;
		Case [][] output = new Case [m][n];

		for (int i=0; i<n; i++)
			for (int j=0;j<m; j++)
				output [m-1-j][i] = input[i][j];
		return output;
	
	}
	
	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}
	
	public int getCoordx() {
		return coordx;
	}

	public void setCoordx(int coordx) {
		this.coordx = coordx;
	}

	public int getCoordy() {
		return coordy;
	}

	public void setCoordy(int coordy) {
		this.coordy = coordy;
	}
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


}
