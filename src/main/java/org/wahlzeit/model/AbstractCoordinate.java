package org.wahlzeit.model;


/**
 * A generic implementation of a Coordinate
 */
public class AbstractCoordinate implements Coordinate{
    public AbstractCoordinate() {
    }

    @Override
    public boolean isEqual(Coordinate other) {
        return false;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return 0;
    }

    /*
    * Get's the distance to another coordinate from the current locations coordinate
    * @methodtype get
    */
    public double getDistance(Coordinate other) {

        if(other instanceof CartesianCoordinate){

            return this.getCartesianDistance(other);

        } else if(other instanceof SphericCoordinate){

            return this.getSphericDistance(other);

        } else {
            //for NoWhereCoordinate or undefined coordinate
            return Double.NaN;

        }
    }

    @Override
    public double getSphericDistance(Coordinate other) {
        return 0;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }

}
