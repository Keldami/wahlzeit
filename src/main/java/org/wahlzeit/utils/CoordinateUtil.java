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

package org.wahlzeit.utils;

/**
 * Helper class
 * Gives assertions for Coordinate-Classes
 * @see {@link org.wahlzeit.model.Coordinate}
 */
public class CoordinateUtil {


    /**
     * Asserts the Cartesian-Parameters
     * @param val
     * @param parameterName
     */
    public static void assertCartesianParameter(double val, String parameterName) throws Exception{

        isNotNull(val, parameterName);

        if(Double.isNaN(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is NaN for " + parameterName);
        }
        if(Double.isInfinite(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is infinite for " + parameterName);
        }
    }


    /**
     * Asserts Spheric parameters
     * @param val
     * @param upperRange
     * @param lowerRange
     * @param parameterName
     */
    public static void assertSphericParameter(double val, double upperRange, double lowerRange, String parameterName) throws Exception{


        isNotNull(val, parameterName);

        if(Double.isNaN(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is NaN for " + parameterName);
        }

        if(Double.isInfinite(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is infinite for " + parameterName);
        }

        if (val > upperRange) {
            throw new IllegalArgumentException("IllegalArgumentException: over the upperRange for " + parameterName);

        } else if(val < lowerRange) {

            throw new IllegalArgumentException("IllegalArgumentException: under the lowerRange for " + parameterName);
        }
    }


    /**
     * Asserts all cartesian coordinate parameters
     * Default Names are x, y, z for CartesianCoordinates
     * @see {@link org.wahlzeit.model.CartesianCoordinate}
     * @param x
     * @param y
     * @param z
     */
    public static void assertAllCartesianParameters(double x, double y, double z) throws Exception{
        assertCartesianParameter(x, "x");
        assertCartesianParameter(y, "y");
        assertCartesianParameter(z, "z");
    }

    /**
     * Asserts all spheric coordinate parameters
     * note that assert is for degrees (you set values in degrees), calculated in radians
     * @see {@link org.wahlzeit.model.SphericCoordinate}
     * @param latitude
     * @param longitude
     * @param radius
     */
    public static void assertAllSphericParameters(double latitude, double longitude, double radius) throws Exception{

        assertSphericParameter(latitude, 180, 0.0, "latitude");
        assertSphericParameter(longitude, 180, -180, "longitude");
        assertSphericParameter(radius, Double.POSITIVE_INFINITY, 0, "radius");

    }

    /**
     * Asserts that object is not null
     * @param object
     * @param parameterName
     * @param <T>
     */
    public static <T> void isNotNull(T object, String parameterName) throws Exception{

        if (object == null) {
            throw new IllegalArgumentException("IllegalArgumentException: " + parameterName + " is NULL.");
        }

    }

    /**
     * Asserts distance that
     * <ul>
     *     <li>not null</li>
     *     <li>not NaN</li>
     *     <li>not infinite</li>
     *     <li>distance not negative</li>
     * </ul>
     * @param distance
     * @param parameterName
     */
    public static void assertDistance(double distance, String parameterName) throws Exception{

        isNotNull(distance, parameterName);

        if(Double.isNaN(distance)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is NaN for " + parameterName);
        }

        if( Double.isInfinite(distance) ) {
            throw new IllegalStateException("IllegalStateException: " + parameterName + " is INFINITE.");
        }

        if (distance < 0 ) {
            throw new IllegalStateException("IllegalStateException: " + parameterName + " is NEGATIVE.");
        }
    }

}
