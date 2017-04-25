package Client;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class SudokuViewModel extends Application{
	private SudokuModel model;
	private SudokuTimerTask timer;
	@FXML
	private Label timerLabel;
	private StringProperty test = new StringProperty() {

		@Override
		public void set(String value) {
			// TODO Auto-generated method stub

		}

		@Override
		public String get() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void removeListener(InvalidationListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addListener(InvalidationListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeListener(ChangeListener<? super String> listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addListener(ChangeListener<? super String> listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getBean() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void unbind() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isBound() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void bind(ObservableValue<? extends String> observable) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Sudoku");
        model = new SudokuModel();
        timer = new SudokuTimerTask();
        timer.start();



		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SudokuView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {

        launch(args);
    }


    public void createNewGame(){
    	test.set("Test");
    	timerLabel.textProperty().bind(test);
    	//model.createNewGame();

    }

    public void reloadGame(){
    	model.reloadGame();
	}

    public void solveGame(){
    	model.solveGame();
	}

    public void checkGame(){
    	model.checkGame();
    }

    public void saveGame(){
    	model.saveGame();
    }

    public  void loadGame(){
    	model.loadGame();
    }

    public void undoGame(){
    	model.undoGame();
    }

	/*@Override
	public void initialize(URL location, ResourceBundle resources) {
		timerLabel.textProperty().bind(timer.getTimerLabel());
	}*/

    //@FXML
    /*private void initialize(){

    	//timerLabel.textProperty().bind(timer.getTimerLabel());
    }*/



}
