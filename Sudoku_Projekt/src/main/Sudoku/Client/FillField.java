package Client;

import javafx.scene.control.TextField;

public class FillField{
	private TextField selectedField;
	private String newValue, oldValue;

	public FillField(TextField selectedField, String newValue){
		this.selectedField = selectedField;
		this.newValue = newValue;
	}

	public void execute() {
		System.out.println("Wert: " + newValue);
		oldValue = selectedField.getText();
		selectedField.setText(newValue);
	}

	public void undo() {
		System.out.println("Wert: " + newValue);
		selectedField.setText(oldValue);
		}

	public TextField getSelectedField(){
		return selectedField;
	}
}
