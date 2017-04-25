package client;

import service.*;


public class SudokuModel {
	private Service service;


	public SudokuModel(){
		service = new Service();
	}

	public void createNewGame(){
		service.createNewGame("");
    }

    public void reloadGame(){
    	service.relodeGame();
	}

    public void solveGame(){
    	service.solveGame();
	}

    public void checkGame(){
    	service.checkGame();
    }

    public void saveGame(){
    	service.saveGame("");
    }

    public  void loadGame(){
    	service.loadGame("");
    }

    public void undoGame(){
    	service.undo();
    }

}
