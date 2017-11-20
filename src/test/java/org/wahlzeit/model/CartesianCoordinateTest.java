package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CartesianCoordinateTest {

    CartesianCoordinate one;
    CartesianCoordinate two;
    CartesianCoordinate three;
    CartesianCoordinate four;
    NoWhereCoordinate nowhere;

    double MAX = Double.MAX_VALUE;
    double MIN = Double.MIN_VALUE;
    //if epsilon not high enough conversions get wrecked
    double EPSILON = 1E-10;


    @Test
    public void initTest() {
        one = new CartesianCoordinate(1.0, 1.0, 1.0);
        two = new CartesianCoordinate(1.0, 1.0, 1.0);
        three = new CartesianCoordinate();
        four = new CartesianCoordinate(2.0, 3.0, -1.0);
        nowhere = new NoWhereCoordinate();

        assertTrue(one.isEqual(two));
        assertFalse(one.isEqual(four));
        assertTrue(three.getX() == 0.0);
    }


    @Test
    public void testInvalidArgumentInitCoordinate() {

        double inf = MAX + MAX;

        try {
            one = new CartesianCoordinate(Double.NaN, 0, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = new CartesianCoordinate(0, Double.NaN, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = new CartesianCoordinate(0, 0, Double.NaN);
        } catch (IllegalArgumentException e) {
        }

        try {
            two = new CartesianCoordinate(inf, 0, 0);
            assert(Double.isInfinite(two.getX()));
        } catch (IllegalArgumentException e) {
        }

        try {
            two = new CartesianCoordinate(0, inf, 0);
            assert(Double.isInfinite(two.getY()));
        } catch (IllegalArgumentException e){
        }

        try {
            two = new CartesianCoordinate(0, 0, inf);
            assert(Double.isInfinite(two.getZ()));
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setCartesianCoordinatesTest() {
        one = new CartesianCoordinate();
        one.setCartesianCoordinates(1,2, 3);
        two = new CartesianCoordinate(1, 2, 3);
        assert(one.isEqual(two) && two.isEqual(one));

        three = new CartesianCoordinate();
        try {
            three.setCartesianCoordinates(Double.NaN, 0, 0);
            three.setCartesianCoordinates(0, Double.NaN, 0);
            three.setCartesianCoordinates(0, 0, Double.NaN);

            three.setCartesianCoordinates(Double.POSITIVE_INFINITY, 0, 0);
            three.setCartesianCoordinates(0, Double.POSITIVE_INFINITY, 0);
            three.setCartesianCoordinates(0, 0, Double.POSITIVE_INFINITY);

            three.setCartesianCoordinates(Double.NEGATIVE_INFINITY, 0, 0);
            three.setCartesianCoordinates(0, Double.NEGATIVE_INFINITY, 0);
            three.setCartesianCoordinates(0, 0, Double.NEGATIVE_INFINITY);

        } catch (IllegalArgumentException e){
        }
    }

    @Test
    public void SphericCoordinateParamConversionTest(){

        one = new CartesianCoordinate(0.5, 0.5, 0.5);

        double longitude = one.asSphericCoordinate().getLongitude();
        double latitude = one.asSphericCoordinate().getLatitude();
        double radius = one.asSphericCoordinate().getRadius();

        SphericCoordinate spheric = new SphericCoordinate(latitude, longitude, radius);
        double x = spheric.asCartesianCoordinate().getX();
        double y = spheric.asCartesianCoordinate().getY();
        double z = spheric.asCartesianCoordinate().getZ();

//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(z);
//
//        System.out.println(latitude);
//        System.out.println(longitude);
//        System.out.println(radius);


        assertTrue(Math.abs(0.5 - x) < EPSILON);
        assertTrue(Math.abs(0.5 - y) < EPSILON);
        assertTrue(Math.abs(0.5 - z) < EPSILON);
    }

    @Test
    public void getDistanceTest() {

        CartesianCoordinate test = new CartesianCoordinate ( MAX , MAX, MAX);
        CartesianCoordinate test2 = new CartesianCoordinate();

        try {
            Double.isInfinite(test.getDistance(test2));
        } catch (IllegalStateException e) {
        }

        one = new CartesianCoordinate();
        two = new CartesianCoordinate();
        assert(one.getDistance(two) == two.getDistance(one) && one.getDistance(two) == 0.0);

        one = new CartesianCoordinate(1, 2, 3);
        two = new CartesianCoordinate(1, 2, 3);
        assert(one.getDistance(two) == two.getDistance(one));
    }

    @Test
    public void isEqualTest() {

        one = new CartesianCoordinate();
        two = new CartesianCoordinate();
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        one = new CartesianCoordinate(3, 2, 1);
        two = new CartesianCoordinate( 3, 2, 1);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        three = new CartesianCoordinate(1, 2, 3);
        four = new CartesianCoordinate(-1, -2, -3);
        assertFalse(three.isEqual(four));
        assertFalse(four.isEqual(three));
        assert(three.isEqual(three));
        assert(four.isEqual(four));
    }

    @Test
    public void equalsTest() {
        one = new CartesianCoordinate();
        two = new CartesianCoordinate();


        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));;

        one = new CartesianCoordinate(3, 2, 1);
        two = new CartesianCoordinate( 3, 2, 1);

        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));

        three = new CartesianCoordinate(1, 2, 3);
        four = new CartesianCoordinate(-1, -2, -3);
        assertFalse(three.equals(four));
        assertFalse(four.equals(three));
        assert(three.equals(three));
        assert(four.equals(four));

        nowhere = new NoWhereCoordinate();

        assertFalse(one.equals(nowhere));
        assert(nowhere.equals(nowhere));

    }
    @Test
    public void squareTest() {
        one = new CartesianCoordinate();


        assert(one.square(2) == 4);
        assert(one.square(-2) == 4);
        try{
            one.square(MAX);
        } catch (IllegalStateException e) {
        }
        try {
            one.square(MIN);
        } catch (IllegalStateException e){
        }
    }

    @Test
    public void toStringTest() {

        one = new CartesianCoordinate();
        Assert.assertTrue(one.toString().compareTo("random text") < 0);
        Assert.assertTrue((one.toString()).compareTo("Coordinate: {x=0.0 y=0.0 z=0.0 }") == 0);
        Assert.assertFalse(one.toString().compareTo("random text") == 0);
        Assert.assertFalse(one.toString().compareTo("Coordinate: {x=0.0 y=0.0 z=0.0 }") < 0);

        two = new CartesianCoordinate(1.0E-15, 1.0E-15,1.0E-15);
        Assert.assertTrue(two.toString().compareTo("random text") < 0);
        Assert.assertTrue((two.toString()).compareTo("Coordinate: {x=1.0E-15 y=1.0E-15 z=1.0E-15 }") == 0);
        Assert.assertFalse(two.toString().compareTo("random text") == 0);
        Assert.assertFalse(two.toString().compareTo("Coordinate: {x=1.0E-15 y=1.0E-15 z=1.0E-15 }") < 0);
    }
}
