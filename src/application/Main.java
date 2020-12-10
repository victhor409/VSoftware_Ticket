package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage PrimaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
		
			mainScene = new Scene(scrollPane);
			PrimaryStage.setScene(mainScene);
			PrimaryStage.setTitle("VS Soft");
			PrimaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
}