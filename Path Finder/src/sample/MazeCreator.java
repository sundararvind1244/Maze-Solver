package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MazeCreator {
    private int rows;
    private int columns;
    private int startRow;
    private int endRow;
    private int startColumn;
    private int endColumn;
    private Coordinate startingPosition;
    private Coordinate endPosition;
    private Coordinate currentCoordinate;
    private Random random = new Random();
    private ArrayList<Coordinate> unusedCoordinates = new ArrayList<>();

    private ArrayList<Coordinate> coordinates = new ArrayList<>();
    private int counter;
    private int numberOfObstacles;
    private static final double PROBABILITY = 0.21;
    public MazeCreator(int rows, int columns){


        startRow = rows - 1;
        endRow = 0;
        this.rows = rows;
        this.columns = columns;
        startRow = rows - 1;
        endRow = 0;
        setStartColumns();
        startingPosition = new Coordinate(startRow,startColumn, "Cyan");
        endPosition = new Coordinate(endRow, endColumn,"Cyan");
        MazeSolve.conveyPositions(startingPosition,endPosition);
        Main.conveyPositions(startingPosition,endPosition);
        counter = 4;
        currentCoordinate = startingPosition;
        numberOfObstacles = (this.rows*this.columns)/rows;
        System.out.println(startColumn+" "+endColumn+" "+ startRow+ " "+ endRow+" "+numberOfObstacles);

    }
    private void setStartColumns(){
          startColumn = random.nextInt(columns-1);
          endColumn = random.nextInt(columns-1);


    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public int getStartColumn(){
        return startColumn;
    }
    public int getEndColumn(){
        return endColumn;
    }
    public int getStartRow(){
        return startRow;
    }
    public int getEndRow(){
        return endRow;
    }
    public void generateMaze(){
         for(int i = 0; i< rows; i++) {
             progressOneStep(currentCoordinate);
             System.out.println(counter);
         }

        for(Coordinate c: coordinates){
            System.out.println(c.getRow()+" "+ c.getColumn());
        }

    }
    public Coordinate getStartingPosition(){
        return startingPosition;
    }
    public Coordinate getEndPosition(){
        return endPosition;
    }
    private boolean validIndices(Coordinate c){

        return (c.getRow() >= 0 && c.getRow()<rows && c.getColumn() >= 0 && c.getColumn()< columns);
    }
    private void progressOneStep(Coordinate c){

     System.out.println("Current Row: "+ c.getRow()+ " Current Column"+ c.getColumn());
        if(c.isEmpty()&& counter%3 !=0){
            c.setExplored();
             currentCoordinate = getNextCoordinate(c);
             counter++;
        }
        else if(c.isEmpty()&& counter%3== 0){
            c.setExplored();
            currentCoordinate = getTopCoordinate(c);
            counter++;
        }


    }
    private Coordinate getNextCoordinate(Coordinate c){

        ArrayList<Coordinate> validCoordinates = new ArrayList<>();
        HashMap<Integer, Coordinate> adjacentCoordinates = new HashMap<>(c.getAdjacentCoordinates(c));
        for(Coordinate coordinate : adjacentCoordinates.values()){
            System.out.println("row: "+ coordinate.getRow()+ " column: "+ coordinate.getColumn());
            if(!validIndices(coordinate)){
                System.out.println("coordinate to remove:"+coordinate.getRow()+ " "+ coordinate.getColumn());
            }
            else{
                if(coordinate.isEmpty()) {
                    System.out.println("coordinate to add:" + coordinate.getRow() + " " + coordinate.getColumn());
                    validCoordinates.add(coordinate);
                }
            }
        }
        System.out.println(validCoordinates.size());
        int key =  random.nextInt(validCoordinates.size());
        currentCoordinate = validCoordinates.get(key);
        validCoordinates.remove(currentCoordinate);
        changeRandomCoordinates(validCoordinates);
        return currentCoordinate;
    }
    private Coordinate getTopCoordinate(Coordinate c){

        ArrayList<Coordinate> validCoordinates = new ArrayList<>();
        HashMap<Integer, Coordinate> adjacentCoordinates = new HashMap<>(c.getAdjacentCoordinates(c));
        for(Coordinate coordinate : adjacentCoordinates.values()){
            if(!validIndices(coordinate)){

            }
            else{
                validCoordinates.add(coordinate);
            }
        }
        int key = 0;
        currentCoordinate= validCoordinates.get(key);
        validCoordinates.remove(currentCoordinate);
        changeRandomCoordinates(validCoordinates);
        return currentCoordinate;
    }
    private void changeRandomCoordinates(ArrayList<Coordinate> coordinates){

        //if(currentCoordinate.getRow()>0 && currentCoordinate.getColumn()>0 && currentCoordinate.getColumn()<columns-1 && currentCoordinate.getRow() < rows-1) {
            int key = random.nextInt(coordinates.size());
            Coordinate coordinate = coordinates.get(key);
            if (coordinate.isEmpty() && coordinate != endPosition) {
                coordinate.setObstacle();
                coordinate.setColor("Cyan");
                this.coordinates.add(coordinate);

            }

        //}
    }
    public void createMaze(){

        for(int i = 0 ; i<numberOfObstacles; i++){
            for(int j = 0; j<numberOfObstacles; j++){
                Coordinate coordinate = new Coordinate(i,j,"Cyan");
                if(random.nextDouble()<= PROBABILITY){

                    coordinate.setObstacle();
                    coordinates.add(coordinate);
                    Main.addCoordinates(coordinate);
                }
                else{
                    unusedCoordinates.add(coordinate);
                    MazeSolve.setList(coordinate);

                }
            }
        }
    }
    public void clearCoordinates(){
        coordinates.clear();
        setStartColumns();
    }
    public ArrayList<Coordinate> getUnusedCoordinates(){
        return unusedCoordinates;
    }
    public void setRows(int rows){
        this.rows = rows;
    }
    public void setColumns(int columns){
        this.columns = columns;
    }

}
