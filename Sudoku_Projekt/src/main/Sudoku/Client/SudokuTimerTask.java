package client;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class SudokuTimerTask extends Thread {

	private Thread thread = null;
	private long time = 0;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:S");
	private String[] split;
	private String min, sec;
	private StringProperty timerLabel = new SimpleStringProperty() {

		@Override
		public void set(String value) {
			// TODO Auto-generated method stub

		}

		@Override
		public String get() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void removeListener(InvalidationListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addListener(InvalidationListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeListener(ChangeListener<? super String> listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addListener(ChangeListener<? super String> listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getBean() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void unbind() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isBound() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void bind(ObservableValue<? extends String> observable) {
			// TODO Auto-generated method stub

		}
	};

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

    	System.out.println(String.format("%s:%s", min,sec));
    	this.timerLabel.set(String.format("%s:%s", min,sec));
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

	public StringProperty getTimerLabel() {
		return timerLabel;
	}
}