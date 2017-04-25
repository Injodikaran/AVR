package solver;
import solver.Backtracking;

public class SolverTest {
	
	public static void main(String[] args)
	{
		// [y-Direction][x-Direction]
		// 00, 01, 02, 03, ...
		// 10
		byte[][] testSudoku = { {5,3,0 ,0,7,0, 0,0,0},
								{6,0,0 ,1,9,5, 0,0,0},
								{0,9,8 ,0,0,0, 0,6,0},
								
								{8,0,0 ,0,6,0, 0,0,3},
								{4,0,0 ,8,0,3, 0,0,1},
								{7,0,0 ,0,2,0, 0,0,6},
								
								{0,6,0 ,0,0,0, 2,8,0},
								{0,0,0 ,4,1,9, 0,0,5},
								{0,0,0 ,0,8,0, 0,7,9}
							  };
		
		byte[][] testSudoku2 = {{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								 
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
							
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0},
								{0,0,0 ,0,0,0, 0,0,0}
			  }; 
		
		//print(testSudoku);
		
		
		
		print(Backtracking.Backtracking(testSudoku));
		System.out.println("Solution");
		
		print(Backtracking.Backtracking(testSudoku2));
		System.out.println("Solution");
		
		
	}
	
	public static void print(byte[][] input)
	{
		if(input == null) 
		{
			System.out.println("No data");
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
