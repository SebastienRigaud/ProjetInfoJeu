
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import librairie.Grille;
import librairie.Piece;
import librairie.Plateau;
import librairie.Rotation;
import librairie.Translation;
import modeletetris.JeuTetris;
import modeletetris.PlateauTetris;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueTetris extends Application implements Observer {

	private PlateauTetris plateau;
	private Scene scene;
	private GridPane gPane;
	private BorderPane border;
	private GridPane gPaneMenu;
	private FlowPane leftPane;
	
	private Button buttonRestart;
	private Label gameLabel;
	private Label textScoreLabel;
	private Text scoreText;

	@Override
	public void start(Stage primaryStage) {

		border = new BorderPane();
		gPane = new GridPane();
		gPaneMenu = new GridPane();
		leftPane = new FlowPane();

		plateau = new PlateauTetris();
		Grille grille = plateau.getGrille();

		plateau.addObserver(this);

		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				gPane.add(new Rectangle(40, 40), j, i);
			}

		}

		gPane.setGridLinesVisible(true);
		gPaneMenu.setMinSize(300, 300);
		
		gameLabel = new Label("TETRIS");
		gameLabel.setFont(new Font("Calibri",32));
		gPaneMenu.add(gameLabel, 5, 1);
		
		textScoreLabel = new Label("Score :");
		textScoreLabel.setFont(new Font("Calibri",32));
		gPaneMenu.add(textScoreLabel, 5, 2);
		
		scoreText = new Text("");
		scoreText.setFont(new Font("Calibri",32));
		gPaneMenu.add(scoreText, 7, 2);
		
		buttonRestart = new Button("Restart Game");
		buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("test");
				
			}
		});
		gPaneMenu.add(buttonRestart, 15, 15);
		
		
		
		border.setCenter(gPane);
		border.setRight(gPaneMenu);

		scene = new Scene(border, Color.LIGHTBLUE);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()){
				
				case DOWN:
					plateau.movePiece(Translation.BAS);
					break;
				case LEFT:
					plateau.movePiece(Translation.GAUCHE);
					break;
				case RIGHT:
					plateau.movePiece(Translation.DROITE);
					break;
				case UP:
					plateau.movePiece(Translation.BAS);
					break;
				case A:
					plateau.rotatePiece(Rotation.GAUCHE);
					break;
				case Z:
					plateau.rotatePiece(Rotation.DROITE);
					break;
	
				}}
		});

		JeuTetris jeu = new JeuTetris(plateau);
		
		primaryStage.setTitle("Tetris");
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
		PlateauTetris pll = (PlateauTetris)o;
		Grille grille = pll.getGrille();
		Case[][] cases_grille = grille.getCases();
		
		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		
		Piece pc = pll.getPieceCourante();
		Case[][] cases = pc.getCases();
		int coordx = pc.getCoordx();
		int coordy = pc.getCoordy();
	
		Color colorGrille = null;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(cases_grille[i][j] != null){
					switch(cases_grille[i][j].getColor()){
					case BLUE:
						colorGrille = Color.BLUE;
						break;
					case  CYAN:
						colorGrille = Color.CYAN;
						break;
					case GREEN:
						colorGrille = Color.GREEN;
						break;
					case YELLOW:
						colorGrille = Color.YELLOW;
						break;
					case ORANGE:
						colorGrille = Color.ORANGE;
						break;
					case RED:
						colorGrille = Color.RED;
						break;
					case MAGENTA:
						colorGrille = Color.MAGENTA;
						break;
					}
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(colorGrille);
				}else{
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(Color.WHITE);
				}
			}
		}
		
		Color color = null;
		switch(pc.getColor()){
		case BLUE:
			color = Color.BLUE;
			break;
		case  CYAN:
			color = Color.CYAN;
			break;
		case GREEN:
			color = Color.GREEN;
			break;
		case YELLOW:
			color = Color.YELLOW;
			break;
		case ORANGE:
			color = Color.ORANGE;
			break;
		case RED:
			color = Color.RED;
			break;
		case MAGENTA:
			color = Color.MAGENTA;
			break;
		}
		
		for (int i = pc.getCoordy(); i < row; i++) {
			for (int j = pc.getCoordx(); j < column; j++) {
				if(j - pc.getCoordx() >= pc.getTaille() || i - pc.getCoordy() >= pc.getTaille())
					continue;
				if(pc.getCases()[i - pc.getCoordy()][j - pc.getCoordx()] != null)
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(color);
			}
		}
		
		scoreText.setText(pll.getScore().toString());
	}
	
	@Override
	public void stop() throws Exception {
		plateau.setRunning(false);
	}

}
