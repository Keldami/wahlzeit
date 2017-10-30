package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    Coordinate one;
    Coordinate two;
    Coordinate three;
    Coordinate four;

    double MAX = Double.MAX_VALUE;
    double MIN = Double.MIN_VALUE;


    @Test
    public void initTest() {
        one = new Coordinate(1.0, 1.0, 1.0);
        two = new Coordinate(1.0, 1.0, 1.0);
        three = new Coordinate();
        four = new Coordinate(2.0, 3.0, -1.0);

        assertTrue(one.isEqual(two));
        assertFalse(one.isEqual(four));
        assertTrue(three.getX() == 0.0);
    }

    @Test
    public void testInvalidArgumentInitCoordinate() {

        double inf = MAX + MAX;

        try {
            one = new Coordinate(Double.NaN, 0, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = new Coordinate(0, Double.NaN, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = new Coordinate(0, 0, Double.NaN);
        } catch (IllegalArgumentException e) {
        }

        try {
            two = new Coordinate(inf, 0, 0);
            assert(Double.isInfinite(two.getX()));
        } catch (IllegalArgumentException e) {
        }

        try {
            two = new Coordinate(0, inf, 0);
            assert(Double.isInfinite(two.getY()));
        } catch (IllegalArgumentException e){
        }

        try {
            two = new Coordinate(0, 0, inf);
            assert(Double.isInfinite(two.getZ()));
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void setCoordinatesTest() {
        one = new Coordinate();
        one.setCoordinates(1,2, 3);
        two = new Coordinate(1, 2, 3);
        assert(one.isEqual(two) && two.isEqual(one));

        three = new Coordinate();
        try {
            three.setCoordinates(Double.NaN, 0, 0);
            three.setCoordinates(0, Double.NaN, 0);
            three.setCoordinates(0, 0, Double.NaN);

            three.setCoordinates(Double.POSITIVE_INFINITY, 0, 0);
            three.setCoordinates(0, Double.POSITIVE_INFINITY, 0);
            three.setCoordinates(0, 0, Double.POSITIVE_INFINITY);

            three.setCoordinates(Double.NEGATIVE_INFINITY, 0, 0);
            three.setCoordinates(0, Double.NEGATIVE_INFINITY, 0);
            three.setCoordinates(0, 0, Double.NEGATIVE_INFINITY);

        } catch (IllegalArgumentException e){
        }
    }

    @Test
    public void getDistanceTest() {

        Coordinate test = new Coordinate ( MAX , MAX, MAX);
        Coordinate test2 = new Coordinate();

        try {
            Double.isInfinite(test.getDistance(test2));
        } catch (IllegalStateException e) {
        }

        one = new Coordinate();
        two = new Coordinate();
        assert(one.getDistance(two) == two.getDistance(one) && one.getDistance(two) == 0.0);

        one = new Coordinate(1, 2, 3);
        two = new Coordinate(1, 2, 3);
        assert(one.getDistance(two) == two.getDistance(one));
    }

    @Test
    public void isEqualTest() {

        one = new Coordinate();
        two = new Coordinate();
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        one = new Coordinate(3, 2, 1);
        two = new Coordinate( 3, 2, 1);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        three = new Coordinate(1, 2, 3);
        four = new Coordinate(-1, -2, -3);
        assertFalse(three.isEqual(four));
        assertFalse(four.isEqual(three));
        assert(three.isEqual(three));
        assert(four.isEqual(four));
    }


    @Test
    public void squareTest() {
        one = new Coordinate();


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

        one = new Coordinate();
        Assert.assertTrue(one.toString().compareTo("random text") < 0);
        Assert.assertTrue((one.toString()).compareTo("Coordinate: x=0.0 y=0.0 z=0.0") == 0);
        Assert.assertFalse(one.toString().compareTo("random text") == 0);
        Assert.assertFalse(one.toString().compareTo("Coordinate: x=0.0 y=0.0 z=0.0") < 0);

        two = new Coordinate(1.0E-15, 1.0E-15,1.0E-15);
        Assert.assertTrue(two.toString().compareTo("random text") < 0);
        Assert.assertTrue((two.toString()).compareTo("Coordinate: x=1.0E-15 y=1.0E-15 z=1.0E-15") == 0);
        Assert.assertFalse(two.toString().compareTo("random text") == 0);
        Assert.assertFalse(two.toString().compareTo("Coordinate: x=1.0E-15 y=1.0E-15 z=1.0E-15") < 0);
    }
}
