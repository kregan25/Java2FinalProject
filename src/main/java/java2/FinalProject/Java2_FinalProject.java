
package java2.FinalProject;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Java2_FinalProject extends Application{

    public static Boundary createBoundaryObject(String currentLine) {
        double xCoordinate = 0.0;
        double yCoordinate = 0.0;
        Boundary currentBoundary;

        // split current line into an array of strings
        String[] arrayOfString = currentLine.split("[,()]");

        List<String> arrayNoBlankElements = new ArrayList<String>();
        for (int i = 0; i < arrayOfString.length; i++) {
            if (arrayOfString[i] != "") {
                arrayNoBlankElements.add(arrayOfString[i]);
            }
        }


            //parse the first element as a double for price and create new Boundary object
            double price = Double.parseDouble(arrayOfString[0]);
            currentBoundary = new Boundary(price);


            //loop through remaining elements to get all boundaries of the current plot
            for (int i = 1; i < arrayNoBlankElements.size(); i += 2) {

                xCoordinate = Double.parseDouble(arrayNoBlankElements.get(i));
                yCoordinate = Double.parseDouble(arrayNoBlankElements.get(i+1));

                currentBoundary.addBoundaryPair(xCoordinate, yCoordinate);
            }

        return currentBoundary;
        }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Image plotGraphic = new Image("PlotsByPrice.JPG");

        ImageView imageView = new ImageView();
        imageView.setImage(plotGraphic);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 750, 750);

        primaryStage.setTitle("Final Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException {


        ArrayList<Boundary> BoundariesOfAlLPPlots = new ArrayList<Boundary>();
        Boundary currentPlot;
        double price;
        double xCoordinate;
        double yCoordinate;
        FileInputStream fstream = new FileInputStream("./CompanyX.dat");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String currentLine;


        //read file line by line
        try {
            while((currentLine = br.readLine()) != null) {
                //create a function that turns the line into a boundary object - use string.split
                //turns the string into an array of strings
                System.out.println(currentLine);

                currentPlot = createBoundaryObject(currentLine);
                System.out.println(currentPlot.getPrice());

                currentPlot.setAreaOfPlot();
                System.out.println(currentPlot.getArea());
                currentPlot.setCostPerSquareFoot();
                System.out.println(currentPlot.getCostPerSquareFoot());
                BoundariesOfAlLPPlots.add(currentPlot);

                fstream.close();
            }
        } catch (IOException ioe) {
            System.out.println("End of file");
        }

        launch();
    }

}
