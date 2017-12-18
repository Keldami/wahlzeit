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

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.CoordinateUtil;

/**
 * Spheric coordinate class implementation
 */
@Subclass
public class SphericCoordinate extends AbstractCoordinate {


    private double latitude;
    private double longitude;
    private double radius;

    private SphericCoordinate(double latitude, double longitude, double radius) throws Exception{
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setRadius(radius);
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getRadius() {
        return radius;
    }


    /**
     * private method used in constructor, <b>don't</b> use for further mutation
     * @param latitude
     */
    private void setLatitude(double latitude) throws Exception{
        CoordinateUtil.assertSphericParameter(latitude, 180, 0, "latitude");
        this.latitude = latitude;
    }

    /**
     * private method used in constructor, don't use for further mutation
     * @param longitude
     */
    private void setLongitude(double longitude) throws Exception{
        CoordinateUtil.assertSphericParameter(longitude, 180, -180, "longitude");
        this.longitude = longitude;

    }

    /**
     * private method used in constructor, don't use for further mutation
     * @param radius
     */
    private void setRadius(double radius) throws Exception{
        CoordinateUtil.assertSphericParameter(radius, Double.POSITIVE_INFINITY, 0, "radius");
        this.radius = radius;

    }


    // \,\zeta =\arccos \left(\sin(\phi _{A})\cdot \sin(\phi _{B})+\cos(\phi _{A})\cdot \cos(\phi _{B})\cdot \cos(\lambda _{B}-\lambda _{A})\right)
    // ( arccos(sin(latitude(a)) * sin(latitude(b) + cos(latitude(a)) * cos (latitude(b)) * cos(longitude(a) -longitude(b)) ) * radius
    @Override
    public double getSphericDistance(Coordinate other) throws Exception{

        //could works without start, but it's a safer implementation(?)
        SphericCoordinate start = this.asSphericCoordinate();
        SphericCoordinate end = other.asSphericCoordinate();

        CoordinateUtil.assertAllSphericParameters(
                start.getLatitude(),
                start.getLongitude(),
                start.getRadius());

        CoordinateUtil.assertAllSphericParameters(
                end.getLatitude(),
                end.getLongitude(),
                end.getRadius());

        double distance;
        double sinlata = Math.toRadians(Math.sin(start.getLatitude()));
        double sinlatb = Math.toRadians(Math.sin(end.getLatitude()));
        double coslata = Math.toRadians(Math.cos(start.getLatitude()));
        double coslatb = Math.toRadians(Math.cos(end.getLatitude()));
        double coslongalongb = Math.toRadians(Math.cos(start.getLongitude()) - Math.cos(end.getLongitude()));
        distance = radius * Math.acos(sinlata * sinlatb + coslata * coslatb * coslongalongb);

        CoordinateUtil.assertDistance(distance, "spheric distance");

        return distance;
    }

    @Override
    public double getCartesianDistance(Coordinate other) throws Exception{
        return this.asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
    }



    /**
     * @methodtype interpreter
     * returnsa new CartesianCoordinate
     * to update cartesian coordinates
     *     x = radius * cos(latitude) * sin(longitude)
     *     y = radius * sin(latitude)
     *     z = -radius * cos(latitude) * cos(longitude)
     * @see CartesianCoordinate#asCartesianCoordinate()
     * @see SphericCoordinate#asCartesianCoordinate()
     * @return instance of CartesianCoordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws Exception{

        double x = this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                * Math.sin(Math.toRadians(this.getLongitude()));

        double y = this.getRadius() * Math.sin(Math.toRadians(this.getLatitude()));

        double z = -this.getRadius() * Math.cos(Math.toRadians(this.getLatitude()))
                * Math.cos(Math.toRadians(this.getLongitude()));

        CoordinateUtil.assertAllCartesianParameters(x, y, z);

        return CartesianCoordinate.create(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public boolean isEqual(Coordinate other) {

        if(!(other instanceof SphericCoordinate)) {
            return false;
        }
        try {
            if (Double.compare(this.getLatitude(), other.asSphericCoordinate().getLatitude()) == 0 &&
                    Double.compare(this.getLongitude(), other.asSphericCoordinate().getLongitude()) == 0 &&
                    Double.compare(this.getRadius(), other.asSphericCoordinate().getRadius()) == 0) {
                return true;
            }
        } catch (Exception e) {
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

        if (obj instanceof SphericCoordinate && this.isEqual( (Coordinate) obj )){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int ret = 37;
        ret = 41 * ret + Double.valueOf(this.getLatitude()).hashCode();
        ret = 41 * ret + Double.valueOf(this.getLongitude()).hashCode();
        ret = 41 * ret + Double.valueOf(this.getRadius()).hashCode();

        return ret;
    }


    /**
     * returns current coordinate to string
     * @return string
     */
    public String toString() {
        return "SphericCoordinate: {latitude=" + this.getLatitude() + " longitude=" +
                this.getLongitude() + " radius=" + this.getRadius() + " }";
    }

    public static SphericCoordinate create(double latitude, double longitude, double radius) throws Exception {
        return new SphericCoordinate(latitude, longitude, radius);
    }

}
