/*//http://codefordummies.blogspot.ch/2014/01/backtracking-solve-sudoku-in-java.html

package solver;


public class Eliminator {

	public static final byte FIELD_DIMENSION = 9;
	public static final byte EMPTY = 0;
	public static final byte VALUE_NOT_POSSIBLE = 0;
	public static final byte VALUE_POSSIBLE = 1;
	

	
	static byte[][][] field = new byte[FIELD_DIMENSION][FIELD_DIMENSION][FIELD_DIMENSION];
	static byte[][] solution_field = new byte[FIELD_DIMENSION][FIELD_DIMENSION];
	
	public static byte[][] Eliminator(byte[][] input)
	{
		// Save a copy of the input array
		// to a 3d array -> [yPos][xPos][value 1 ... 9]
		for(int yPos = 0; yPos < FIELD_DIMENSION; yPos++)
		{
			for(int xPos = 0; xPos < FIELD_DIMENSION; xPos++)
			{
				// Is field empty? Fill with 1 ... 9 (all values possible)
				if(input[yPos][xPos] == EMPTY){
					for(int value = 1; value < 10; value++)
					{
						field[yPos][xPos][value] = VALUE_POSSIBLE;
					}
				}
				else // Field is not empty -> save value (just one value possible)
				{
					int inputValue = input[yPos][xPos];
					if(inputValue > 10 || inputValue < 1)
					{
						System.out.println("Invalid Playground");
					}
					field[yPos][xPos][inputValue] = VALUE_POSSIBLE;
					
					for(int value = 1; value < 10; value++)
					{
						// Value == input: 
						if(inputValue != value)
						{
							field[yPos][xPos][value] = VALUE_NOT_POSSIBLE;
						} // if		
					} // for	
				} // if
			} // for
		}
		Elimnator(0,0,0);
		return field;
		
	}
	
	public static boolean Elimnator(int yPos, int xPos, int counter)
	{	
		// Termination, reached end of field
		if(counter > FIELD_DIMENSION*FIELD_DIMENSION)
		{
			return true;
		}
		
		int value = 0;
		solution_field[yPos][xPos] = field[yPos][xPos][value];
		CheckSolution(solution_field[][], xPos, yPos, value);
		
		
		// Check, if field is empty
		if(field[yPos][xPos] == EMPTY) // Fill in new value and check
		{
			for(byte value = 1; value < 10; value++)
			{
				if(CheckSolution(field, xPos, yPos, value))
				{
					field[yPos][xPos] = value;
					int[] pos = getNextPosition(yPos,xPos);
					if(BacktrackingAlgo(pos[1], pos[0]))
					{
						return true;
					}
					else
					{
						field[yPos][xPos] = EMPTY;
					}

				}
				else // Solution not valid
				{
					// Delete last entry by overwriting with EMPTY
					field[yPos][xPos] = EMPTY;
				} // if
				
			} // for
			
			// No valid value found.
			return false;
		}
		else // Not empty -> Go forward
		{
			int[] pos = getNextPosition(yPos,xPos);
			return BacktrackingAlgo(pos[1], pos[0]);
			
		}
	
	} // BacktrackingAlgo

	
		Check, if solution correct
	 * 
	 
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
			}
		}
		
		// Check y-Direction
		for(int yPosCurrent = 0; yPosCurrent < FIELD_DIMENSION; yPosCurrent++)
		{
			if(value == arrayToCheck[yPosCurrent][xPos])
			{
				return false;
			}
		}
		
	
		// Check subfield
		int xStart = 3*(xPos / 3); // Beginning of x-field
		int yStart = 3*(yPos / 3); // Beginning of y-field

		for(int yPosCurrent = yStart; yPosCurrent < yStart+2; yPosCurrent++)
		{
			for(int xPosCurrent = xStart; xPosCurrent < xStart+2; xPosCurrent++)
			{
				if(value == arrayToCheck[yPosCurrent][xPosCurrent]) return false;
			}

		}
				
		
		// Check Fields
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
		
		// Reached end of field
		if(yPos > FIELD_DIMENSION - 1)
		{
			return null;
		}
		
		
		int[] pos = {xPos, yPos};
		return pos;
		
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
*/