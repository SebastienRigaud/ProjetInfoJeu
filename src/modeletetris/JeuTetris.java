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
	

	@Override
	public void run() {
		boolean Ok;
		while(pl.getPieceCourante() != null && pl.isRunning()){

			try {
				Thread.sleep(1000);
				Piece pc = pl.getPieceCourante();
				Ok = pc.translation(Translation.BAS,pl.getGrille());
				if(Ok){
					pl.setPiece(pc);
				}
				else {
					pl.addPieceToGrille();
					pl.checkLines();
					pl.setPiece(new Piece());
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("You Lose!!!");
		
		

	}

}
