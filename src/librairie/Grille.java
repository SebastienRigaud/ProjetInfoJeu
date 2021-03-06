package librairie;

public class Grille {
	
	private Case[][] cases;
	private int nbLignes;
	private int nbColonnes;
	
	
	public Grille(int nbLigne, int nbColonne){
		cases = new Case[nbLigne][nbColonne];
		
		this.setNbLignes(cases.length);
		this.setNbColonnes(cases[0].length);
	}


	public Case[][] getCases() {
		return cases;
	}


	public void setCases(Case[][] cases) {
		this.cases = cases;
	}


	public int getNbLignes() {
		return nbLignes;
	}


	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}


	public int getNbColonnes() {
		return nbColonnes;
	}


	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}
	

}