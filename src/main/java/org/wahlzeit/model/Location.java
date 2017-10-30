package org.wahlzeit.model;


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
     */
    public Location() {
        setCoordinate(new Coordinate());
    }

    /*
     *  @methodtype set
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

    /*
     *  @methodtype get
     */
    public double getDistance(Coordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("You can't get distance to null");
        }
        return coordinate.getDistance(other);
    }

    /*
     * if coordinates are equal then the location is true
     */
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Location && coordinate.isEqual( ((Location) obj).getCoordinate())){
            return true;
        }
        return false;
    }
}
