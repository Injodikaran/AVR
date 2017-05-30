package sudokuService;

import java.io.FileNotFoundException;
import java.io.IOException;

import json.SudokuJSONReader;
import json.SudokuJSONWriter;
import java.util.ArrayDeque;
import java.util.Deque;

import SudokuJSONObject.SudokuGame;

public class SudokuModel{
	private byte[][] tempsource=new byte[9][9];				//Sudoku in Ursprünglicher Form
	public byte[][] tempgame=new byte[9][9];				//Aktueller Spielstand
	private byte[][] tempsolution=new byte[9][9];			//Lösung
	boolean[][] changeable=new boolean[9][9];				//Feld Attribut: änderbar
	boolean[][] truth=new boolean[9][9];					//Feld Attribut: Richtig (wird nur Angezeigt wenn erwünscht)
	private Deque<doFile> undos = new ArrayDeque<doFile>();	//undo Stack
	private Deque<doFile> redos = new ArrayDeque<doFile>();	//redo Stack
	private Solver sudokusolver=new Solver();				//SolverKlasse
	private doFile u;

	/** Neues Spiel
	 * Öffnet aus der Sudokuquelle ein neues Spiel
	 *
	 * @return
	 * Spiel
	 */
	public void createNewGame(){
		SudokuGame imp;
		int[][] temp;
		String fileName = "Sudoku"+((int)(Math.random()*10+1))+".txt";
		try {
			imp = SudokuJSONReader.read(fileName);
			temp = imp.getTemplate();
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					tempsource[b1][b2]=(byte)temp[b1][b2];
				}
			}
			tempgame=new byte[9][9];
			for (byte x=0;x<9;x++){
				for(byte y=0;y<9;y++){
					tempgame[x][y]=tempsource[x][y];
				}
			}
			tempsolution=sudokusolver.solver(tempsource);
			for(byte b1=0;b1<9;b1++){
				for(byte b2=0;b2<9;b2++){
					if (tempsource[b1][b2]>0){
						changeable[b1][b2]=false;
					}else{
						changeable[b1][b2]=true;
					}
				}
			}
			redos.clear();
			undos.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Neu Starten
	 * giebt das Sudoku aus dem zwischenspeicher neu aus, und setzt den Timer zurück
	 * @return
	 * Spiel
	 */
	public void reloadGame(){
		tempgame=new byte[9][9];
		for (byte x=0;x<9;x++){
			for(byte y=0;y<9;y++){
				tempgame[x][y]=tempsource[x][y];
			}
		}
		tempsolution=sudokusolver.solver(tempsource);
		for(byte b1=0;b1<9;b1++){
			for(byte b2=0;b2<9;b2++){
				if (tempsource[b1][b2]>0){
					changeable[b1][b2]=false;
				}else{
					changeable[b1][b2]=true;
				}
			}
		}
		truth=new boolean[9][9];
		redos.clear();
		undos.clear();
	}

	/** Anzeigen
	 * zeigt die Lösung an
	 * @return
	 * lösung
	 */
	public void solveGame(){
		tempgame=new byte[9][9];
		for (byte x=0;x<9;x++){
			for(byte y=0;y<9;y++){
				tempgame[x][y]=tempsolution[x][y];
			}
		}
	}

	/** Check
	 * vergleicht aktuelles Sudoku mit der Lösung
	 * @return
	 * Richtigkeit
	 */
	public void checkGame(){
		for(byte b1=0;b1<9;b1++){
			for(byte b2=0;b2<9;b2++){
				if (tempgame[b1][b2]>0 && tempgame[b1][b2]!=tempsolution[b1][b2]){
					truth[b1][b2]=false;
				}else if (tempgame[b1][b2]>0){
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
			e.printStackTrace();
		}
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
			redos.clear();
			undos.clear();
			return imp.getTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Ändere Number
	 * ändere eine Zahl/gebe eine ein
	 *
	 */
	public void enterNumber(int x,int y, int number){
		u = new doFile();
		u.x=(byte) x;
		u.y=(byte) y;
		u.number=(byte) number;
		undos.push(u);
		tempgame[x][y] = (byte) number;
		truth[x][y] = true;
	}

	/** Mache Änderung rückgängig
	 */
	public void undoGame(){
		if (undos.isEmpty()){
			//do nothing
		} else {
			u = undos.pop();
			byte x=u.x;
			byte y=u.y;
			byte n=u.number;
			tempgame[x][y] = n;
		}
	}

	/**
	 * Holt das aktuelle Spiel
	 */
	public byte[][] getTempGame(){
		return tempgame;
	}

	/**
	 * Holt die veränderbaren Felder
	 */
	public boolean[][] getChangeable(){
		return changeable;
	}

	/**
	 * Holt richtig besetzten Felder
	 */
	public boolean[][] getTruth(){
		return truth;
	}

	/**
	 * Holt das gelöste Spiel
	 */
	public byte[][] getSolution(){
		return tempsolution;
	}

	/**
	 * Setzt Stack zurück
	 */
	public void resetstacks(){
		undos.clear();
	}
}

