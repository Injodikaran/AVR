package Client;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;

public class SudokuTimerTask extends Thread {

	private Thread thread = null;
	private long time = 0;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
	private String[] split;
	private String min, sec;

	private final StringProperty timer;
    public String getFirstName() {
        return timer.get();
    }

    public void setFirstName(String firstName) {
        this.timer.set(firstName);
    }

    SudokuController ui;
    public SudokuTimerTask(SudokuController controller)
    {
    	this.timer = new SimpleStringProperty();
    	ui = controller;
    }
    @Override
    public void run()
    {
    	try
    	{
    		while(!thread.interrupted())
    		{
			sleep(1000);
    			this.time = this.time + 1000;
    			this.getTime();
    		}
    	}
    	catch(Exception ex)
    	{}
    }


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

    public void startTimer()
    {
    	thread = new Thread(this);
    	thread.start();
    }

    public void stopTimer()
    {
    	if(thread != null)
    	{
    		thread.interrupt();
    	}
    }
}