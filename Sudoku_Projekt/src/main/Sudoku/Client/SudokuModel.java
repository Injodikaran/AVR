package Client;

import sudokuService.*;


public class SudokuModel {
	private SudokuService service;


	public SudokuModel(){
		service = new SudokuService();
	}

	public void createNewGame(){
		service.createNewGame();
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
    
    public void resetstacks(){
    	service.resetstacks();
    }
    
    public void saveGame(String time, String filename){
    	service.saveGame(time, filename);
    }

    public  void loadGame(String filename){
    	System.out.println(filename);
    	service.loadGame(filename);
    	System.out.println(service.tempgame[0][0]);
    }

    public void undoGame(){
    	service.undo();
    }

    public byte[][] getTempGame(){
		return service.getTempGame();
	}

    public boolean[][] getChangeable(){
    	return service.getChangeable();
    }

    public boolean[][] getTruth(){
		return service.getTruth();
	}

    public byte[][] getSolution(){
		return service.getSolution();
	}

    public void enterNumber(int x, int y, int newValue){
    	service.enterNumber(x, y, newValue);
    };

}
