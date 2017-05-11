package modeleplateau;

import java.util.Observable;

import javafx.beans.InvalidationListener;



public class Piece {
	private Case[][] cases;
	private int coordx;
	private int coordy;
	private int taille;
	
	public Piece(){
		int rand = (int) (Math.random() * 7);
		coordy = 0;
		switch(rand){
			//Barre
			case 1:
				cases = new Case[][]{
					{null,null,null,null},
					{new Case("cyan"),new Case("cyan"),new Case("cyan"),new Case("cyan")},
					{null,null,null,null},
					{null,null,null,null}
				};
				taille = 4;
				break;
			//Carré
			case 2:
				cases = new Case[][]{
					{new Case("yellow"),new Case("yellow")},
					{new Case("yellow"),new Case("yellow")}
				};
				taille = 2;
				break;
			//T
			case 3:
				cases = new Case[][]{
					{new Case("magenta"),new Case("magenta"),new Case("magenta")},
					{null,new Case("magenta"),null},
					{null,null,null}
				};
				taille = 3;
				break;
			//L
			case 4:
				cases = new Case[][]{
					{new Case("orange"),new Case("orange"),new Case("orange")},
					{new Case("orange"),null,null},
					{null,null,null}
				};
				taille = 3;
				break;
			//L inversé
			case 5:
				cases = new Case[][]{
					{new Case("blue"),new Case("blue"),new Case("blue")},
					{null,null,new Case("blue")},
					{null,null,null}
				};
				taille = 3;
				break;
			//Biais
			case 6:
				cases = new Case[][]{
					{new Case("red"),new Case("red"),null},
					{null,new Case("red"),new Case("red")},
					{null,null,null}
				};
				taille = 3;
				break;
			//Biais inversé
			case 7:
				cases = new Case[][]{
					{null,new Case("green"),new Case("green")},
					{new Case("green"),new Case("green"),null},
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
						if(cases[j][i] != null && coordDroite != -1){
							coordDroite = j;
						}
					}
					if(coordDroite != -1){
						if(coordDroite+coordx+1 == grille.getNbColonnes()-1 || grilleJeu[coordDroite+coordx+1][i+coordy] != null){
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
						if(cases[j][i] != null && coordGauche != -1){
							coordGauche = j;
						}
					}
					if(coordGauche != -1){
						if(coordGauche+coordx == 0 || grilleJeu[coordGauche+coordx-1][i+coordy] != null){
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
					for(int j = taille-1; j>1;j--){
						if(cases[i][j] != null && coordBas != -1){
							coordBas = j;
						}
					}
					if(coordBas != -1){
						if(coordBas+coordy == grille.getNbLignes()-1 || grilleJeu[coordx+i][coordy+coordBas-1] != null){
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
						if(cases[i][j] != null && grilleJeu[j+coordx][taille-1-i+coordy]!=null){
							return false;
						}
					}
				}
				cases = doRotationDroite(cases);
				break;
				
			case GAUCHE:
				for(int i=0; i<taille; i++){
					for(int j=0; j<taille; j++){
						if(cases[i][j] != null && grilleJeu[taille-1-j+coordx][i+coordy]!=null){
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
}
