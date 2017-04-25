//http://codefordummies.blogspot.ch/2014/01/backtracking-solve-sudoku-in-java.html
/*According to wikipedia https://de.wikipedia.org/wiki/Backtracking
 * Funktion FindeLoesung (Stufe, Vektor)
  1. wiederhole, solange es noch neue Teil-Lösungsschritte gibt:
     a) wähle einen neuen Teil-Lösungsschritt;
     b) falls Wahl gültig ist:
               I) erweitere Vektor um Wahl;
              II) falls Vektor vollständig ist, return true; // Lösung gefunden!
                  sonst:
                       falls (FindeLoesung(Stufe+1, Vektor)) return true; // Lösung!
                       sonst mache Wahl rückgängig; // Sackgasse (Backtracking)!
  2. Da es keinen neuen Teil-Lösungsschritt gibt: return false // Keine Lösung!
 */

package solver;


public class Backtracking {

	public static final byte FIELD_DIMENSION = 9;
	public static final byte EMPTY = 0;

	
	static byte[][] field = new byte[FIELD_DIMENSION][FIELD_DIMENSION];
	
	public static byte[][] solver(byte[][] input)
	{
		// Copy input 2d-array
		for (int yPos = 0; yPos < FIELD_DIMENSION; yPos++)
		{
			for (int xPos = 0; xPos < FIELD_DIMENSION; xPos++)
			{
				field[yPos][xPos] = input[yPos][xPos];
			} // for
		} // for
		

		if (BacktrackingAlgo(0,0))
		{
			return field;
		}
		else
		{
			return null;
		} // if
	}
	
	public static boolean BacktrackingAlgo(int yPos, int xPos)
	{	
		// Termination, reached end of field
		if(yPos > FIELD_DIMENSION - 1)
		{
			return true;
		} // if
		
		// Check, if field is empty
		if(field[yPos][xPos] == EMPTY) // Fill in new value and check
		{
			for(byte value = 1; value < 10; value++)
			{
				if(CheckSolution(field, xPos, yPos, value))
				{
					field[yPos][xPos] = value;
					int[] pos = getNextPosition(yPos,xPos);
					if(BacktrackingAlgo(pos[1], pos[0])) // Algorithm with new positions
					{
						return true; // Value okay
					}
					else
					{
						field[yPos][xPos] = EMPTY; // Delete old position (step back)
					} // if
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
		int[] pos = {xPos, yPos};
		return pos;
	}
}