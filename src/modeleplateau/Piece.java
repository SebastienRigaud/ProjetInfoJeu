package modeleplateau;

import java.util.Observable;

import javafx.beans.InvalidationListener;



public class Piece {
	
	private Case[][] cases;
	
	
	
	public boolean rotation (Rotation rotation){
		return false;
	}
	
	public boolean translation (Translation translation){
		descendre();
		
		return false;
	}

	private void descendre() {
		
	}


}
