package sudokuService;

import java.util.ArrayList;

public class Solver {
	private ArrayList<Byte> temp;// = new ArrayList<Byte>();
	private byte[][] mySudokuSolution;
	
	public byte[][] solver(byte[][] input){
		//*
		mySudokuSolution=new byte[9][9];
		for (byte x=0;x<9;x++){
			for(byte y=0;y<9;y++){
				mySudokuSolution[x][y]=input[x][y];
			}
		}//*/
		//mySudokuSolution=input;
		
		
		for (byte b=0;b<40;b++){
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
			
			SolvField i= new SolvField(mySudokuSolution,(byte) 0,(byte) 0,false);
			SolvField s=fieldsolver(i);
			mySudokuSolution=s.sud;
		}
		//Gültikeitskontrolle (Da Rekursiver Allgoritmus evt. doppelte Zahlen nicht erkennt)
		//*/
		byte kontrolle;
		for(byte b1=0;b1<9;b1++){
			for(byte b2=0;b2<9;b2++){
					kontrolle=mySudokuSolution[b1][b2];
					mySudokuSolution[b1][b2]=0; 
					findRelevant(b1,b2);
					if (temp.size()==1 && temp.get(0)==kontrolle){
						mySudokuSolution[b1][b2]=kontrolle;
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
			}
		}//*/
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
		private static SolvField fieldsolver(SolvField input){
			
			final  SolvField oldstate = input.clone();
			SolvField solution =oldstate;
			SolvField tempstate = oldstate;
			ArrayList<Byte> rel = new ArrayList<Byte>();
			
			
				byte a=oldstate.sud[oldstate.x][oldstate.y];
				if (a!=0){
					//rel.add(a);
					
					
					///*
					if (tempstate.x==8){
						if (tempstate.y==8){
							//if (rel.size()!=1){System.out.println("dürfte nie eintreten");return oldstate.clone();}else{
							tempstate.f=true;
							return tempstate.clone();
							//}
						} else {
							tempstate.y++;
							tempstate.x=0;
						} 
					}else {
						tempstate.x++;
					}
					solution=fieldsolver(tempstate);
					if (oldstate.x==0 && oldstate.y==0){
						return new SolvField(new byte[][]{
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1},
							{-1,-1,-1,-1,-1,-1,-1,-1,-1}},(byte) 0,(byte) 0,false);
					} else {
						return solution;
					}
					//*/
				}else{
					rel=findRelevant(oldstate); // finde mögliche lösungen
				} if (rel.isEmpty()) // wenn keine lösung vorhanden Springe aufwärts /
					return oldstate.clone();
				//for(int i=0;i<rel.size();i++){System.out.print(rel.get(i)+", ");}System.out.println("gehört zu "+oldstate.x+"|"+oldstate.y);
				for(byte b=0;b<rel.size();b++){ //prüfe bis unmöglich
					tempstate=oldstate.clone();
					tempstate.sud[tempstate.x][tempstate.y]=rel.get(b);
					if (tempstate.x==8){
						if (tempstate.y==8){
							//if (rel.size()!=1){System.out.println("dürfte nie eintreten");return oldstate.clone();}else{
							tempstate.f=true;
							return tempstate;
							//}
						} else {
							tempstate.y++;
							tempstate.x=0;
						} 
					}else {
						tempstate.x++;
					}
					solution=fieldsolver(tempstate);
					if (solution.f){
						return solution;
					}
				}
			
			//System.out.println("Never should end here!");
			return new SolvField(new byte[][]{
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1},
				{-1,-1,-1,-1,-1,-1,-1,-1,-1}},(byte) 0,(byte) 0,false);
		}
		private static ArrayList<Byte> findRelevant(SolvField input){
			ArrayList<Byte> rel = new ArrayList<Byte>();
			for(byte b=1;b<10;b++){ // schreibe alle Zahlen 1-9 in rel (relevante Zahlen)
				rel.add((Byte) b);	
			}
			for(byte b=0;b<9;b++){ // entferne alle Zahlen welche sich bereits in der Spalte befinden
				rel.remove((Byte) (input.sud[input.x][b]));
			}
			for(byte b=0;b<9;b++){// entferne alle Zahlen welche sich bereits in der Zeile befinden
				rel.remove((Byte) (input.sud[b][input.y]));
			}
			for(byte b=0;b<3;b++){// entferne alle Zahlen welche sich bereits im Quadrant befinden
				for (byte c=0;c<3;c++){
					rel.remove((Byte) (input.sud[(input.x/3)*3+b][(input.y/3)*3+c]));
				}
			}
			return rel;
		}
}

