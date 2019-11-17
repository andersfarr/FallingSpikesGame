package game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class GameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    //private ArrayList<Polygon> spikes = new ArrayList<Polygon>();
    
    private void performAnimation(int numSpike) {
    	DoubleProperty x = new SimpleDoubleProperty();
        DoubleProperty y = new SimpleDoubleProperty();
        Timeline timeline = new Timeline(
        		new KeyFrame(Duration.seconds(0), new KeyValue(x,0), new KeyValue(y,0)),
        		new KeyFrame(Duration.seconds(1), new KeyValue(x,0), new KeyValue(y, 340))	
        );
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);
        ArrayList<Polygon> spikes = new ArrayList<Polygon>();
        for (int i = 0; i< numSpike; i++) {
        	double randomVal = Math.random()*640;
        	Polygon triangle = new Polygon();
        	triangle.getPoints().addAll(new Double[] {0.0 +randomVal,0.0,20.0+randomVal,0.0,10.0+randomVal,60.0});
        	spikes.add(triangle);
        }
        
        AnimationTimer timer = new AnimationTimer() {
        	public void handle(long now) {
        		gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.fillRect(0,0,640,400);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                gc.strokeLine(0,400, 640, 400);
                for(int i= 0; i< numSpike; i++) {
	                Polygon spike = spikes.get(i);
	        		gc.strokePolygon(new double [] {spike.getPoints().get(0),spike.getPoints().get(2),spike.getPoints().get(4)}, 
	        	        		new double[] {spike.getPoints().get(1)+ y.doubleValue(),spike.getPoints().get(3) + y.doubleValue(),spike.getPoints().get(5) + y.doubleValue()}, 3);
                }
		        if(y.doubleValue() >=340) {
        			timeline.stop();
        			this.stop();
        			return;
	        	}
        	}
        };
        timer.start();
    	timeline.play();
    }
    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'gamePane.fxml'.";
       for (int i = 1; i< 10;i++) {
    	   performAnimation(i);
       }
    }
}