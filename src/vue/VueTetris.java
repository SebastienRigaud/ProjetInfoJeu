
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
import modeletetris.JeuTetris;
import modeletetris.PlateauTetris;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueTetris extends Application implements Observer {

	private Pane globalPane;
	private PlateauTetris plateau;
	private Scene scene;
	private GridPane gPane;
	private BorderPane border;
	private BorderPane gPaneMenu;
	private Pane leftPane;
	private GridPane holdPane;
	
	private ImageView viewLogo;
	
	private Label gameLabel;
	private Label textScoreLabel;
	private Text scoreText;
	private Text overText;
	
	@Override
	public void start(Stage primaryStage) {

		globalPane = new Pane();
		border = new BorderPane();
		gPane = new GridPane();
		gPaneMenu = new BorderPane();
		leftPane = new Pane();
		holdPane = new GridPane();

		plateau = new PlateauTetris();
		Grille grille = plateau.getGrille();

		plateau.addObserver(this);

		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				gPane.add(new Rectangle(30, 30), j, i);
			}

		}
		
		int holdColumn = 4; int holdRow = 4;
		for (int i = 0; i < holdRow; i++) {
			for (int j = 0; j < holdColumn; j++) {
				holdPane.add(new Rectangle(40, 40), j, i);
			}

		}

		gPane.setGridLinesVisible(true);
		//gPaneMenu.setMinSize(300, 300);
		
		scoreText = new Text("Score : ");
		scoreText.setFont(new Font("Calibri",32));
		gPaneMenu.setTop(scoreText);
		
		overText = new Text("Game over");
		overText.setFont(new Font("Calibri",32));
		gPaneMenu.setBottom(overText);
		overText.setVisible(false);
		
		holdPane.setGridLinesVisible(true);
		gPaneMenu.setCenter(holdPane);	
		
		viewLogo = new ImageView();
		viewLogo.setImage(new Image("Ressources/TetrisLogo.jpg"));
		leftPane.getChildren().add(viewLogo);
		
		border.setCenter(gPane);
		border.setRight(gPaneMenu);
		border.setLeft(leftPane);
		
		globalPane.getChildren().add(border);
		
		Image backgroundImage = new Image("Ressources/tetris.jpeg");
		BackgroundImage background = new BackgroundImage(backgroundImage,null,null,null,null);
		Background back = new Background(background);
		globalPane.setBackground(back);

		scene = new Scene(globalPane, Color.LIGHTGRAY);
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
		
		if(!pll.isRunning()){
			overText.setVisible(true);
			return;
		}
		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();
		
		Piece pc = pll.getPieceCourante();
		Case[][] cases = pc.getCases();

		
		Piece nextPc = pll.getNextPiece();
		Case[][] nextCases = nextPc.getCases();
	
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
		
		Color color = computeColor(pc);
		
		for (int i = pc.getCoordy(); i < row; i++) {
			for (int j = pc.getCoordx(); j < column; j++) {
				if(j - pc.getCoordx() >= pc.getTaille() || i - pc.getCoordy() >= pc.getTaille())
					continue;
				if(pc.getCases()[i - pc.getCoordy()][j - pc.getCoordx()] != null)
					((Rectangle)gPane.getChildren().get(column*i+j)).setFill(color);
			}
		}
		
		Color colorHold = computeColor(nextPc);
		
		for (int i = 0; i < 16; i++) {
			((Rectangle)holdPane.getChildren().get(i)).setFill(Color.WHITE);
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//((Rectangle)holdPane.getChildren().get(i+j)).setFill(Color.WHITE);
				if(j>= nextPc.getTaille() || i>= nextPc.getTaille())
					continue;
				if(nextPc.getCases()[i][j] != null)
					((Rectangle)holdPane.getChildren().get(4*i+j)).setFill(colorHold);
					
			}
		}
		
		scoreText.setText("Score :"+ pll.getScore().toString());
		
		if(!pll.isRunning()){
			overText.setVisible(true);
			return;
		}
		
			
	}
	
	@Override
	public void stop() throws Exception {
		
		plateau.setRunning(false);
	}
	
	/**
	 * Récupère la couleur en fonction de la pièce.
	 * @param pc - La pièce.
	 * @return
	 */
	public Color computeColor(Piece pc){
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
		
		
		return color;
		
	}

}
