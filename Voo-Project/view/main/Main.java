package main;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.PrincipalController;

public class Main extends Application {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Voo-SQLite");
	public static EntityManager em = emf.createEntityManager();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		PrincipalController principal = new PrincipalController();
		
		Scene scene = new Scene(principal);
		
		
		scene.setFill(Color.TRANSPARENT);

		stage.setScene(scene);
		stage.setOnCloseRequest(we -> System.out.println("Stage is closing"));
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
