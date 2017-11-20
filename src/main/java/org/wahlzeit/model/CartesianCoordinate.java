package org.wahlzeit.model;

import java.lang.Math;
/*
 * Coordinate gets cartesian coordinates of a picture
 */
public class CartesianCoordinate implements Coordinate{

    private double x;
    private double y;
    private double z;
    private SphericCoordinate asSpheric = null;

    /*
     *  @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    /*
     *  @methodtype constructor
     */
    public CartesianCoordinate() {
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

        if(asSpheric != null) {
            updateSphericCoordinate(asSpheric);
        }
    }

    /*
     *  @methodtype set
     */
    public void setY(double y) {
        if(Double.isNaN(y) || Double.isInfinite(y)){
            throw new IllegalArgumentException("The double value for y is too big");
        }
        this.y = y;

        if(asSpheric != null) {
            updateSphericCoordinate(asSpheric);
        }
    }

    /*
     *  @methodtype set
     */
    public void setZ(double z) {
        if(Double.isNaN(z) || Double.isInfinite(z)){
            throw new IllegalArgumentException("The double value for z is too big");
        }
        this.z = z;

        if(asSpheric != null) {
            updateSphericCoordinate(asSpheric);
        }
    }

    /*
     *  @methodtype set
     */
    public void setCartesianCoordinates(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    // we have asCartesianCoordinate for this but still :)
    // @methodtype get
    public CartesianCoordinate getCartesianCoordinate() { return this; }

    @Override
    public double getCartesianDistance(Coordinate other) {

        double diffx = this.asCartesianCoordinate().getX() - other.asCartesianCoordinate().getX();
        double diffy = this.asCartesianCoordinate().getY() - other.asCartesianCoordinate().getY();
        double diffz = this.asCartesianCoordinate().getZ() - other.asCartesianCoordinate().getZ();

        double ret = Math.sqrt(this.asCartesianCoordinate().square(diffx) +
                               this.asCartesianCoordinate().square(diffy) +
                               this.asCartesianCoordinate().square(diffz));

        if (Double.isInfinite(ret)) {
            throw new IllegalStateException("The distance is infinity. Illegal state.");
        }
        return ret;
    }

    //TODO implement true getSphericDistance
    @Override
    public double getSphericDistance(Coordinate other) {
        return this.asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
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

    //@methodtype interpreter
    //returns either a new SphericCoordinate or creates a specific for this CartesianCoordinate
    @Override
    public SphericCoordinate asSphericCoordinate() {
        if(asSpheric == null) {
            asSpheric = new SphericCoordinate();
            updateSphericCoordinate(asSpheric);
        }

        return asSpheric;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }



    /*
    * compares to a coordinate
    * if the coordinates are the same, the locations  needn't be equal !!
    * We cast other to Cartesian Coordinate because we check if it's a CartesianCoordinate
    * if its not isEquals returns false even if it's in the same location
    */
    @Override
    public boolean isEqual(Coordinate other) {

        if(!(other instanceof CartesianCoordinate)) {
            return false;
        }

        if( Double.compare(this.getX(),  other.asCartesianCoordinate().getX()) == 0 &&
                Double.compare(this.getY(), other.asCartesianCoordinate().getY()) == 0 &&
                Double.compare(this.getZ(), other.asCartesianCoordinate().getZ()) == 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof CartesianCoordinate && this.isEqual( (Coordinate) obj )){
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
        return "Coordinate: {x=" + x + " y=" + y + " z=" + z + " }";
    }


    //to update spheric coordinate object
//    radius = sqrt(x^2 + y^2 + z^2)
//    latitude = arcsin(y/radius)
//    longitude = atan2(x, -z)
    private void updateSphericCoordinate(SphericCoordinate asSpheric) {
        if(asSpheric != null) {
            double radius = Math.sqrt(square(this.getX()) + square(this.getY()) + square(this.getZ()));
            double latitude = Math.toDegrees(Math.asin(this.getY() / radius));
            double longitude = Math.toDegrees(Math.atan2(this.getX(), -this.getZ()));

            asSpheric.setLatitude(latitude);
            asSpheric.setLongitude(longitude);
            asSpheric.setRadius(radius);
        }
    }
}
