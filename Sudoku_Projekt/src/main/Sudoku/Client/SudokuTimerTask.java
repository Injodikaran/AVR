package Client;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;

public class SudokuTimerTask extends Thread {

	private volatile boolean running = true;
	private long time = 0;
    private SudokuController ui;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
	private String[] split;
	private String min, sec;
	private final StringProperty timer;
	
	// Konstruktor
    public SudokuTimerTask(SudokuController controller)
    {
    	this.timer = new SimpleStringProperty();
    	ui = controller;
    }
    
    public String getFirstName() {
        return timer.get();
    }

    public void setFirstName(String firstName) {
        this.timer.set(firstName);
    }

	@Override
	public void run() {
		try {
			while (true) {
				if(running)
				{
					sleep(1000);
					this.time = this.time + 1000;
					this.getTime();
				}
			}
		} catch (Exception ex) {
		}
	}

	// Timer pausieren
    public void pauseThread() throws InterruptedException
    {
        running = false;
    }

    // Timer reaktivieren
    public void resumeThread()
    {
        running = true;
    }
    // Timer Reseten
    public void resetThread()
    {
        time = 0;
        min = "00";
        sec = "00";
    }

    // Anzeige Zeit
    public void getTime()
    {
    	split = sdf.format(new Date(time)).split(":");
    	min = split[0];
    	sec = split[1];

    	 Platform.runLater(new Runnable(){
             @Override public void run() {
        		 ui.setTime(String.format("%s:%s", min,sec));
             }
         });
	System.out.println(String.format("%s:%s", min,sec));
    }
}