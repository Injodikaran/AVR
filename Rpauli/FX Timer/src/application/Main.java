package application;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    KTimer ktimer;
    Label timeLabel; 
  
public void ll(){
    ktimer = new KTimer();
    timeLabel = new Label(ktimer.getSspTime().get());
    ktimer.getSspTime().addListener(new InvalidationListener() {

      
		@Override
		public void invalidated(javafx.beans.Observable arg0) {
			timeLabel.setText(ktimer.getSspTime().get());
			
		}
    });
}
    @Override
    public void start(Stage primaryStage) {
    	VBox vbox = new VBox();
    	
    	primaryStage.setTitle("Sudoku");

       
        Button btn = new Button();
        btn.setText("Start");
        Button btnn = new Button();
        btnn.setText("Stop");
        vbox.getChildren().addAll(btn,btnn,timeLabel);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ktimer.start();
            }
        });
        btnn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ktimer.stopTimer(0);
            }
        });
        
        
     
        
        StackPane root = new StackPane();
    
        root.getChildren().add(vbox);
       
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
