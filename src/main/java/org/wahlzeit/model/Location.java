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
        try {
            setCoordinate(coordinate);
        }
        catch (Exception e) {
            //do nothing
        }
    }

    /**
     * @methodtype constructor
     * default is new CartesianCoordinate
     * @throws Exception
     */
    public Location() {
        try {
            setCoordinate(CartesianCoordinate.create(0, 0, 0));
        }
        catch (Exception e) {}
    }

    /**
     * Sets a Coordinate
     * @param other
     */
    public void setCoordinate(Coordinate other) throws Exception{
        if (other == null) {
            throw new IllegalArgumentException("Can not set null as coordinate");
        }
        if ( other instanceof CartesianCoordinate) {
            this.coordinate = CartesianCoordinate.create(((CartesianCoordinate) other).getX(),
                    ((CartesianCoordinate) other).getY(),
                    ((CartesianCoordinate) other).getZ()
            );
        }
        else if (other instanceof SphericCoordinate ) {
            this.coordinate = SphericCoordinate.create(
                    ((SphericCoordinate) other).getLatitude(),
                    ((SphericCoordinate) other).getLongitude(),
                    ((SphericCoordinate) other).getRadius());
        }
        else {
            //NoWhereCoordinate immutable by default
            this.coordinate = new NoWhereCoordinate();
        }
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
     * @return the distance between 2 coordinates of -1 if getDistance failed
     */
    public double getDistance(Coordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("You can't get distance to null");
        }
        try {
            return coordinate.getDistance(other);
        } catch (Exception e) {
            //TODO sad return of -1
            return -1;
        }
    }
}
