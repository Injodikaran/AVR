package solver;

public class SolvField {
	byte[][] sud;//aktueller stand des Sudokus
	byte x;//zeile im Algorytmus
	byte y;//spalte im Algorytmus
	//boolean r;//l�sung relevant / in Ortnung
	boolean f;//l�sung gefunden
	
	public SolvField(byte[][] sud,byte x,byte y,boolean f){
		this.sud=sud;
		this.x=x;
		this.y=y;
		//r=r;
		this.f=f;
	}
	
}
