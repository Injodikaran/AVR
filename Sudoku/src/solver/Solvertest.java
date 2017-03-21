package solver;

public class Solvertest {
	public static void main(String[] args){
		byte[][] example= new byte[][]{
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
		//example=new byte[9][9];
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+example[x][y]);
			}
			System.out.println("");
		}
		//example[8][0]=7;
		
		System.out.println("");
		System.out.println("");
		
		IntelligentBacktracking i=new IntelligentBacktracking();
		byte[][] solve=i.solver(example);
		System.out.println("Solv1:");
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+solve[x][y]);
			}
			System.out.println("");
		}
		System.out.println("Solv2:");
		byte[][] solve2=i.solver(solve);
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+solve2[x][y]);
			}
			System.out.println("");
		}
		byte[][] example2= new byte[][]{
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
		byte[][] solve3=i.solver(example2);
		System.out.println("Solv3:");
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+solve3[x][y]);
			}
			System.out.println("");
		}
		
	}
}
