
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modeleplateau.Grille;
import modeleplateau.Jeu;
import modeleplateau.Plateau;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VuePlateau extends Application implements Observer {

	private Plateau plateau;
	private Scene scene;
	private GridPane gPane;
	private BorderPane border;

	@Override
	public void start(Stage primaryStage) {

		// gestion du placement (permet de palcer le champ Text affichage en
		// haut, et GridPane gPane au centre)
		border = new BorderPane();

		// permet de placer les diffrents boutons dans une grille
		gPane = new GridPane();

		plateau = new Plateau();

		Grille grille = plateau.getGrille();

		plateau.addObserver(this);

		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();

		// création des bouton et placement dans la grille
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				gPane.add(new Rectangle(40, 40), i, j);
			}

		}

		gPane.setGridLinesVisible(true);

		border.setCenter(gPane);

		scene = new Scene(border, Color.LIGHTBLUE);

		Jeu jeu = new Jeu(plateau);
		
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
		Plateau pll = (Plateau)o;
		Grille grille = pll.getGrille();
		int column = grille.getNbColonnes();
		int row = grille.getNbLignes();

		// création des bouton et placement dans la grille
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				((Rectangle)gPane.getChildren().get(column*i+j)).setFill(Color.AQUA);
			}

		}
		System.out.println("test");
		
	}

}
