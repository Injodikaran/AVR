package Client;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SingletonDataStore{
	private Stage primaryStage;
	private String selectedNumber;
	private TextField selectedTextField;
	private String test;
	private static SingletonDataStore mainapp = null;
	@FXML
	private Button numberOne;
	@FXML
	private Button createNewGame;
	@FXML
	private URL location;
	@FXML
	private List<Button> numbers;

	private SingletonDataStore(){}

	public static SingletonDataStore getInstance(){
		if(mainapp == null) {
	         mainapp = new SingletonDataStore();
	      }
	      return mainapp;
	}

	public void setStage(Stage stage){
		this.primaryStage = stage;
	}

	public Stage getStage(){
		return primaryStage;
	}

	public void setSelectedNumber(String selectedNumber){
		this.selectedNumber = selectedNumber;
	}

	public void setSelectedTextField(TextField selectedTextField){
		this.selectedTextField = (TextField) selectedTextField;
	}
	public TextField getSelectedTextField(){
		return selectedTextField;
	}
}
