package com.journaldev.model;

import java.util.Arrays;

import javax.json.JsonArray;

public class SudokuGame {

	private int[][] sudoku;

	public int[][] getSudoku() {
		return this.sudoku;
	}
	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}
	
	public void print(){
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				System.out.print(sudoku[y][x]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
