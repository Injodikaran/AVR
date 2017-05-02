package Client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SudokuController extends Application{
	private Stage primaryStage;

	@FXML
	private List<TextField> textFieldList;
	
	@FXML
	private Label timerLabel;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sudoku");

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SudokuView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        launch(args);
    }

    int i;
	@FXML
	public void initialize() {

		for (i = 0; i < 81; i++) {
			
	        textFieldList.get(i).textProperty().addListener(new ChangeListener<String>(){

	        	@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        		if (newValue.matches("[0-9]")) {
	        			textFieldList.get(i).setText(newValue);        			
	        	      }
	        	    else {
	        	    	textFieldList.get(i).setText(newValue);
	        	      }
				}
	        });
	        SudokuTimerTask timer = new SudokuTimerTask(this);
	        timer.start();
	       
	    }
	}
	
	public void setTime(String time)
	{
		this.timerLabel.setText(time);
	}
}
