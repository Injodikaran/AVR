//http://codefordummies.blogspot.ch/2014/01/backtracking-solve-sudoku-in-java.html

package solver;


public class Eliminator {

	public static final byte FIELD_DIMENSION = 9;
	public static final byte EMPTY = 0;
	public static final byte VALUE_NOT_POSSIBLE = 0;
	public static final byte VALUE_POSSIBLE = 1;
	private static byte counter = 0;
	

	
	static byte[][] field = new byte[FIELD_DIMENSION][FIELD_DIMENSION];
	
	public static byte[][] solver(byte[][] input)
	{
		// Save a copy of the input array
		for(int yPos = 0; yPos < FIELD_DIMENSION; yPos++)
		{
			for(int xPos = 0; xPos < FIELD_DIMENSION; xPos++)
			{
				field[yPos][xPos]= input[yPos][xPos];
			} // for
		} // for
		
		if(checkIfValidField(field) == false)
		{
			System.out.println("Invalid Field. Check input");
			return null;
		}
		
		if(EliminatorAlgo(0,0)) // Solve field with init position (0,0)
		{
			System.out.println("Return valid solution");
			return field;
		}
		else
		{
			System.out.println("Return null: No solution found");
			return null;
		} // if
		
	}
	
	private static boolean EliminatorAlgo(int yPos, int xPos)
	{	
		// TBD: Abbruchbedingung
		if(yPos > FIELD_DIMENSION - 1) // Reached end of field -> Finished
		{
			if(counter > 1) // At least two numbers filled in
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		//System.out.println("Eliminator xPos: " + xPos + "\t yPos: " + yPos);
				
		// Check if field is empty
		if(field[yPos][xPos] == EMPTY) // Empty
		{
			byte numOfPossibleSolutions = 0;
			byte lastValue = 0;
			// Check all possible solutions and count it
			for(byte value = 1; value < 10; value++)
			{
				if(CheckSolution(field, xPos, yPos, value))
				{
					numOfPossibleSolutions++;
					lastValue = value;
				} // if
			} // for
			if (numOfPossibleSolutions == 1) // Just one possible solution -> Fill in, only possible value for that field
			{
				//System.out.println("Just one solution. Value: " + lastValue + "\t xPos: " + xPos + "\t yPos: " + yPos);
				field[yPos][xPos] = lastValue; // Take last value -> Just one
				//int[] pos = getNextPosition(yPos,xPos); // Go to the next position
				counter++; // increment counter
				//print(field);
				return EliminatorAlgo(0,0); // Start at zero again and check result, if false -> No solution
			}
			else // More than one value -> increment position and start again
			{
				int[] pos = getNextPosition(yPos,xPos); // Go to the next position
				return EliminatorAlgo(pos[1], pos[0]);
			}
		}
		else // Not empty -> Go forward
		{
			int[] pos = getNextPosition(yPos,xPos);
			return EliminatorAlgo(pos[1], pos[0]);
		}
	} // BacktrackingAlgo
	
	

	/*	Check, if solution correct
	 * 
	 */
	private static boolean CheckSolution(byte[][] arrayToCheck, int xPos, int yPos, int value)
	{
		// Check x-Direction
		for(int xPosCurrent = 0; xPosCurrent < FIELD_DIMENSION; xPosCurrent++)
		{
			if(value == arrayToCheck[yPos][xPosCurrent])
			{
				//System.out.println("Double number in "+xPos +" " +yPos);
				//print(arrayToCheck);
				return false;
			} // if
		} // for
		
		// Check y-Direction
		for(int yPosCurrent = 0; yPosCurrent < FIELD_DIMENSION; yPosCurrent++)
		{
			if(value == arrayToCheck[yPosCurrent][xPos])
			{
				return false;
			} // if
		} // for
		
		// Check subfield
		int xStart = 3*(xPos / 3); // Beginning of x-field
		int yStart = 3*(yPos / 3); // Beginning of y-field

		for(int yPosCurrent = yStart; yPosCurrent < (yStart + 3); yPosCurrent++)
		{
			for(int xPosCurrent = xStart; xPosCurrent < (xStart + 3); xPosCurrent++)
			{
				if(value == arrayToCheck[yPosCurrent][xPosCurrent])
				{
					return false;
				} // if
			} // for

		} // for
		// No errors
		return true;
	}
	
	public static int[] getNextPosition(int yPos, int xPos)
	{
		xPos++;
		// Next row? Set xPos to zero and increment yPos
		if(xPos > FIELD_DIMENSION - 1)
		{
			xPos = 0;
			yPos++;
		}
		
		int[] pos = {xPos, yPos};
		return pos;
	}
	
	public static boolean checkIfValidField(byte[][] input)
	{
		byte temp = 0;
		
		for(int yPos = 0; yPos < FIELD_DIMENSION; yPos++)
		{
			for(int xPos = 0; xPos < FIELD_DIMENSION; xPos++)
			{
				temp = input[yPos][xPos];
				if(temp != EMPTY)
				{
					// Check 1: More than 1 given value per subfield, x-axis, y-axis
					input[yPos][xPos] = EMPTY; // Delete current value -> For check
					if(CheckSolution(input, xPos, yPos, temp) == false)
					{
						return false; // Field not valid: Double numbers
					} // if
					input[yPos][xPos] = temp; // Restore previous value
					
					// Check 2: Range of input data
					if(temp > 9 || temp < 1) return false;
					
				} // if
			} // for
		} // for
		return true;
	}

	
	public static void print(byte[][] input)
	{
		System.out.println("---Start---");
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				System.out.print(input[y][x]);
				if(x==2 || x== 5) System.out.print(" ");
			}
			System.out.println();
			if(y==2 || y== 5) System.out.println();
		}
		System.out.println("---END---");
	}	
}
