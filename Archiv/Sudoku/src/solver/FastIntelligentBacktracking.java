package solver;

import java.util.ArrayList;

public class FastIntelligentBacktracking{
	byte[][] mySudokuSolution=new byte [9][9];
	boolean solutionfound;
	
	public FastIntelligentBacktracking(){
		
	}

	
	public byte[][] solver(byte[][] input){
		solutionfound=false;
		for (byte b=0;b<9;b++){
			for (byte c=0;c<9;c++){
				mySudokuSolution[b][c]=input[b][c];
			}
		}
		sysout();
		psolve(new Byte2());
		if (solutionfound){
			return mySudokuSolution;
		} else {
			return mySudokuSolution;//null;
		}
	}
	private boolean psolve(Byte2 B){
		ArrayList<Byte> temp = new ArrayList<Byte>();
		Byte2 tB=new Byte2();
		if (B.x==8){
			if (B.y==8){
			} else {
				tB.y++;
				tB.x=0;
			} 
		}else {
			tB.x++;
		}
		if (mySudokuSolution[B.x][B.y]!=0){
			if(B.x==8 && B.y==8){
				solutionfound=true;
			} else{
				psolve(tB);
			}
			return true;//false?
		}else{
			temp=findRelevant(B);
		}
		if (temp.isEmpty()){
		return false;
		}
		for(byte b=0;b<temp.size();b++){ //prüfe bis unmöglich
			if (solutionfound){
				return true;
			}
			mySudokuSolution[B.x][B.y]=temp.get(b);
			sysout();
			if (B.x==8 && B.y==8){
				solutionfound=true;
				return true;
			}
			psolve(tB);
			mySudokuSolution[B.x][B.y]=0;
		}
		return false;
		
	}
	
	private ArrayList<Byte> findRelevant(Byte2 B){
		ArrayList<Byte> t=new ArrayList<Byte>();
		if (mySudokuSolution[B.x][B.y]!=0){t.add(mySudokuSolution[B.x][B.y]);
		}else{
			for(byte b=1;b<10;b++){ // schreibe alle Zahlen 1-9 in rel (relevante Zahlen)
				t.add((Byte) b);	
			}
			for(byte b=0;b<9;b++){ // entferne alle Zahlen welche sich bereits in der Spalte befinden
				t.remove((Byte) (mySudokuSolution[B.x][b]));
			}
			for(byte b=0;b<9;b++){// entferne alle Zahlen welche sich bereits in der Zeile befinden
				t.remove((Byte) (mySudokuSolution[b][B.y]));
			}
			for(byte b=0;b<3;b++){// entferne alle Zahlen welche sich bereits im Quadrant befinden
				for (byte c=0;c<3;c++){
					t.remove((Byte) (mySudokuSolution[(B.x/3)*3+b][(B.y/3)*3+c]));
				}
			}
		}
		return t;
	}
	private void sysout(){
		System.out.println("");
		System.out.println("");
		for(int x=0;x<9;x++){
			for(int y=0;y<9;y++){
				System.out.print(" "+mySudokuSolution[x][y]);
			}
			System.out.println("");
		}
	}
}
