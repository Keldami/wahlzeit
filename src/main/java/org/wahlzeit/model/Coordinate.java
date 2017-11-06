package org.wahlzeit.model;

import java.lang.Math;
/*
 * Coordinate gets cartesian coordinates of a picture
 */
public class Coordinate {
    private double x;
    private double y;
    private double z;

    /*
     *  @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    /*
     *  @methodtype constructor
     */
    public Coordinate() {
        this.setX(0);
        this.setY(0);
        this.setZ(0);
    }

    /*
     *  @methodtype get
     */
    public double getX() {
        return x;
    }

    /*
     *  @methodtype get
     */
    public double getY() {
        return y;
    }

    /*
     *  @methodtype get
     */
    public double getZ() {
        return z;
    }

    /*
     *  @methodtype set
     */
    public void setX(double x) {
        if(Double.isNaN(x) || Double.isInfinite(x)){
            throw new IllegalArgumentException("The double value for x is too big");
        }
        this.x = x;
    }

    /*
     *  @methodtype set
     */
    public void setY(double y) {
        if(Double.isNaN(y) || Double.isInfinite(y)){
            throw new IllegalArgumentException("The double value for y is too big");
        }
        this.y = y;
    }

    /*
     *  @methodtype set
     */
    public void setZ(double z) {
        if(Double.isNaN(z) || Double.isInfinite(z)){
            throw new IllegalArgumentException("The double value for z is too big");
        }
        this.z = z;
    }

    /*
     *  @methodtype set
     */
    public void setCoordinates(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /*
     * Get's the euclidian distance to another coordinate from the current locations coordinate
     * @methodtype get
     */
    public double getDistance(Coordinate other) {

        double diffx = this.getX() - other.getX();
        double diffy = this.getY() - other.getY();
        double diffz = this.getZ() - other.getZ();

        double ret = Math.sqrt(square(diffx) + square(diffy) + square(diffz));
        if (Double.isInfinite(ret)){
            throw new IllegalStateException("The distance is infinity. Illegal state.");
        }
        return ret;
    }
    /*
     * compares to a coordinate
     * if the coordinates are the same, the locations  needn't be equal !!
     */
    public boolean isEqual(Coordinate other) {
        if( Double.compare(this.getX(), other.getX()) == 0 &&
                Double.compare(this.getY(), other.getY()) == 0 &&
                Double.compare(this.getZ(), other.getZ()) == 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof Coordinate && this.isEqual( (Coordinate) obj )){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){

        int ret = 17;
        ret = 31 * ret + Double.valueOf(this.getX()).hashCode();
        ret = 31 * ret + Double.valueOf(this.getY()).hashCode();
        ret = 31 * ret + Double.valueOf(this.getZ()).hashCode();

        return ret;
    }

    protected double square(double val) {

        double ret = Math.pow(val, 2);
        if(Double.isInfinite(ret)) {
            throw new IllegalStateException("Overflow at double square");
        }
        return ret;
    }

    /*
     * Current Coordinate to String
     */
    public String toString() {
        return "Coordinate: x=" + x + " y=" + y + " z=" + z;
    }
}
