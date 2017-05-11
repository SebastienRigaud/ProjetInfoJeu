package modeleplateau;

public class Jeu implements Runnable {
	
	private Plateau pl;
	
	public Jeu(Plateau pl){
		this.pl = pl;
		(new Thread(this)).start();
	}
	

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
			Piece pc = pl.getPieceCourante();
			boolean Ok = pc.translation(Translation.BAS);
			if(!Ok){
				pl.setPiece(pc);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

}
