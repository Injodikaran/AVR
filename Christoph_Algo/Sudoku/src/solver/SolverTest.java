package solver;
import solver.Backtracking;

public class SolverTest {
	
	public static final int NUM_OF_TESTS = 6;
	
	public static void main(String[] args)
	{
		
		
		
		byte[][][] test = new byte[NUM_OF_TESTS][9][9];
		
		// [y-Direction][x-Direction]
		// 00, 01, 02, 03, ...
		// 10

		byte[][] testSudoku1 = { {5,3,0 ,0,7,0, 0,0,0},
								{6,0,0 ,1,9,5, 0,0,0},
								{0,9,8 ,0,0,0, 0,6,0},
								
								{8,0,0 ,0,6,0, 0,0,3},
								{4,0,0 ,8,0,3, 0,0,1},
								{7,0,0 ,0,2,0, 0,0,6},
								
								{0,6,0 ,0,0,0, 2,8,0},
								{0,0,0 ,4,1,9, 0,0,5},
								{0,0,0 ,0,8,0, 0,7,9}
							  };
		
		byte[][] testSudoku2 = {{5,3,0 ,0,7,0, 0,0,0},
								{6,0,0 ,1,9,5, 0,0,0},
								{0,9,8 ,0,0,0, 0,6,0},
								
								{8,0,0 ,0,6,0, 0,0,3},
								{4,0,0 ,8,0,3, 0,0,1},
								{7,0,0 ,0,2,0, 0,0,6},
								
								{0,6,0 ,0,0,0, 2,8,0},
								{0,0,0 ,4,1,9, 0,0,5},
								{0,0,0 ,0,8,0, 0,7,8}
			  					};
		
		byte[][] testSudoku3 = {{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								 
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
							
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0}
			  					}; 
		
		byte[][] testSudoku4 = {{0,0,1 ,5,0,9, 0,7,2},
								{4,0,9 ,0,0,6, 0,0,0},
								{0,0,0 ,0,0,0, 3,0,0},
								 
								{0,4,0 ,8,0,0, 2,0,0},
								{0,2,0 ,0,0,0, 0,5,0},
								{0,0,8 ,3,0,2, 0,4,0},
							
								{0,0,2 ,9,0,0, 0,0,0},
								{0,0,0 ,4,0,0, 6,0,3},
								{3,5,0 ,6,0,0, 8,0,0}
									}; 
		
		
		// Test Sam
		byte[][] testSudoku5 = {
					            {5,3,4,6,7,8,9,1,2},
					
					            {6,7,0,0,0,0,0,0,8},
					
					            {1,0,0,0,0,0,0,0,7},
					
					            {8,0,0,0,0,0,0,0,3},
					
					            {4,0,0,0,0,0,0,0,1},
					
					            {7,0,0,0,0,0,0,0,6},
					
					            {9,6,0,0,0,0,0,0,0},
					
					            {2,0,0,0,0,0,0,0,5},
					
					            {3,4,5,2,8,6,1,7,8},
								};
		
		byte[][] model = new byte[9][9]; // Init with zeros
							  model[0][3] = 2 ;
						      model[0][5] = 1 ;
					
						      model[2][3] = 9 ;
						      model[2][4] = 5 ;
						      model[2][5] = 7 ;
					
						      model[3][0] = 3 ;
						      model[3][2] = 9 ;
						      model[3][3] = 7 ;
						      model[3][5] = 5 ;
						      model[3][6] = 1 ;
						      model[3][8] = 6 ;
					
						      model[4][2] = 2 ;
						      model[4][6] = 5 ;
					
						      model[5][0] = 4 ;
						      model[5][2] = 5 ;
						      model[5][3] = 1 ;
						      model[5][5] = 8 ;
						      model[5][6] = 3 ;
						      model[5][8] = 9 ;
					
						      model[6][3] = 5 ;
						      model[6][4] = 7 ;
						      model[6][5] = 3 ;
					
						      model[8][3] = 8 ;
						      model[8][5] = 9 ;
						 
			// Make an array to test easier
			  test[0] = testSudoku1;
			  test[1] = testSudoku2;
			  test[2] = testSudoku3;
			  test[3] = testSudoku4;
			  test[4] = testSudoku5;
			  test[5] = model;
						      
			  
			  for(int i = 0; i < NUM_OF_TESTS; i++)
			  {

			      System.out.println("Backtracking: ");
				  long startTime = System.currentTimeMillis();
			      print(Backtracking.solver(test[i]));
			      long stopTime = System.currentTimeMillis();
			      long runTime = stopTime-startTime;
			      System.out.println("Runtime " + runTime + " ms");
			      System.out.println("***************************");
			  }	
	}
	
	public static void print(byte[][] input)
	{
		if(input == null) 
		{
			System.out.println("No solution found");
			return;
		}
		
		System.out.println("---START---");
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
