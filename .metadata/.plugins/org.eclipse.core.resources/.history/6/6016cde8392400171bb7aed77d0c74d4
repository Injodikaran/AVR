package com.journaldev.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.journaldev.model.Address;
import com.journaldev.model.SudokuGame;

public class SudokuJSONParser {

	public static final String FILE_NAME = "Sudoku.txt";

	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream(FILE_NAME);

		JsonParser jsonParser = Json.createParser(fis);

		/**
		 * We can create JsonParser from JsonParserFactory also with below code
		 * JsonParserFactory factory = Json.createParserFactory(null);
		 * jsonParser = factory.createParser(fis);
		 */

		SudokuGame emp = new SudokuGame();
		String keyName = null;
		List<Integer> phoneNums = new ArrayList<Integer>();
		
		while (jsonParser.hasNext()) {
			Event event = jsonParser.next();
			switch (event) {
			case KEY_NAME:
				keyName = jsonParser.getString();
				break;
			case VALUE_NUMBER:
				setNumberValues(emp, keyName, jsonParser.getInt(), phoneNums);
				break;
			case VALUE_NULL:
				// don't set anything
				break;
			default:
				// we are not looking for other events
			}
		}
		int[][] nums = new int[9][9];
		int index = 0;
//		for(int l :phoneNums){
//			nums[index++] = l;
//		}
		emp.setSudoku(nums);
		
		System.out.println(emp);
		
		//close resources
		fis.close();
		jsonParser.close();
	}

	private static void setNumberValues(SudokuGame emp, String keyName, int value, List<Integer> sudokus) {
		switch(keyName){
		case "Sudoku":
			sudokus.add(value);
			break;
		default:
			System.out.println("Unknown element with key="+keyName);	
		}
	}
}
