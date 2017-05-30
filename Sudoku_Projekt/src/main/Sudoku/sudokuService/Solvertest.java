package sudokuService;

public class Solvertest {

	public static void main(String[] args){
		SudokuModel a=new SudokuModel();
		a.loadGame("emp2.txt");
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+a.tempgame[x][y]);
			}
			System.out.println("");
		}

	}
}
