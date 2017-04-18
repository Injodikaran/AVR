//http://codefordummies.blogspot.ch/2014/01/backtracking-solve-sudoku-in-java.html

package solver;


public class Backtracking {

	public static final byte FIELD_DIMENSION = 9;
	public static final byte EMPTY = 0;

	
	static byte[][] field = new byte[FIELD_DIMENSION][FIELD_DIMENSION];
	
	public static byte[][] Backtracking(byte[][] input)
	{
		// Make a copy of the input array
		for(int yPos = 0; yPos < FIELD_DIMENSION; yPos++)
		{
			for(int xPos = 0; xPos < FIELD_DIMENSION; xPos++)
			{
				field[yPos][xPos] = input[yPos][xPos];
			}
		}
		if (BacktrackingAlgo(0,0))
		{
			return field;
		}
		else
		{
			System.out.println("No soulution found");
			return null;
		}
		
		
	}
	
	public static boolean BacktrackingAlgo(int yPos, int xPos)
	{	
		// Termination, reached end of field
		if(yPos > FIELD_DIMENSION - 1)
		{
			return true;
		}
		
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
		
		// Reached end of field
		/*if(yPos > FIELD_DIMENSION - 1)
		{
			return null;
		}
		*/
		
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
