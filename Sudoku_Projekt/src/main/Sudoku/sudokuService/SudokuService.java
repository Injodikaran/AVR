package sudokuService;

import java.io.FileNotFoundException;
import java.io.IOException;

import json.SudokuJSONReader;
import json.SudokuJSONWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import SudokuJSONObject.SudokuGame;

public class SudokuService{
	private byte[][] tempsource=new byte[9][9];							//Sudoku in Ursprünglicher Form
	public byte[][] tempgame=new byte[9][9];										//Aktueller Spielstand
	private byte[][] tempsolution=new byte[9][9];							//Lösung
	boolean[][] changeable=new boolean[9][9];				//Feld Attribut: änderbar
	boolean[][] truth=new boolean[9][9];					//Feld Attribut: Richtig (wird nur Angezeigt wenn erwünscht)
	private Deque<Byte> undos = new ArrayDeque<Byte>();		//undo Stack
	private Deque<Byte> redos = new ArrayDeque<Byte>();		//redo Stack
	private Solver sudokusolver=new Solver();				//SolverKlasse
	
	
	//public Service(){}
	//Timer?
	
	/** Neues Spiel
	 * Öffnet aus der Sudokuquelle ein neues Spiel
	 * 
	 * @return
	 * Spiel
	 */
	public void createNewGame(String fileName){
		SudokuGame imp;
		int[][] temp;
		try {
			imp = SudokuJSONReader.read(fileName);
			temp = imp.getTemplate();
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					tempsource[b1][b2]=(byte)temp[b1][b2];
				}
			}
			tempgame=tempsource;
			tempsolution=sudokusolver.solver(tempsource);
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					if (tempsource[b1][b2]!=0){
						changeable[b1][b2]=false;
					}else{
						changeable[b1][b2]=true;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Neu Starten
	 * giebt das Sudoku aus dem zwischenspeicher neu aus, und setzt den Timer zurück
	 * @return
	 * Spiel
	 */
	public void relodeGame(){
		tempgame = tempsource;
	}
	
	
	/** Anzeigen
	 * zeigt die Lösung an
	 * @return
	 * lösung
	 */
	public void solveGame(){
		tempgame = tempsolution;
	}
	
	
	/** Check
	 * vergleicht aktuelles Sudoku mit der Lösung
	 * @return
	 * Richtigkeit
	 */
	public void checkGame(){
		for(byte b1=0;b1<9;b1++){
			for(byte b2=0;b2<9;b2++){
				if (tempgame[b1][b2]!=0 && tempgame[b1][b2]!=tempsolution[b1][b2]){
					truth[b1][b2]=false;
				}else{
					truth[b1][b2]=true;
				}
			}
		}
	}
	
	
	/** Spiel Speichern
	 * legt das Spiel ab
	 * @throws FileNotFoundException 
	 * 
	 */
	public void saveGame(String time, String fileName) {
		try {
			SudokuJSONWriter.write(tempsource, tempgame, time, fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//speicher code (lege tempgame und tempsource ab)
	}
	
	
	/** Spiel Laden
	 * ladet ein altes Spiel anhand Filenamen
	 * 
	 */
	public  String loadGame(String fileName){
		SudokuGame imp;
		int[][] temp;
		try {
			imp = SudokuJSONReader.read(fileName);
			temp = imp.getTemplate();
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					tempsource[b1][b2]=(byte)temp[b1][b2];
				}
			}
			temp = imp.getSudoku();
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					tempgame[b1][b2]=(byte)temp[b1][b2];
				}
			}
			tempsolution=sudokusolver.solver(tempsource);
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					if (tempsource[b1][b2]!=0){
						changeable[b1][b2]=false;
					}else{
						changeable[b1][b2]=true;
					}
				}
			}
			return imp.getTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
	
	/** Ändere Number
	 * ändere eine Zahl/gebe eine ein
	 * 
	 */
	public void enterNumber(int x,int y, int number){
		undos.add((byte) x);
		undos.add((byte) y);
		undos.add(tempgame[x][y]);
		redos.clear();
		tempgame[x][y] = (byte) number;
		truth[x][y] = true;
		
	}
	
	/** Mache Änderung rückgängig
	 */
	public void undo(){
		if (undos.isEmpty()){
			//do nothing
		} else {
			byte number = undos.pop();
			byte y = undos.pop();
			byte x = undos.pop();
			redos.add((byte) x);
			redos.add((byte) y);
			redos.add(tempgame[x][y]);
			tempgame[x][y] = number;
		}
	}
		
		/** Mache Änderung wieder
		 */
		public void redo(){
			if (redos.isEmpty()){
				//do nothing
			} else {
				byte number = redos.pop();
				byte y = redos.pop();
				byte x = redos.pop();
				undos.add((byte) x);
				undos.add((byte) y);
				undos.add(tempgame[x][y]);
				tempgame[x][y] = number;
			}
	}
	
	
}

