package administrationLogic;

import solver.LogicSolv;

public class Main {
	byte[][] tempsource;
	byte[][] tempgame;
	byte[][] tempsolution;
	boolean[][] trueth=new boolean[9][9];
	LogicSolv sudokusolver=new LogicSolv();
	public Main(){}
	
	public static void main(String[] args){
		//Starte alles auf...
	}
	
	
	/** Neues Spiel
	 * Öffnet aus der Sudokuquelle ein neues Spiel
	 * 
	 * @return
	 * Spiel
	 */
	public byte[][] createNewGame(){
		//code... tempsource=XY;
		tempsolution=sudokusolver.solver(tempsource);
		return tempsource;
	}
	
	
	/** Neu Starten
	 * giebt das Sudoku aus dem zwischenspeicher neu aus, und setzt den Timer zurück
	 * @return
	 * Spiel
	 */
	public byte[][] relodeGame(){
		return tempsource;
	}
	
	
	/** Anzeigen
	 * zeigt die Lösung an
	 * @return
	 * lösung
	 */
	public byte[][] solveGame(){
		return tempsolution;
	}
	
	
	/** Check
	 * vergleicht aktuelles Sudoku mit der Lösung
	 * @return
	 * Richtigkeit
	 */
	public boolean[][] checkGame(byte[][] game){
		tempgame=game;
		for(byte b1=0;b1<9;b1++){
			for(byte b2=0;b2<9;b2++){
				if (tempgame[b1][b2]!=0 && tempgame[b1][b2]!=tempsolution[b1][b2]){
					trueth[b1][b2]=false;
				}else{
					trueth[b1][b2]=true;
				}
			}
		}
		return trueth;
	}
	
	
	/** Spiel Speichern
	 * legt das Spiel ab
	 * 
	 */
	public void saveGame(byte[][] game){
		tempgame=game;
		//speicher code (lege tempgame und tempsource ab)
	}
	
	
	/** Spiel Laden
	 * ladet ein altes Spiel anhand Filenamen
	 * 
	 */
	public  byte[][] loadGame(){
		//lade temsource als neues spiel, und tempgame als aktuellen spielstand
		//relodeGame();
		
		//......
		return tempsource;
		// "Gui" muss anschliessend selbständig tempgame laden
		
	}
}

