package Client;

import javafx.scene.control.TextField;

public class FillField{
	private TextField selectedField;
	private String value;

	public FillField(TextField selectedField, String value){
		this.selectedField = selectedField;
		this.value = value;
	}

	public void execute() {
		selectedField.setText(value);

	}



}
