package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sudoku");

		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GUI_Extended.fxml"));
			
			// Timer
			SudokuTimerTask timerTask = new SudokuTimerTask();
			timerTask.start();
			String time = timerTask.getTime();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void newGame(){
		System.out.println("TEst");
	}

	public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
