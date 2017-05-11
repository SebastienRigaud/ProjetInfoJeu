package modeleplateau;

public class Jeu implements Runnable {
	
	private Plateau pl;
	
	public Jeu(Plateau pl){
		this.pl = pl;
		(new Thread(this)).start();
	}
	

	@Override
	public void run() {
		while(true){

			try {
				Thread.sleep(1000);
				Piece pc = pl.getPieceCourante();
				boolean Ok = pc.translation(Translation.BAS,pl.getGrille());
				if(Ok){
					pl.setPiece(pc);
				}
				System.out.println("POUR LE COMMIT");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}
