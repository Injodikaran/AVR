package Client;

import javafx.scene.control.TextField;

public class FillField{
	private TextField selectedField;
	private String newValue, oldValue;

	public FillField(TextField selectedField, String newValue, String oldValue){
		this.selectedField = selectedField;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}

	public void execute() {
		selectedField.setText(newValue);
	}

	public void undo() {
		selectedField.setText(oldValue);
	}
}
