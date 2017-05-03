package Client;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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

	private ArrayList<String> temp = new ArrayList<String>();

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
	        			sm.enterNumber(x, y, Integer.parseInt(newValue));
	        			showTempGameInGUI();
	        	    }
	        		else if (newValue.matches("")) {
	        			textFieldList.get(j).setText("");
	        		}
	        	    else {
	        	    	textFieldList.get(j).setText(oldValue);
	        	    }
				}
	        });
		sm = new SudokuModel();
	    }
	}

	@FXML
	public void showTempGameInGUI(){

		byte tempGame[][] = this.getTempGame();
		int nrTextFeld = 0;
		int j = 0;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				j = tempGame[i][k];
				nrTextFeld = i * 9 + k;
				if (j <= 0) {
					textFieldList.get(nrTextFeld).setText("");
				} else {
					textFieldList.get(nrTextFeld).setText("" + j);
				}
			}
		}
	}

	public byte[][] getTempGame(){
		return sm.getTempGame();
	}

	@FXML
	public void disablePresetFields(){

		boolean changeable[][] = this.getChangeable();
		int nrTextFeld = 0;
		boolean j = false;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				j = changeable[i][k];
				nrTextFeld = i * 9 + k;
				textFieldList.get(nrTextFeld).setEditable(j);
			}
		}
	}

	public boolean[][] getChangeable(){
		return sm.getChangeable();
	}

	@FXML
	public void cleanSudokuField(){
		for (int i = 0; i < 81; i++) {
			textFieldList.get(i).setEditable(true);
			textFieldList.get(i).setText("");
			textFieldList.get(i).setStyle("-fx-background-color: white;");
		}
	}

	@FXML
	public void showTruthInGUI(){

		boolean truth[][] = this.getTruth();
		int nrTextFeld = 0;
		boolean j = false;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				j = truth[i][k];
				nrTextFeld = i * 9 + k;
				if (j && textFieldList.get(nrTextFeld).getText().matches("[1-9]")){
					textFieldList.get(nrTextFeld).setStyle("-fx-background-color: #99ffcc;");	//grün
				} else if (!textFieldList.get(nrTextFeld).getText().matches("[1-9]")) {
					textFieldList.get(nrTextFeld).setStyle("-fx-background-color: white;");		//weiss
				} else {
					textFieldList.get(nrTextFeld).setStyle("-fx-background-color: #ff6699;");	//rot
				}
			}
		}
	}

	public boolean[][] getTruth(){
		return sm.getTruth();
	}

	@FXML
	public void showSolutionInGUI(){

		byte solution[][] = this.getSolution();
		int nrTextFeld = 0;
		int j = 0;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				j = solution[i][k];
				nrTextFeld = i * 9 + k;
				textFieldList.get(nrTextFeld).setText("" + j);
			}
		}
	}

	public byte[][] getSolution(){
		return sm.getSolution();
	}

	public void setTime(String time)
	{
		this.timerLabel.setText(time);
	}

	@FXML
	public void mousePressed()
	{
		//timer.start();
	}
	@FXML
	public void stopEvent()
	{
    	System.out.print("Stop");
    	// timer.stopTimer();
    	timer.interrupt();

	}

	@FXML
	public void createNewGame()
	{
		cleanSudokuField();
		sm.createNewGame();
		showTempGameInGUI();
		disablePresetFields();
	}

	@FXML
	public void restartGame()
	{
		cleanSudokuField();
		sm.reloadGame();
		showTempGameInGUI();
		disablePresetFields();
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

 	@FXML
	public void solveGame()
	{
		sm.solveGame();
		//cleanSudokuField();
		showTempGameInGUI();
		//showSolutionInGUI();
	}

 	@FXML
	public void undoGame()
	{
		sm.undoGame();
		//cleanSudokuField();
		showTempGameInGUI();
	}

 	@FXML
	public void checkGame()
	{
		sm.checkGame();
		showTruthInGUI();
	}
}
