package solver;

import java.util.*;


public class IntelligentBacktracking {
	int counter=0;
	public IntelligentBacktracking(){
		
	}
	public byte[][] solver(byte[][] input){
		SolvField i= new SolvField(input,(byte) 0,(byte) 0,false);
		SolvField s=fieldsolver(i);
		System.out.println(counter);
		System.out.println("");
		return s.sud;
		
	}

	private SolvField fieldsolver(SolvField input){
		counter=counter+1;
		
		final SolvField oldstate = input;
		SolvField solution =oldstate;
		SolvField temp = oldstate;
		final ArrayList<Byte> rel;
		if(oldstate.sud[oldstate.x][oldstate.y]!=0){ //wenn bereits beschrieben
			if (oldstate.x<8){//wenn nicht in letzter Spalte: erhöhe Spalte um 1 und fieldsolve
				temp.x++;
				return fieldsolver(temp);
			} else if(oldstate.y<8){//wenn nicht in letzter Zeile erhöhe Zeile um 1 und setze Spalte auf 0 und fieldsolve
				temp.y++;
				temp.x=(byte) 0;
				return fieldsolver(temp);
			} else { //sudoku ist abgeschlossen
				temp.f=true;
				return temp;
			}
		} else { // wenn noch leer (0)
			rel=findRelevant(oldstate); // finde mögliche lösungen
			if (rel.isEmpty()) // wenn keine lösung vorhanden Springe aufwärts /
				return oldstate;
			for(int i=0;i<rel.size();i++){System.out.print(rel.get(i)+", ");}System.out.println("gehört zu "+oldstate.x+"|"+oldstate.y);
			for(byte b=0;b>rel.size();b++){ //prüfe bis unmöglich
				temp=oldstate;
				temp.sud[temp.x][temp.y]=rel.get(b);
				if (temp.x==8){
					if (temp.y==8){
						if (rel.size()!=1){System.out.println("dürfte nie eintreten");}
						temp.f=true;
						return temp;
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
		}
		System.out.println("Never should end here!");
		return new SolvField(null,(byte) 0,(byte) 0,false);
	}
	private ArrayList<Byte> findRelevant(SolvField input){
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
