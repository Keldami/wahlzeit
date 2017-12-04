package org.wahlzeit.model;


/**
 * A generic implementation of a Coordinate
 */
public abstract class AbstractCoordinate implements Coordinate{

    public AbstractCoordinate() {
    }

    @Override
    public abstract boolean isEqual(Coordinate other);

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public abstract double getCartesianDistance(Coordinate other);

    @Override
    public abstract double getSphericDistance(Coordinate other);

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

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

}
