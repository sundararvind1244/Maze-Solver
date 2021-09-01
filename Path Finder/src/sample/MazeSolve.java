package sample;

import java.awt.desktop.SystemEventListener;
import java.util.*;

public class MazeSolve{
    private int cost;
    private int benefit;
    private int netBenefit;
    private boolean isSolved;
    private static int rows;
    private int columns;
    private static Coordinate startingPosition;
    private static Coordinate endingPosition;
    private static Coordinate currentCoordinate;
    private MazeCreator mazeCreator;
    private static ArrayList<Coordinate> path = new ArrayList<>();
    private static ArrayList<Coordinate> availableCoordinates = new ArrayList<>();
    private static int counter;
    public MazeSolve(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        mazeCreator = new MazeCreator(this.rows,this.columns);

        cost = 0;
        benefit = 0;
        netBenefit = benefit - cost;
        isSolved = false;


        currentCoordinate = startingPosition;
        System.out.println("Size = "+availableCoordinates.size());

        System.out.println("Current Coordinate = Row:"+currentCoordinate.getRow());

        System.out.println(availableCoordinates.size());
        System.out.println(startingPosition.getRow());
        counter = 0;

    }
    public static void findPath(){
        while (!currentCoordinate.equals(endingPosition)&&computeCost(currentCoordinate)!=0) {
            if(computeCost(currentCoordinate)==0){
                break;
            }
            else {
                counter++;
                System.out.println(counter);
                step(currentCoordinate);
                System.out.println("Path:" + path.size());
            }

        }
    }
    private static void step(Coordinate c){
       System.out.println(c);

        ArrayList<Coordinate> adjacents = new ArrayList<>(c.getAdjacentEight(c));

        ArrayList<Coordinate> compare = new ArrayList<>();
        HashMap<Integer, Coordinate> costs = new HashMap<>();
        for(Coordinate adjacent: adjacents){
            if(availableCoordinates.contains(adjacent)){

                costs.put(computeCost(adjacent),adjacent);
                compare.add(c);
                System.out.println("Avail");
            }
        }
        if(compare.size()!=0) {
            System.out.println("Notzero");
            Coordinate nextCoordinate = computeBenefit(costs);
            path.add(nextCoordinate);
            currentCoordinate = nextCoordinate;
            availableCoordinates.remove(c);
        }
    }
    private static int computeCost(Coordinate c){
        if(c!=null) {

            int cost = 0;
            cost = ((c.getRow() - endingPosition.getRow()) * (c.getRow() - endingPosition.getRow()) + ((c.getColumn() - endingPosition.getColumn()) * (c.getColumn() - endingPosition.getColumn())));
            System.out.println("Cost:"+cost);
            return cost;
        }
        else {
            System.out.println("Null");
            return 100;
        }
    }

    private static Coordinate computeBenefit(HashMap<Integer,Coordinate> costs){

        List<Integer> list =(new ArrayList<>(costs.keySet()));
        Collections.sort(list);
        return costs.get(list.get(0));
    }
    public ArrayList<Coordinate> getPath(){
        return path;
    }
    public static void main(String[] args){
        MazeSolve solve = new MazeSolve(3,3);

        solve.findPath();
    }
    public static void setList(Coordinate c){
        availableCoordinates.add(c);
    }
    public static void conveyPositions(Coordinate start, Coordinate finish){
        startingPosition = start;
        endingPosition = finish;
    }
}
