package modeletetris;

import librairie.Piece;
import librairie.Plateau;
import librairie.Translation;

public class JeuTetris implements Runnable {
	
	private PlateauTetris pl;
	
	public JeuTetris(PlateauTetris pl){
		this.pl = pl;
		(new Thread(this)).start();
	}
	

	/**
	 * Thread du jeu Tetris. Fais descendre une pièce toutes les seconde dans un plateau et vérifie si le sommet n'est pas
	 * atteint. Augmente la vitesse de chute des picèes en fonction du score. Quand on ne peux plus jouer le jeu s'arrête.
	 */
	@Override
	public void run() {
		boolean Ok;
		while(pl.getPieceCourante() != null && pl.isRunning()){

			try {
				if (pl.getScore() != 0){
					Thread.sleep(1000 - (pl.getScore() * 2));
				} else 
					Thread.sleep(1000);
				Piece pc = pl.getPieceCourante();
				Ok = pc.translation(Translation.BAS,pl.getGrille());
				if(Ok){
					pl.setPiece(pc);
				} else {
					pl.addPieceToGrille();
					pl.checkLines();
					pl.setPiece(pl.getNextPiece());	
					pl.setNextPiece();
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pl.setRunning(false);
		System.out.println("You Lose!!!");
		
		

	}

}
