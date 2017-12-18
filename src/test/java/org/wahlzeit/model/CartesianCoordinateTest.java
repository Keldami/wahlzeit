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
    public void initTest() throws Exception{
        one = CartesianCoordinate.create(1.0, 1.0, 1.0);
        two = CartesianCoordinate.create(1.0, 1.0, 1.0);
        three = CartesianCoordinate.create(0,0,0);
        four = CartesianCoordinate.create(2.0, 3.0, -1.0);
        nowhere = new NoWhereCoordinate();

        assertTrue(one.isEqual(two));
        assertFalse(one.isEqual(four));
        assertTrue(three.getX() == 0.0);
    }


    @Test
    public void testExpectSameInstance() throws Exception{
        for (int i = 0; i < 100; i++) {
            double x = Math.random();
            double y = Math.random();
            double z = Math.random();

            CartesianCoordinate coord1 = CartesianCoordinate.create(x, y, z);
            CartesianCoordinate coord2 = CartesianCoordinate.create(x, y, z);
            Assert.assertTrue(coord1.equals(coord2));
        }
    }

    @Test
    public void testInvalidArgumentInitCoordinate() throws Exception {

        double inf = MAX + MAX;

        try {
            one = CartesianCoordinate.create(Double.NaN, 0, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = CartesianCoordinate.create(0, Double.NaN, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = CartesianCoordinate.create(0, 0, Double.NaN);
        } catch (IllegalArgumentException e) {
        }

        try {
            two = CartesianCoordinate.create(inf, 0, 0);
            assert(Double.isInfinite(two.getX()));
        } catch (IllegalArgumentException e) {
        }

        try {
            two = CartesianCoordinate.create(0, inf, 0);
            assert(Double.isInfinite(two.getY()));
        } catch (IllegalArgumentException e){
        }

        try {
            two = CartesianCoordinate.create(0, 0, inf);
            assert(Double.isInfinite(two.getZ()));
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void SphericCoordinateParamConversionTest() throws Exception{

        one = CartesianCoordinate.create(0.5, 0.5, 0.5);

        double longitude = one.asSphericCoordinate().getLongitude();
        double latitude = one.asSphericCoordinate().getLatitude();
        double radius = one.asSphericCoordinate().getRadius();

        SphericCoordinate spheric = SphericCoordinate.create(latitude, longitude, radius);
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
    public void getDistanceTest() throws Exception{

        CartesianCoordinate test = CartesianCoordinate.create ( MAX , MAX, MAX);
        CartesianCoordinate test2 = CartesianCoordinate.create(0,0,0);

        try {
            Double.isInfinite(test.getDistance(test2));
        } catch (IllegalStateException e) {
        }

        one = CartesianCoordinate.create(0,0,0);
        two = CartesianCoordinate.create(0,0,0);
        assert(one.getDistance(two) == two.getDistance(one) && one.getDistance(two) == 0.0);

        one = CartesianCoordinate.create(1, 2, 3);
        two = CartesianCoordinate.create(1, 2, 3);
        assert(one.getDistance(two) == two.getDistance(one));
    }

    @Test
    public void isEqualTest() throws Exception{

        one = CartesianCoordinate.create(0,0,0);
        two = CartesianCoordinate.create(0,0,0);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        one = CartesianCoordinate.create(3, 2, 1);
        two = CartesianCoordinate.create( 3, 2, 1);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        three = CartesianCoordinate.create(1, 2, 3);
        four = CartesianCoordinate.create(-1, -2, -3);
        assertFalse(three.isEqual(four));
        assertFalse(four.isEqual(three));
        assert(three.isEqual(three));
        assert(four.isEqual(four));
    }

    @Test
    public void equalsTest() throws Exception{
        one = CartesianCoordinate.create(0,0,0);
        two = CartesianCoordinate.create(0,0,0);


        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));;

        one = CartesianCoordinate.create(3, 2, 1);
        two = CartesianCoordinate.create( 3, 2, 1);

        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));

        three = CartesianCoordinate.create(1, 2, 3);
        four = CartesianCoordinate.create(-1, -2, -3);
        assertFalse(three.equals(four));
        assertFalse(four.equals(three));
        assert(three.equals(three));
        assert(four.equals(four));

        nowhere = new NoWhereCoordinate();

        assertFalse(one.equals(nowhere));
        assert(nowhere.equals(nowhere));

    }
    @Test
    public void squareTest() throws Exception{
        one = CartesianCoordinate.create(0,0,0);


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
    public void toStringTest() throws Exception {

        one = CartesianCoordinate.create(0,0,0);
        Assert.assertTrue(one.toString().compareTo("random text") < 0);
        Assert.assertTrue((one.toString()).compareTo("Coordinate: {x=0.0 y=0.0 z=0.0 }") == 0);
        Assert.assertFalse(one.toString().compareTo("random text") == 0);
        Assert.assertFalse(one.toString().compareTo("Coordinate: {x=0.0 y=0.0 z=0.0 }") < 0);

        two = CartesianCoordinate.create(1.0E-15, 1.0E-15,1.0E-15);
        Assert.assertTrue(two.toString().compareTo("random text") < 0);
        Assert.assertTrue((two.toString()).compareTo("Coordinate: {x=1.0E-15 y=1.0E-15 z=1.0E-15 }") == 0);
        Assert.assertFalse(two.toString().compareTo("random text") == 0);
        Assert.assertFalse(two.toString().compareTo("Coordinate: {x=1.0E-15 y=1.0E-15 z=1.0E-15 }") < 0);
    }
}
