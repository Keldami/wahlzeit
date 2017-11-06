package org.wahlzeit.model;


// null coordinate for location
public class NoWhereCoordinate extends Coordinate{

    private double x, y, z;

    public NoWhereCoordinate() {
        super();
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.z = Double.NaN;
    }

    public double getX() {
        return Double.NaN;
    }

    public double getY() {
        return Double.NaN;
    }

    public double getZ() {
        return Double.NaN;
    }

    public void setX(){
        throw new IllegalArgumentException("You can't set x to nowhere");
    }

    public void setY(){
        throw new IllegalArgumentException("You can't set y to nowhere");
    }

    public void setZ(){
        throw new IllegalArgumentException("You can't set z to nowhere");
    }

    public double getDistance(Coordinate other) {
        return Double.NaN;
    }

    public boolean isEqual(Coordinate other) {
        return false;
    }

    public String toString() {
        return "Coordinate: x=" + x + " y=" + y + " z=" + z;
    }
}
