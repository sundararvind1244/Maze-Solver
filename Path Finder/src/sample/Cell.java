package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private boolean isObstacle;
    private boolean isExplored;
    private boolean isEmpty;
    private Coordinate gridPosition;
    public Cell(int width, int height,Coordinate coordinate){
        isEmpty = true;
        isExplored = false;
        isObstacle = false;
        gridPosition = coordinate;
        this.setWidth(width);
        this.setHeight(height);
        this.setStrokeWidth(3);
        //this.setStroke(Paint.valueOf("Black"));
    }
    public void colorRect(String color){
        this.setFill(Paint.valueOf(color));
    }

    public boolean isObstacle(){
        return isObstacle;
    }
    public boolean isExplored(){
        return isExplored;
    }
    public boolean isEmpty(){
        return isEmpty;
    }
    public Coordinate getGridPosition(){
        return gridPosition;
    }

}
