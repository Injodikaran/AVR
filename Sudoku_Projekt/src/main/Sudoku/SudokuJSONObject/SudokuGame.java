package SudokuJSONObject;

import java.util.Arrays;

import javax.json.JsonArray;

public class SudokuGame {

	// Sudoku Spiel
	private int[][] sudoku;

	public int[][] getSudoku() {
		return this.sudoku;
	}
	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}
	
	// Sudoku Spiel Vorlage
	private int[][] template;

	public int[][] getTemplate() {
		return this.template;
	}
	public void setTemplate(int[][] temp) {
		this.template = temp;
	}
	
	// Zeitangabe
	private String Time = "00:00";
	
	public void setTime(String time)
	{
		this.Time = time;
	}
	public String getTime()
	{
		return this.Time;
	}
	
	// Testausgabe JSON File in Java
	public void print(){
		System.out.println("Sudoku:");
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				System.out.print(sudoku[y][x]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
		System.out.println("Vorlage:");
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				System.out.print(template[y][x]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
		System.out.println("Time:");
		System.out.println(this.Time);
	}
}
