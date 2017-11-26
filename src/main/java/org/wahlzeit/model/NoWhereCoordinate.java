package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Subclass;

// null coordinate for location
public class NoWhereCoordinate implements Coordinate{



    public NoWhereCoordinate() {
    }

    @Override
    public double getCartesianDistance(Coordinate other) {
        return Double.NaN;
    }

    @Override
    public double getSphericDistance(Coordinate other) {
        return Double.NaN;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public double getDistance(Coordinate other) {
        return Double.NaN;
    }

    public boolean isEqual(Coordinate other) {
        return false;
    }

    public String toString() {
        return "NoWhereCoordinate in NoWhere";
    }
}
