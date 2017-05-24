package Client;

import java.awt.MouseInfo;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javax.swing.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sudokuService.SudokuModel;

public class SudokuViewModel extends Application{
	private Stage primaryStage;
	SudokuTimerTask timer = new SudokuTimerTask(this);
	CommandManager cm = new CommandManager();

	private SingletonDataStore datastore = SingletonDataStore.getInstance();

	@FXML
	private Button number1;
	@FXML
	private Button number2;
	@FXML
	private Button number3;
	@FXML
	private Button number4;
	@FXML
	private Button number5;
	@FXML
	private Button number6;
	@FXML
	private Button number7;
	@FXML
	private Button number8;
	@FXML
	private Button number9;
	@FXML
	private Button delete;
	@FXML
	private List<TextField> textFieldList;
	@FXML
	URL location;
	@FXML
	private Pane numberSelectionBasicPane;
	@FXML
	private Label timerLabel;
	@FXML
	private ToggleButton checkGame;

	SudokuModel service;

	private boolean solvedWithCheck = false;
	private int anzRichtige = 0;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sudoku");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon.png")));

		// Threads beenden
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		try {
			Parent root = FXMLLoader.load(getClass().getResource("SudokuView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("stylesheet.css");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
=======

>>>>>>> origin/master
	}

	/**
	 * Reagiert auf den Toggle Button "Check" und zeigt richtige und falsche Ziffern
	 * welche bereits eingegeben wurden, wenn der Button gedrückt ist. Ist der Button nicht
	 * gedrückt wird die Kontrollanzeige wieder rückgängig gemacht
	 */
	@FXML
	protected void handleCheckGameButtonAction(ActionEvent event){
		if (checkGame.isSelected()) {
			checkGame();
			checkGame.setText("Uncheck");
		} else {
			uncheckGame();
			checkGame.setText("Check");
		}
	}

	public void hideWindows(){
		datastore.getStage().close();
	}

	public void showNumbersChooser(MouseEvent mouse) throws IOException{
		try {
			datastore.setSelectedTextField((TextField) mouse.getSource());
			if(datastore.getSelectedTextField().isEditable()==true){
				primaryStage = new Stage();
				Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("Eingabeziffern.fxml"));
				Scene numbers = new Scene(root);
				numbers.getStylesheets().add("stylesheet.css");
				primaryStage.setScene(numbers);
				primaryStage.setTitle("Number Selection");
				primaryStage.initModality(Modality.APPLICATION_MODAL);
				primaryStage.initStyle(StageStyle.UNDECORATED);
				primaryStage.setX(MouseInfo.getPointerInfo().getLocation().x);
				primaryStage.setY(MouseInfo.getPointerInfo().getLocation().y);
				primaryStage.show();
				datastore.setStage(primaryStage);

			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void numberSelection(ActionEvent action) throws IOException{
		try {
			primaryStage = datastore.getStage();
			if(action.getSource() == number1){
				datastore.getSelectedTextField().setText("1");
			}else if(action.getSource() == number2){
				datastore.getSelectedTextField().setText("2");
			}else if(action.getSource() == number3){
				datastore.getSelectedTextField().setText("3");
			}else if(action.getSource() == number4){
				datastore.getSelectedTextField().setText("4");
			}else if(action.getSource() == number5){
				datastore.getSelectedTextField().setText("5");
			}else if(action.getSource() == number6){
				datastore.getSelectedTextField().setText("6");
			}else if(action.getSource() == number7){
				datastore.getSelectedTextField().setText("7");
			}else if(action.getSource() == number8){
				datastore.getSelectedTextField().setText("8");
			}else if(action.getSource() == number9){
				datastore.getSelectedTextField().setText("9");
			}else if(action.getSource() == delete){
				datastore.getSelectedTextField().clear();
			}
			primaryStage.close();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Reagiert auf eine Änderung im Sudoku Spielfeld
	 * und schreibt diese ins aktuelle Spiel im Sudoku Model
	 */
	@FXML
	public void initialize() {
		if(location.getPath().endsWith("SudokuView.fxml")){
			for (int i = 0; i < 81; i++) {

				final int j = i;
				final int x = j / 9;
				final int y = j % 9;


				textFieldList.get(j).textProperty().addListener(new ChangeListener<String>(){

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						if (newValue.matches("[1-9]")) {
							cm.executeCommand(new FillField(textFieldList.get(j), newValue, oldValue));
							service.enterNumber(x, y, Integer.parseInt(newValue));
							showTempGameInGUI();
							System.out.println("SolvedWithCheck:" + solvedWithCheck);
							if (!solvedWithCheck){
								checkUserGame();
							}
						}
						else if (newValue.matches("")) {
							service.enterNumber(x, y, 0);
							showTempGameInGUI();
							textFieldList.get(j).setText("");
						}
						else {
							textFieldList.get(j).setText(oldValue);
						}
					}
				});
			}
		}
		else
		{
			fadeTransition(numberSelectionBasicPane);
		}
		service = new SudokuModel();
	}

	private void fadeTransition(Node e){
		FadeTransition x= new FadeTransition(new javafx.util.Duration(1000),e);
		x.setFromValue(0);
		x.setToValue(100);
		x.setCycleCount(1);
		x.setInterpolator(Interpolator.LINEAR);
		x.play();
	}

	/**
	 * Zeigt das aktuelle Spiel im Sudoku Spielfeld
	 */
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

	/**
	 * Holt das aktuelle Spiel aus dem Sudoku Model
	 */
	public byte[][] getTempGame(){
		return service.getTempGame();
	}

	/**
	 * Sperrt die bereits ausgefüllten Felder beim Laden eines neuen Spiels
	 */
	@FXML
	public void disablePresetFields(){

		boolean changeable[][] = this.getChangeable();
		int nrTextFeldDisable = 0;
		boolean jDisable = false;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				jDisable = changeable[i][k];
				nrTextFeldDisable = i * 9 + k;
				textFieldList.get(nrTextFeldDisable).setEditable(jDisable);
			}
		}
	}

	/**
	 * Holt die veränderbaren Sudokufelder aus dem Sudoku Model
	 */
	public boolean[][] getChangeable(){
		return service.getChangeable();
	}

	/**
	 * Reinigt das Sudoku Spielfeld:
	 * - Alle Felder wieder editierbar
	 * - Alle Felder ohne Ziffern
	 * - Alle Felder mit weissem Hintergrund
	 */
	@FXML
	public void cleanSudokuField(){
		for (int i = 0; i < 81; i++) {
			textFieldList.get(i).setEditable(true);
			textFieldList.get(i).setText("");
			textFieldList.get(i).setStyle("-fx-background-color: white;");
		}
	}

	/**
	 * Zeigt die richtigen und falschen Ziffern mit grünem und rotem Hintergrund
	 * im Sudoku Spielfeld an
	 */
	@FXML
	public void showTruthInGUI(){

		boolean truth[][] = this.getTruth();
		int nrTextFeldTruth = 0;
		boolean jTruth = false;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				jTruth = truth[i][k];
				nrTextFeldTruth = i * 9 + k;
				if (jTruth && textFieldList.get(nrTextFeldTruth).getText().matches("[1-9]")){
					textFieldList.get(nrTextFeldTruth).setStyle("-fx-background-color: #99ffcc;");	//grÃ¼n
				} else if (!textFieldList.get(nrTextFeldTruth).getText().matches("[1-9]")) {
					textFieldList.get(nrTextFeldTruth).setStyle("-fx-background-color: white;");		//weiss
				} else {
					textFieldList.get(nrTextFeldTruth).setStyle("-fx-background-color: #ff6699;");	//rot
				}
			}
		}
	}

	/**
	 * Macht die Anzeige, ob eine Ziffer richtig (grün) oder falsch (rot) ist, wieder rückgängig
	 */
	public void uncheckGame(){
		for (int i = 0; i < 81; i++) {
			textFieldList.get(i).setStyle("-fx-background-color: white;");
		}
	}

	/**
	 * Holt zu jeder Ziffer die Information, ob eine Zahl richtig oder falsch ist
	 */
	public boolean[][] getTruth(){
		return service.getTruth();
	}

	/**
	 * Zeigt die komplette Lösung im Spielfeld an
	 */
	@FXML
	public void showSolutionInGUI(){

		byte solution[][] = this.getSolution();
		int nrTextFeldSolution = 0;
		int jSolution = 0;

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				jSolution = solution[i][k];
				nrTextFeldSolution = i * 9 + k;
				textFieldList.get(nrTextFeldSolution).setText("" + jSolution);
			}
		}
	}

	/**
	 * Holt die Lösung aus dem Sudoku Model
	 */
	public byte[][] getSolution(){
		return service.getSolution();
	}

	public void setTime(String time)
	{
		this.timerLabel.setText(time);
	}

	@FXML
	public void mousePressed()
	{
		if(!timer.isAlive())
		{
			timer.start();
		}else{timer.resumeThread();}
	}

	public void resetEvent()
	{
		if(timer.isAlive())
		{
			timer.resetThread();
		}
	}


	/**
	 * Startet ein neues Spiel
	 */
	@FXML
	public void createNewGame() throws InterruptedException
	{
		solvedWithCheck = false;
		timer.pauseThread();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Neues Spiel");
		alert.setHeaderText("Wollen Sie ein neues Spiel starten?");

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("images/icon.png").toString()));

		ButtonType buttonTypeYes = new ButtonType("Ja");
		ButtonType buttonTypeNo = new ButtonType("Nein");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeYes){
			this.resetEvent();
			cleanSudokuField();
			service.createNewGame();
			showTempGameInGUI();
			disablePresetFields();
			cm.deleteHistory();
		} else if (result.get() == buttonTypeNo) {
			timer.resumeThread();
		} else {
			timer.resumeThread();
		}
	}

	/**
	 * Setzt das aktuelle Spiel wieder auf den Ursprung zurück
	 */
	@FXML
	public void restartGame()
	{
		solvedWithCheck = false;
		cleanSudokuField();
		service.reloadGame();
		showTempGameInGUI();
		disablePresetFields();
		service.resetstacks();
		this.resetEvent();
	}

	/**
	 * Lädt ein abgespeichertes Spiel
	 */
 	@FXML
 	public void loadGame()
 	{
 		solvedWithCheck = false;
 		FileChooser fileChooser = new FileChooser();
 		fileChooser.setTitle("Open Sudoku Game");
 		fileChooser.setInitialDirectory(new File("./"));
 		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("txt Files", "*.txt"));
 		File file = fileChooser.showOpenDialog(primaryStage);
 		if(file != null)
 		{
 			String filename = file.getName();
 	 		cleanSudokuField();
 			service.loadGame(filename);
 	 		this.resetEvent();
 	 		showTempGameInGUI();
 			disablePresetFields();
 		}
 	}

 	/**
	 * Speichert das aktuelle Spiel
	 */
 	@FXML
 	public void saveGame() throws InterruptedException
 	{
 		timer.pauseThread();
 		TextInputDialog dialog = new TextInputDialog();
 		dialog.setTitle("Spiel speichern");
 		dialog.setHeaderText("Unter welchen Namen wollen Sie das Spiel speichern?");
 		Stage stage1 = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage1.getIcons().add(new Image(this.getClass().getResource("images/icon.png").toString()));

 		Optional<String> result = dialog.showAndWait();
 		if (result.isPresent() && result.get()!=null && !result.get().isEmpty() && !result.get().startsWith(" ")){
 			service.saveGame(timerLabel.getText(), result.get());
 			Alert alert = new Alert(AlertType.INFORMATION);
 			Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
 	        stage2.getIcons().add(new Image(this.getClass().getResource("images/icon.png").toString()));
 			alert.setTitle("Information");
 			alert.setHeaderText(null);
 			alert.setContentText("Ihr Spiel \"" + result.get() + "\" wurde erfolgreich gespeichert");
 			alert.showAndWait();
 		} else {
 			Alert alert = new Alert(AlertType.INFORMATION);
 			Stage stage3 = (Stage) alert.getDialogPane().getScene().getWindow();
 	        stage3.getIcons().add(new Image(this.getClass().getResource("images/icon.png").toString()));
 			alert.setTitle("Information");
 			alert.setHeaderText(null);
 			alert.setContentText("Spielstand wurde nicht gespeichert");
 			alert.showAndWait();
 			timer.resumeThread();
 		}
 	}

 	/**
	 * Ruft die solve Methode im Sudoku Model durch den Anzeigen Button auf
	 */
 	@FXML
	public void solveGame()
	{
 		solvedWithCheck = true;
		service.solveGame();
		showTempGameInGUI();
	}

 	/**
	 * Macht die letzte Eingabe im Sudoku Spielfeld durch den Button Undo wieder rückgängig
	 */
	@FXML
	public void undoGame()
	{
		cm.undoCommand();
		//service.undoGame();
		//showTempGameInGUI();
	}

	/**
	 * Zeigt die richtigen und falschen Eingaben im Spielfeld durch den Button Check an
	 */
	@FXML
	public void checkGame()
	{
		service.checkGame();
		showTruthInGUI();
	}

	public void checkUserGame(){
		Image image = new Image(getClass().getResource("images/icon.png").toExternalForm());
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(64);
	    imageView.setFitHeight(64);

		boolean truthUser[][] = this.getTruth();
		boolean jUser = false;
		anzRichtige = 0;


		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++){
				jUser = truthUser[i][k];
				if (jUser) {
					anzRichtige += 1;
				}
			}
		}

		System.out.println("Anzahl Richtige: " + anzRichtige);

		if (anzRichtige == 81){
			System.out.println("Test ob checkUserGame funktioniert");

			Alert alert = new Alert(AlertType.INFORMATION);
 			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
 	        stage.getIcons().add(new Image(this.getClass().getResource("images/icon.png").toString()));
 			alert.setTitle("Herzlichen Glückwunsch!");
 			alert.setHeaderText(null);
 			alert.setGraphic(imageView);
 			alert.setContentText("Sie haben das Sudoku erfolgreich gemeistert!");
 			alert.showAndWait();

		}
	}
}
