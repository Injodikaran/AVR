package solver;

import java.util.ArrayList;


public class LogicSolv {
	ArrayList<Byte> temp;// = new ArrayList<Byte>();
	byte[][] mySudokuSolution;
	IntelligentBacktracking alpha= new IntelligentBacktracking();
	
	public byte[][] solver(byte[][] input){
		mySudokuSolution=input;
		
		for (byte b=0;b<81;b++){
			for (byte x=0;x<9;x++){
				for(byte y=0;y<9;y++){
					if (mySudokuSolution[x][y]==0){
						//System.out.println(""+x+"|"+y+" was "+mySudokuSolution[x][y]);
						findRelevant(x,y);
						if (temp.size()==1){
							mySudokuSolution[x][y]=temp.get(0);
							//System.out.println(""+x+"|"+y+" is know "+temp.get(0));
						}
						
					}
				}
			}
			
			/*
			for(int g=0;g<9;g++){
				for(int h=0;h<9;h++){
					System.out.print(" "+mySudokuSolution[g][h]);
				}
				System.out.println("");
			}
			System.out.println("");
			
			//*/
		}
		/*
		for(int g=0;g<9;g++){
			for(int h=0;h<9;h++){
				System.out.print(" "+mySudokuSolution[g][h]);
			}
			System.out.println("");
		}
		System.out.println("");
		
		//*/

		mySudokuSolution = alpha.solver(mySudokuSolution);
		
		//Kontrolle ob auch letzte Ziffer Gültig!
		byte kontrolle=mySudokuSolution[8][8];
		mySudokuSolution[8][8]=0; 
		findRelevant((byte)8,(byte)8);
		if (temp.size()==1 && temp.get(0)==kontrolle){
			mySudokuSolution[8][8]=kontrolle;
			//Kontrolle erfolgreich
		} else {
			return new byte[][]{
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1}};
		}

		return mySudokuSolution;
	}
	

	private void findRelevant(byte x, byte y){
		temp = new ArrayList<Byte>();
		for(byte b1=1;b1<10;b1++){ // schreibe alle Zahlen 1-9 in rel (relevante Zahlen)
			temp.add((Byte) b1);	
		}
		for(byte b2=0;b2<9;b2++){ // entferne alle Zahlen welche sich bereits in der Spalte befinden
			temp.remove((Byte) (mySudokuSolution[x][b2]));
		}
		for(byte b3=0;b3<9;b3++){// entferne alle Zahlen welche sich bereits in der Zeile befinden
			temp.remove((Byte) (mySudokuSolution[b3][y]));
		}
		for(byte b4=0;b4<3;b4++){// entferne alle Zahlen welche sich bereits im Quadrant befinden
			for (byte b5=0;b5<3;b5++){
				temp.remove((Byte) (mySudokuSolution[(x/3)*3+b4][(y/3)*3+b5]));
			}
		}
	}
}
