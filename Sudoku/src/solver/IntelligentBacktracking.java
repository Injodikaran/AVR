package solver;

import java.util.*;


public class IntelligentBacktracking {
	public IntelligentBacktracking(){
	}
	public byte[][] solver(byte[][] input){
		SolvField i= new SolvField(input,(byte) 0,(byte) 0,false);
		SolvField s=fieldsolver(i);
		if (s.sud[0][0]==-1){
		}
		return s.sud;
		
	}

	private static SolvField fieldsolver(SolvField input){
		
		final  SolvField oldstate = input.clone();
		SolvField solution =oldstate;
		SolvField temp = oldstate;
		ArrayList<Byte> rel = new ArrayList<Byte>();
		
		
			byte a=oldstate.sud[oldstate.x][oldstate.y];
			if (a!=0){
				//rel.add(a);
				
				
				///*
				if (temp.x==8){
					if (temp.y==8){
						//if (rel.size()!=1){System.out.println("dürfte nie eintreten");return oldstate.clone();}else{
						temp.f=true;
						return temp.clone();
						//}
					} else {
						temp.y++;
						temp.x=0;
					} 
				}else {
					temp.x++;
				}
				solution=fieldsolver(temp);
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
				temp=oldstate.clone();
				temp.sud[temp.x][temp.y]=rel.get(b);
				if (temp.x==8){
					if (temp.y==8){
						//if (rel.size()!=1){System.out.println("dürfte nie eintreten");return oldstate.clone();}else{
						temp.f=true;
						return temp;
						//}
					} else {
						temp.y++;
						temp.x=0;
					} 
				}else {
					temp.x++;
				}
				solution=fieldsolver(temp);
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
