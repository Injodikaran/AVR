package Client;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;

public class CommandManager {

	private List<FillField> history = new ArrayList<>();

	public void executeCommand(FillField newFill){
		newFill.execute();
		history.add(newFill);
		System.out.println("Size" + history.size());


	}

	public void undo(){
		if(history.size() > 0){
			FillField undoFill = history.get(history.size()-1);
			undoFill.undo();
			System.out.println("Size" + history.size());
			history.remove(history.size()-1);
		}

	}

	public void deleteHistory(){
		history.removeAll(history);
	}
}
