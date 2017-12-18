/*
 * Copyright (c) 2017-2018 by Artur Wasinger
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;


import org.wahlzeit.utils.CoordinateUtil;

import java.lang.Math;

/**
 * Cartesian coordinate implementation
 */
public class CartesianCoordinate extends AbstractCoordinate{

    private double x;
    private double y;
    private double z;


    /**
     * @param x
     * @param y
     * @param z
     * @throws Exception
     */
    private CartesianCoordinate(double x, double y, double z) throws Exception{
        this.setX(x);
        this.setY(y);
        this.setZ(z);
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

    /**
     * private method used in constructor, <b>don't</b> use for further mutation
     * @methodtype set
     * @param x
     */
    private void setX(double x) throws Exception{
        CoordinateUtil.assertCartesianParameter(x, "x");
        this.x = x;
    }

    /**
     * private method used in constructor, <b>don't</b> use for further mutation
     * @methodtype set
     * @param y
     */
    private void setY(double y) throws Exception{
        CoordinateUtil.assertCartesianParameter(y, "y");
        this.y = y;
    }

    /**
     * private method used in constructor, <b>don't</b> use for further mutation
     * @methodtype set
     * @param z
     */
    private void setZ(double z) throws Exception{
        CoordinateUtil.assertCartesianParameter(z, "z");
        this.z = z;
    }

    /**
     * we have asCartesianCoordinate for this but still :)
     * @return this instance
     */
    public CartesianCoordinate getCartesianCoordinate() { return this; }

    @Override
    public double getCartesianDistance(Coordinate other) throws Exception{

        //should work without start (as this.asCartesianCoordinate)
        CartesianCoordinate start = this.asCartesianCoordinate();
        CartesianCoordinate end = other.asCartesianCoordinate();

        CoordinateUtil.assertAllCartesianParameters(
                end.getX(),
                end.getY(),
                end.getZ());

        CoordinateUtil.assertAllCartesianParameters(
                start.getX(),
                start.getY(),
                start.getZ());

        double diffx = start.getX() - end.getX();
        double diffy = start.getY() - end.getY();
        double diffz = start.getZ() - end.getZ();

        //could take square()-implementation form library but it doesn't have an exception
        double ret = Math.sqrt(
                start.square(diffx) + start.square(diffy) + start.square(diffz));

        CoordinateUtil.assertDistance(ret, "cartesian distance");

        return ret;
    }

    @Override
    public double getSphericDistance(Coordinate other) throws Exception{
        return this.asSphericCoordinate().getSphericDistance(other.asSphericCoordinate());
    }

    /**
     * @methodtype interpreter
     * returns a new SphericCoordinate
     * to update spheric coordinate object
     * radius = sqrt(x^2 + y^2 + z^2)
     * latitude = arcsin(y/radius)
     * longitude = atan2(x, -z)
     * @see CartesianCoordinate#asCartesianCoordinate()
     * @see SphericCoordinate#asCartesianCoordinate()
     * @throws Exception
     * @return instance of SphericCoordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws Exception{

        double radius = Math.sqrt(square(this.getX()) + square(this.getY()) + square(this.getZ()));
        double latitude = Math.toDegrees(Math.asin(this.getY() / radius));
        double longitude = Math.toDegrees(Math.atan2(this.getX(), -this.getZ()));

        CoordinateUtil.assertAllSphericParameters(latitude, longitude, radius);

        return SphericCoordinate.create(latitude, longitude, radius);
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
        try {
            if (Double.compare(this.getX(), other.asCartesianCoordinate().getX()) == 0 &&
                    Double.compare(this.getY(), other.asCartesianCoordinate().getY()) == 0 &&
                    Double.compare(this.getZ(), other.asCartesianCoordinate().getZ()) == 0) {
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

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


    /**
     * returns square value for val
     * gives an unchecked exception if there is an overflow
     * @param val
     * @return val * val
     */
    protected double square(double val) {

        double ret = Math.pow(val, 2);
        if(Double.isInfinite(ret)) {
            throw new IllegalStateException("Overflow at double square");
        }
        return ret;
    }

    /**
     * returns current coordinate to string
     * @return string
     */
    public String toString() {
        return "Coordinate: {x=" + x + " y=" + y + " z=" + z + " }";
    }

    /**
     * creates new instances of cartesian coordinates
     * used to valueobjectify the class
     * @param x
     * @param y
     * @param z
     * @return
     * @throws Exception
     */
    public static CartesianCoordinate create(double x, double y, double z) throws  Exception{
        return new CartesianCoordinate(x, y, z);
    }

}
