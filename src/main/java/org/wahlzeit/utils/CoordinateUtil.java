package org.wahlzeit.utils;

public class CoordinateUtil {


    public static void assertCartesianParameter(double val, String parameterName) {

        isNotNull(val, parameterName);

        if(Double.isNaN(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is NaN for " + parameterName);
        }
        if(Double.isInfinite(val)) {
            throw new IllegalArgumentException("IllegalArgumentException: Double is infinite for " + parameterName);
        }
    }


    public static void assertSphericParameter(double val, double upperRange, double lowerRange, String parameterName) {

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


    //Default Names are x, y, z for CartesianCoordinates
    public static void assertAllCartesianParameters(double x, double y, double z){
        assertCartesianParameter(x, "x");
        assertCartesianParameter(y, "y");
        assertCartesianParameter(z, "z");
    }

    //note that assert is for degrees (you set values in degrees), calculated in radians
    public static void assertAllSphericParameters(double latitude, double longitude, double radius){
        assertSphericParameter(latitude, 180, 0.0, "latitude");
        assertSphericParameter(longitude, 180, -180, "longitude");
        assertSphericParameter(radius, Double.POSITIVE_INFINITY, 0, "radius");
    }

    public static <T> void isNotNull(T object, String parameterName){
        if (object == null) {
            throw new IllegalArgumentException("IllegalArgumentException: " + parameterName + " is NULL.");
        }
    }


}
