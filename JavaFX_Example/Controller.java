package texteditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField textContent;

    @FXML
    void closeFile(ActionEvent event) {
    	System.out.println("Close File");

    }

    @FXML
    void openFile(ActionEvent event) {
    	System.out.println("Open File");
    }
    
    @FXML
    void saveFile(ActionEvent event) {
    	System.out.println("Save File");
    }

}