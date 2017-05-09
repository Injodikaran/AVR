package json;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import SudokuJSONObject.SudokuGame;

public class SudokuJSONWriter {
	
    static byte[][] actualGame= new byte[][]{ // Just for debugging
			{5,3,4,6,7,8,9,1,2},
			{6,7,0,0,0,0,0,0,8},
			{1,0,0,0,0,0,0,0,7},
			{8,0,0,0,0,0,0,0,3},
			{4,0,0,0,0,0,0,0,1},
			{7,0,0,0,0,0,0,0,6},
			{9,6,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,5},
			{3,4,5,2,8,6,1,7,9},
		};
		
	static	byte[][] template= new byte[][]{ // Just for debugging
			{5,3,0,0,7,0,0,0,0},
			{6,0,0,1,9,5,0,0,0},
			{0,9,8,0,0,0,0,6,0},
			{8,0,0,0,6,0,0,0,3},
			{4,0,0,8,0,3,0,0,1},
			{7,0,0,0,2,0,0,0,6},
			{0,6,0,0,0,0,2,8,0},
			{0,0,0,4,1,9,0,0,5},
			{0,0,0,0,8,0,0,7,9},
		};

	
	public static void main(String[] args) throws FileNotFoundException{
		for (int i = 1; i < 5; i++)
		{
			String fileName = "test"+i;
			String date 	= i+".00 s";
			write(template, actualGame, date,fileName);
		}
		
	}
	
	public static void write(byte[][] tempsource, byte[][] tempgame, String time, String fileName) throws FileNotFoundException
	{
		//SudokuGame sudokuGame = createSudokuGame();

		JsonObjectBuilder sudokuObjectBuilder = Json.createObjectBuilder();
		
		// Abspeichern Sudoku
		JsonArrayBuilder sudokuBuilderY = Json.createArrayBuilder();
		JsonArrayBuilder sudokuBuilderX = Json.createArrayBuilder();
		
		//int[][] sudoku = sudokuGame.getSudoku();
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				//sudokuBuilderX.add(sudoku[y][x]);
				sudokuBuilderX.add(tempgame[y][x]);
			}
			sudokuBuilderY.add(sudokuBuilderX);
			sudokuBuilderX = Json.createArrayBuilder();
		}
		
		sudokuObjectBuilder.add("Sudoku", sudokuBuilderY);
		
		// Abspeichern Vorlage
		JsonArrayBuilder templateBuilderY = Json.createArrayBuilder();
		JsonArrayBuilder templateBuilderX = Json.createArrayBuilder();
		
		//int[][] template = sudokuGame.getSudoku();
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				// templateBuilderX.add(template[y][x]);
				templateBuilderX.add(tempsource[y][x]);
			}
			templateBuilderY.add(templateBuilderX);
			templateBuilderX = Json.createArrayBuilder();
		}
		
		sudokuObjectBuilder.add("Template", templateBuilderY);
		
		// Abspeichern Zeit
		//sudokuObjectBuilder.add("Time", sudokuGame.getTime());
		sudokuObjectBuilder.add("Time", time);
		
		JsonObject SudokuJsonObject = sudokuObjectBuilder.build();
		
		System.out.println("Sudokus JSON String\n"+SudokuJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream(fileName+".txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(SudokuJsonObject);
		jsonWriter.close();
	}
	
/*
	public static SudokuGame createSudokuGame() {
		SudokuGame sudokuGame = new SudokuGame();
		int game[][] =  new int[][]{
			{-3,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1}};
		sudokuGame.setSudoku(game);
		return sudokuGame;
	}
*/
}
