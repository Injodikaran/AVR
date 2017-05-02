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
	SudokuModel sm = new SudokuModel();

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

	@FXML
	public void initialize() {

		for (int i = 0; i < 81; i++) {

			final int j = i;
			final int x = j / 9;
			final int y = j % 9;

	        textFieldList.get(j).textProperty().addListener(new ChangeListener<String>(){
	        	@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        		if (newValue.matches("[1-9]")) {
	        			textFieldList.get(j).setText(newValue);
	        			//sm.enterNumber(x, y, Integer.parseInt(newValue));

	        			//Test ob richtige Zeile und Spalte übergeben wird
	        			System.out.println("******************************");
	        			System.out.println("Textfeld = " + j);
	        			System.out.println("Zeile = " + x);
	        			System.out.println("Spalte = " + y);
	        			System.out.println("Neuer Wert = " + newValue);
	        			System.out.println("******************************");
	        			showTempGameInGUI();
	        	    }
	        	    else {
	        	    	textFieldList.get(j).setText(oldValue);
	        	    }


				}
	        });
	        SudokuTimerTask timer = new SudokuTimerTask(this);
	        timer.start();

	    }
	}

	public void showTempGameInGUI(){

		byte tempGame[][] = this.getTempGame();
		int nrTextFeld = 0;
		int j = 0;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				j = tempGame[i][k];
				nrTextFeld = i * 9 + k;
				textFieldList.get(nrTextFeld).setText("" + j);
			}
		}
	}

	public byte[][] getTempGame(){
		return sm.getTempGame();
	}

	public void setTime(String time)
	{
		this.timerLabel.setText(time);
	}
}
