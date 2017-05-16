package modeleplateau;

public class Jeu implements Runnable {
	
	private Plateau pl;
	
	public Jeu(Plateau pl){
		this.pl = pl;
		(new Thread(this)).start();
	}
	

	@Override
	public void run() {
		boolean Ok;
		while(true){

			try {
				Thread.sleep(1000);
				Piece pc = pl.getPieceCourante();
				Ok = pc.translation(Translation.BAS,pl.getGrille());
				if(Ok){
					pl.setPiece(pc);
				}
				else {
					pl.setPiece(new Piece());
				}
				System.out.println("Thread running");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}

}
