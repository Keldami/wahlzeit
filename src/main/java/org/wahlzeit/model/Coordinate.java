package org.wahlzeit.model;


/*
* Interface for generic coordinate-classes
*/
public interface Coordinate {


    // @methodtype interpreter method for CartesianCoordinates
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate other);


    // @methodtype interpreter method for SphericCoordinates
    SphericCoordinate asSphericCoordinate();

    double getSphericDistance(Coordinate other);


    //get's the distance to another coordinate
    double getDistance(Coordinate other);

    //returns true if location and type is equal to other
    //else returns false
    boolean isEqual(Coordinate other);

}
