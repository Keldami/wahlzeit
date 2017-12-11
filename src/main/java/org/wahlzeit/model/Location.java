package org.wahlzeit.model;


/**
 * Location class for Photo class
 * needs @see {@link Coordinate} to work properly
 * You have to first set Location in Photo.class to use it and get a Coordinate
 */
public class Location {

    private Coordinate coordinate;

    /*
     *  @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        if(coordinate == null) {
            throw new IllegalArgumentException("Coordinate can't be null");
        }
        setCoordinate(coordinate);
    }

    /*
     *  @methodtype constructor
     *  default is new CartesianCoordinate
     */
    public Location() {
        setCoordinate(new CartesianCoordinate());
    }

    /**
     * Sets a Coordinate
     * @param other
     */
    public void setCoordinate(Coordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("Can not set null as coordinate");
        }
        this.coordinate = other;
    }

    /*
     *  @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Calculates the distance between two coordinates
     * Gives different types of distances for different types of coordinates
     * @see {@link SphericCoordinate#getSphericDistance(Coordinate)}
     * @see {@link CartesianCoordinate#getCartesianDistance(Coordinate)}
     * @param other
     * @return
     */
    public double getDistance(Coordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("You can't get distance to null");
        }
        return coordinate.getDistance(other);
    }
}
