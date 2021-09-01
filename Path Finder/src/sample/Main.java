package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends Application {

          //private Grids grids = new Grids();

    private final GridPane root = new GridPane();
    private final BorderPane borderPane = new BorderPane();
    private int rows;
    private int columns;
    private static Coordinate start;
    private static Coordinate end;
    private MazeCreator mazeCreator;
    private MazeSolve mazeSolver;
    private VBox vbox = new VBox();
    private Button generate;
    private Button solve;
    private TextField numberOfRows;
    private TextField numberOfColumns;
    private static ArrayList<Coordinate> coordinates = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("grids.fxml"));
        rows = 60;
        columns = 60;
        mazeCreator = new MazeCreator(rows,columns);
        mazeSolver = new MazeSolve(rows, columns);
        setGrids(rows,columns);
        //numberOfColumns = new TextField();
       // numberOfColumns.setPromptText("Number of columns for the maze");
       // numberOfRows = new TextField();
        //numberOfRows.setPromptText("Number of rows for the maze");
        root.setPrefSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        borderPane.setPrefSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        borderPane.setCenter(root);
        //generate = new Button("Generate Maze");
        //generate.setOnAction(this::generateEvent);


        solve = new Button("Solve Maze");
        solve.setOnAction(this::solve);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(20,10,0,10));
        vbox.getChildren().addAll(solve);
        borderPane.setRight(vbox);
        borderPane.setStyle("-fx-background-color: black; ");
        root.setStyle("-fx-background-color: black; ");
        primaryStage.setTitle("Path Finder");
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }
    public static void addCoordinates(Coordinate c){
        coordinates.add(c);
    }
    private void setGrids(int rows, int cols){
        for(int i = 0; i<cols; i++){
            for(int j = 0; j<rows; j++){
                Cell cell = new Cell(15,15, new Coordinate(i,j,"Red"));
                cell.colorRect("black");
                root.add(cell,i,j);
            }

        }
       setMaze();
    }
    private void setMaze(){
        mazeCreator.createMaze();
        colorGrids(coordinates);
    }

    public static void conveyPositions(Coordinate start,Coordinate finsih){
        Main.start = start;
        Main.end = finsih;
    }
    private void setPath(){
        for(int i =0;i<columns; i++){
            for(int j = 0; j< rows; j++){
                Cell cell = new Cell(25,25,new Coordinate(i,j,"Red"));
                cell.colorRect((""));
                root.add(cell,i,j);
            }
        }

    }





    private void removeGrids(){
        mazeCreator.clearCoordinates();
    }
    private void colorGrids(ArrayList<Coordinate> coordinatesToColor){
        System.out.println(coordinatesToColor.size());
        for(Coordinate c: coordinatesToColor){
            Cell cell = new Cell(15,15,c);
            cell.colorRect("whitesmoke");
            root.add(cell,c.getRow(), c.getColumn());
        }

        Cell starting = new Cell(15, 15, Main.start);
        starting.colorRect("orchid");
        root.add(starting, Main.start.getRow(),
                Main.start.getColumn());
        Cell ending = new Cell(15, 15, Main.end);
        ending.colorRect("lime");
        root.add(ending, Main.end.getRow(), Main.end.getColumn());


    }
    public void generateEvent(ActionEvent event){
        removeGrids();
        setGrids(rows,columns);
    }
    public void solve(ActionEvent event){
        MazeSolve.findPath();
        setSolution();
        solve.setDisable(true);
    }
    private void setSolution(){
        ArrayList<Coordinate> list= mazeSolver.getPath();
        for(int i = 0; i<list.size();i++){
            addCoordinate(list.get(i));
            //delay(50);
        }
        Cell ending = new Cell(15, 15, Main.end);
        ending.colorRect("lime");
        root.add(ending, Main.end.getRow(), Main.end.getColumn());
    }
    private void addCoordinate(Coordinate c){
        Cell cell = new Cell(10,10,c);
        cell.colorRect("gold");

        root.add(cell,c.getRow(),c.getColumn());
        //delay(60);
    }
    private void delay(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
