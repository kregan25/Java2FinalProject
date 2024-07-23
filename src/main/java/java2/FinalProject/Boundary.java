package java2.FinalProject;

import javafx.util.Pair;

import java.util.ArrayList;

public class Boundary {

    Boundary(double price) {
        priceOfPlot = price;
        plotBoundaries = new ArrayList<Pair>();


    }
    void addBoundaryPair(double xCoordinate, double yCoordinate){
        Pair<Double, Double> currentBoundaryPoint = new Pair<>(xCoordinate, yCoordinate);
        plotBoundaries.add(currentBoundaryPoint);
    }
    double calculateArea(ArrayList<Pair> currentPlotBoundaries){
        double areaInPixels = 0.0;
        double area = 0.0;
        double side1 = 0.0;
        double side2 = 0.0;
        double side3 = 0.0;
        int numberOfcorners = currentPlotBoundaries.size();
        int numberOfTrianges = numberOfcorners - 2;

        //find the area of each interior triangle in pixels
        for(int i = 0; i < numberOfTrianges; i++) {
            side1 = calculateDistanceBetweenTwoPoints(plotBoundaries.get(0), plotBoundaries.get(i + 1));
            side2 = calculateDistanceBetweenTwoPoints(plotBoundaries.get(i + 1), plotBoundaries.get(i + 2));
            side3 = calculateDistanceBetweenTwoPoints(plotBoundaries.get(i + 2), plotBoundaries.get(0));
            double semiPerimeter = (side1 + side2 + side3) / 2.0;
            areaInPixels += Math.sqrt((semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3)));
        }
        //convert area in pixels to area in feet ( 62 pixels = 100 ft)
        area = (areaInPixels/ 62.0) * 100.0;

        return area;
    }
    double calculateDistanceBetweenTwoPoints(Pair<Double, Double> firstPair, Pair<Double, Double> secondPair){
        return Math.sqrt(Math.pow((firstPair.getKey() - secondPair.getKey()), 2) + Math.pow((firstPair.getValue() - secondPair.getValue()), 2));
    }
    double calculateCostPerSquareFoot(){
        return priceOfPlot / areaOfPlot;
    }
    void setCostPerSquareFoot(){
        costPerSquareFoot = calculateCostPerSquareFoot();
    }
    void setAreaOfPlot(){
        areaOfPlot = calculateArea(plotBoundaries);

    }
    Double getArea(){
        return areaOfPlot;
    }
    Double getCostPerSquareFoot(){
        return costPerSquareFoot;
    }
    Double getPrice(){
        return priceOfPlot;
    }


    double areaOfPlot;
    double priceOfPlot;
    double costPerSquareFoot;
    ArrayList<Pair> plotBoundaries;
    Pair<Double, Double> currentPlotBoundary;
}

