package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Grids extends GridPane {

    public Grids(){

        this.setStyle("-fx-background-color: Cyan");

        setGrids();
        this.setGridLinesVisible(true);
    }
    private void setGrids(){
        for(int i = 0; i<10; i++){
            for(int j = 0; j < 10; j++){
                AnchorPane pane = new AnchorPane();
                pane.setVisible(true);
                pane.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                if(i%2 ==0 && j%2 ==0){


                     pane.setStyle("-fx-background-color: White");
                     this.add(pane,i,j);
                     // this.add(changeColor("White"),i,j,1,1);
                }
                else{
                    pane.setStyle("-fx-background-color: Black");
                    //this.add(changeColor("Black"),i,j,1,1);
                    this.add(pane,i,j);
                }
            }
        }
    }
    private Pane changeColor(String color){
        Pane pane = new Pane();
        String display = "-fx-background-color: "+color;
        pane.setStyle("-fx-background-color: "+color);
        pane.setVisible(true);
        System.out.println(display);
        return pane;
    }
}
