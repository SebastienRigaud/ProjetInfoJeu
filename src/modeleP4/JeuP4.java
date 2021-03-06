package modeleP4;

import librairie.Piece;
import librairie.Plateau;
import librairie.Translation;

public class JeuP4 implements Runnable {
	
	private PlateauP4 pl;
	
	public JeuP4(PlateauP4 pl){
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
				} else {
					pl.addPieceToGrille();
					pl.checkLines();
					pl.setPiece(pl.getNextPiece());	
					pl.setNextPiece(new Piece());
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		pl.setRunning(false);
		System.out.println("You Lose!!!");
		
		

	}

}
