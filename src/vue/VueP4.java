
package vue;

import java.util.Observable;
import java.util.Observer;

import com.sun.javafx.css.converters.EnumConverter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import librairie.Case;
import librairie.Couleur;
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;
import librairie.Rotation;
import librairie.Translation;
import modeleP4.JeuP4;
import modeleP4.PlateauP4;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueP4 extends Application implements Observer {

	private Pane globalPane;
	private PlateauP4 plateau;
	private Scene scene;
	private GridPane gPane;
	private BorderPane border;
	

	@Override
	public void start(Stage primaryStage) {

		globalPane = new Pane();
		border = new BorderPane();
		gPane = new GridPane();

		plateau = new PlateauP4();
		Grille grille = plateau.getGrille();

		plateau.addObserver(this);

		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				gPane.add(new Rectangle(30, 30), j, i);
			}

		}		

		gPane.setGridLinesVisible(true);		
		
		globalPane.getChildren().add(border);

		scene = new Scene(globalPane, Color.LIGHTGRAY);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()){
				
				case DOWN:
					//while(plateau.movePiece(Translation.BAS));					
					break;
				case LEFT:
					plateau.movePiece(Translation.GAUCHE);
					break;
				case RIGHT:
					plateau.movePiece(Translation.DROITE);
					break;								
				}}
		});

		JeuP4 jeu = new JeuP4(plateau);
		
		primaryStage.setTitle("Puissance 4");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void update(Observable o, Object arg) {
		PlateauP4 pll = (PlateauP4)o;
		Grille grille = pll.getGrille();
		Case[][] cases_grille = grille.getCases();
		
		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		
		Piece pc = pll.getPieceCourante();
		Case[][] cases = pc.getCases();
	
		Color colorGrille = null;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(cases_grille[i][j] != null){
					switch(cases_grille[i][j].getColor()){
					case YELLOW:
						colorGrille = Color.YELLOW;
						break;
					case RED:
						colorGrille = Color.RED;
						break;
					}
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(colorGrille);
				}else{
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(Color.WHITE);
				}
			}
		}
		
		Color color = computeColor(pc);
		
		for (int i = pc.getCoordy(); i < row; i++) {
			for (int j = pc.getCoordx(); j < column; j++) {
				if(j - pc.getCoordx() >= pc.getTaille() || i - pc.getCoordy() >= pc.getTaille())
					continue;
				if(pc.getCases()[i - pc.getCoordy()][j - pc.getCoordx()] != null)
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(color);
			}
		}		
			
	}
	
	@Override
	public void stop() throws Exception {
		
		plateau.setRunning(false);
	}
	
	public Color computeColor(Piece pc){
		Color color = null;
		switch(pc.getColor()){
		case YELLOW:
			color = Color.YELLOW;
			break;
		case RED:
			color = Color.RED;
			break;
		}
		
		
		return color;
		
	}

}
