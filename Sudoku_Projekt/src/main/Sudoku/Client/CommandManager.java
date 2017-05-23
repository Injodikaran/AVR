package Client;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;

public class CommandManager {

	private List<FillField> history = new ArrayList<>();

	public void executeCommand(FillField newFill){
		history.add(newFill);
		newFill.execute();

	}

	public void undo(){
		if(history.size() > 0){
			FillField undoFill = history.remove(history.size() - 1);
			undoFill.execute();
		}

	}

	public void deleteHistory(){
		history.removeAll(history);
	}
}
