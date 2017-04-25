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

	
	public static void main(String[] args) throws FileNotFoundException{
		write();
	}
	
	public static void write() throws FileNotFoundException
	{
		SudokuGame sudokuGame = createSudokuGame();

		JsonObjectBuilder sudokuObjectBuilder = Json.createObjectBuilder();
		
		// Abspeichern Sudoku
		JsonArrayBuilder sudokuBuilderY = Json.createArrayBuilder();
		JsonArrayBuilder sudokuBuilderX = Json.createArrayBuilder();
		
		int[][] sudoku = sudokuGame.getSudoku();
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				sudokuBuilderX.add(sudoku[y][x]);
			}
			sudokuBuilderY.add(sudokuBuilderX);
			sudokuBuilderX = Json.createArrayBuilder();
		}
		
		sudokuObjectBuilder.add("Sudoku", sudokuBuilderY);
		
		// Abspeichern Vorlage
		JsonArrayBuilder templateBuilderY = Json.createArrayBuilder();
		JsonArrayBuilder templateBuilderX = Json.createArrayBuilder();
		
		int[][] template = sudokuGame.getSudoku();
		for(int y=0; y<9;y++)
		{
			for(int x= 0; x<9;x++)
			{
				templateBuilderX.add(template[y][x]);
			}
			templateBuilderY.add(templateBuilderX);
			templateBuilderX = Json.createArrayBuilder();
		}
		
		sudokuObjectBuilder.add("Template", templateBuilderY);
		
		// Abspeichern Zeit
		sudokuObjectBuilder.add("Time", sudokuGame.getTime());
		
		JsonObject SudokuJsonObject = sudokuObjectBuilder.build();
		
		System.out.println("Sudokus JSON String\n"+SudokuJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("emp2.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(SudokuJsonObject);
		jsonWriter.close();
	}
	

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

}
