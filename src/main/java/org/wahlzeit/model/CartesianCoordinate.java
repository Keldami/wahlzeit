package org.wahlzeit.model;


import org.wahlzeit.utils.CoordinateUtil;

import java.lang.Math;
/*
 * Coordinate gets cartesian coordinates of a picture
 */
public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;


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
        CoordinateUtil.assertCartesianParameter(x, "x");
        this.x = x;

    }

    /*
     *  @methodtype set
     */
    public void setY(double y) {
        CoordinateUtil.assertCartesianParameter(y, "y");
        this.y = y;

    }

    /*
     *  @methodtype set
     */
    public void setZ(double z) {
        CoordinateUtil.assertCartesianParameter(z, "z");
        this.z = z;
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

        CoordinateUtil.assertAllCartesianParameters(
                other.asCartesianCoordinate().getX(),
                other.asCartesianCoordinate().getY(),
                other.asCartesianCoordinate().getZ());

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

    //https://en.wikipedia.org/wiki/Great-circle_distance#Computational_formulas
    @Override
    public double getSphericDistance(Coordinate other) {
        return this.asSphericCoordinate().getSphericDistance(other.asSphericCoordinate());
    }


    //@methodtype interpreter
    //returns either a new SphericCoordinate or creates a specific for this CartesianCoordinate
    //    to update spheric coordinate object
    //    radius = sqrt(x^2 + y^2 + z^2)
    //    latitude = arcsin(y/radius)
    //    longitude = atan2(x, -z)
    @Override
    public SphericCoordinate asSphericCoordinate() {

        SphericCoordinate asSpheric = new SphericCoordinate();

        double radius = Math.sqrt(square(this.getX()) + square(this.getY()) + square(this.getZ()));
        double latitude = Math.toDegrees(Math.asin(this.getY() / radius));
        double longitude = Math.toDegrees(Math.atan2(this.getX(), -this.getZ()));

        CoordinateUtil.assertAllSphericParameters(latitude, longitude, radius);

        asSpheric.setLatitude(latitude);
        asSpheric.setLongitude(longitude);
        asSpheric.setRadius(radius);


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


}
