package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Coordinate {
    private int row;
    private int column;
    private String color;
    private HashMap<Integer, Coordinate> adjacent = new HashMap<>();
    private ArrayList<Coordinate> adjacentEight= new ArrayList<>();
    private ArrayList<Coordinate> adjacent4= new ArrayList<>();
    private boolean isObstacle;
    private boolean isExplored;
    private boolean isEmpty;
    private int cost;
    private int benefit;
    private int netBenefit;


    public Coordinate(int row, int column, String color){
        isEmpty = true;
        isExplored = false;
        isObstacle = false;
        this.row = row;
        this.column = column;
        this.color = color;
        cost=0;
        benefit=0;
        netBenefit= 0;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row && column == that.column;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    private void setAdjacent(Coordinate c){
        adjacent.put(0,new Coordinate(c.getRow(),c.getColumn()-1,"Cyan"));
        adjacent.put(1,new Coordinate(c.getRow()+1,c.getColumn(),"Cyan"));
        adjacent.put(2,new Coordinate(c.getRow(),c.getColumn()+1,"Cyan"));
        adjacent.put(3,new Coordinate(c.getRow()-1,c.getColumn(),"Cyan"));
    }
    private void setEightAdjacents(Coordinate c){
        adjacentEight.add(new Coordinate(c.getRow(),c.getColumn()-1,"Cyan"));
        adjacentEight.add(new Coordinate(c.getRow()+1,c.getColumn(),"Cyan"));
        adjacentEight.add(new Coordinate(c.getRow()-1,c.getColumn()-1,"Cyan"));
        adjacentEight.add(3,new Coordinate(c.getRow()+1,c.getColumn()-1,"Cyan"));
        adjacentEight.add(4,new Coordinate(c.getRow()-1,c.getColumn()+1,"Cyan"));
        adjacentEight.add(5,new Coordinate(c.getRow(),c.getColumn()+1,"Cyan"));
        adjacentEight.add(6,new Coordinate(c.getRow()-1,c.getColumn(),"Cyan"));
        adjacentEight.add(7,new Coordinate(c.getRow()+1,c.getColumn()+1,"Cyan"));


    }
    private void setAdjacent4(Coordinate c){
        adjacent4.add(0,new Coordinate(c.getRow(),c.getColumn()-1,"Cyan"));
        adjacent4.add(1,new Coordinate(c.getRow()+1,c.getColumn(),"Cyan"));
        adjacent4.add(2,new Coordinate(c.getRow(),c.getColumn()+1,"Cyan"));
        adjacent4.add(3,new Coordinate(c.getRow()-1,c.getColumn(),"Cyan"));
    }
    public ArrayList<Coordinate> getAdjacent4(Coordinate c){
        setAdjacent4(c);
        return adjacent4;
    }
    public HashMap<Integer,Coordinate> getAdjacentCoordinates(Coordinate c){
        setAdjacent(c);
        return adjacent;
    }
    public ArrayList<Coordinate> getAdjacentEight(Coordinate c){
        setEightAdjacents(c);
        return adjacentEight;
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
    public void setObstacle(){
        isObstacle = true;
        isEmpty = false;
    }
    public void setExplored(){
        isExplored = true;
        isEmpty = false;
    }
    public void setCost(int cost){
        this.cost = 0;
        this.cost = cost;
    }
    public void setBenefit(int Benefit){
        this.benefit = benefit;
    }
    public void setNetBenefit(){
        netBenefit = this.benefit - this.cost;
    }
    public int getCost(){
        return cost;
    }
    public int getBenefit(){
        return benefit;
    }
    public int getNetBenefit(){
        return netBenefit;
    }


}
