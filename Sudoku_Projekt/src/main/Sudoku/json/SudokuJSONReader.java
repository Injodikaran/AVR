package json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import SudokuJSONObject.SudokuGame;

public class SudokuJSONReader {

	//public static final String JSON_FILE="emp2.txt";
	
	public static void main(String[] args) throws IOException{
		read("asdf");
	}
	
	public static SudokuGame read(String Filename) throws IOException { //Sudokugame
		InputStream fis = new FileInputStream(Filename);
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);
		
		/**
		 * We can create JsonReader from Factory also
		JsonReaderFactory factory = Json.createReaderFactory(null);
		jsonReader = factory.createReader(fis);
		*/		
		//get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();
		
		//we can close IO resource and JsonReader now
		jsonReader.close();
		fis.close();
		
		//Retrieve data from JsonObject and create Employee bean
		SudokuGame game = new SudokuGame();
		
		//reading Sudoku from json-File
		JsonArray sudokuArrayY = jsonObject.getJsonArray("Sudoku");
		int[][] sudoku = new int[9][9];
		for(int y= 0; y<sudokuArrayY.size();y++)
		{
			JsonArray sudokuArrayX = sudokuArrayY.getJsonArray(y); 
			for(int x= 0; x<sudokuArrayX.size();x++)
			{
				sudoku[y][x] = sudokuArrayX.getInt(x);
			}
		}
		
		JsonArray temnplateArrayY = jsonObject.getJsonArray("Sudoku");
		int[][] template = new int[9][9];
		for(int y= 0; y<temnplateArrayY.size();y++)
		{
			JsonArray temnplateArrayX = temnplateArrayY.getJsonArray(y); 
			for(int x= 0; x<temnplateArrayX.size();x++)
			{
				template[y][x] = temnplateArrayX.getInt(x);
			}
		}
		
		JsonString time = jsonObject.getJsonString("Time");
		game.setSudoku(sudoku);
		game.setTemplate(template);
		game.setTime(time.getString());
		
		//game.print();
		return game;
		
	}

}
