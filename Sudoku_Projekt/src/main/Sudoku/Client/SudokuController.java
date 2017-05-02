package Client;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javax.swing.*;

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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SudokuController extends Application{
	private Stage primaryStage;
    SudokuTimerTask timer = new SudokuTimerTask(this);

	@FXML
	private List<TextField> textFieldList;
	
	@FXML
	private Label timerLabel;

	SudokuModel sm;
	
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sudoku");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon.png")));

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
	        //SudokuTimerTask timer = new SudokuTimerTask(this);		
 	        //timer.start();		
		sm = new SudokuModel();
	       
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
		//return sm.getTempGame();
		return null;
	}

	public void setTime(String time)
	{
		this.timerLabel.setText(time);
	}
	
	@FXML
	public void mousePressed()
	{
		timer.start();
	}
	@FXML
	public void stopEvent()
	{
    	System.out.print("Stop");
    	// timer.stopTimer();
    	timer.interrupt();

	}
	
 	@FXML		  	
 	public void loadGame()
 	{		  	
 		FileChooser fileChooser = new FileChooser();
 		fileChooser.setTitle("Open Sudoku Game");		
 		fileChooser.setInitialDirectory(new File("./"));		
 		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("txt Files", "*.txt"));		
 		File file = fileChooser.showOpenDialog(primaryStage);		
 		if(file != null)		
 		{		
 			String filename = file.getName();		
 			sm.loadGame(filename);		
 		}		
 	}		  	
 			
 	@FXML		  	
 	public void saveGame()
 	{		
 		this.stopEvent();
 		String filename = JOptionPane.showInputDialog(null,"Unter welchen Namen wollen Sie das Spiel speichern?",
		"Spiel speichern",
        	JOptionPane.PLAIN_MESSAGE);
 		if(filename != null && !filename.isEmpty())		
 		{		
 			sm.saveGame(timerLabel.getText(), filename);		
 			JOptionPane.showMessageDialog(null, "Ihr Spiel wurde erfolgreich gespeichert");		
 		}
 		else
 		{
 			JOptionPane.showMessageDialog(null, "Spielstand wurde nicht gespeichert");
 		}
 	}
}
