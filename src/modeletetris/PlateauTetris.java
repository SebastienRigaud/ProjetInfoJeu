package modeletetris;

import librairie.Case;
import librairie.Grille;
import librairie.Plateau;

public class PlateauTetris extends Plateau {
	
	private Integer score;
	
	public PlateauTetris(){
		super();
		this.setScore(0);
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

}
