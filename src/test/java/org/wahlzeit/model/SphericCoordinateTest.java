package org.wahlzeit.model;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {

    SphericCoordinate one;
    SphericCoordinate two;
    SphericCoordinate three;
    SphericCoordinate four;
    NoWhereCoordinate nowhere;

    double MAX = Double.MAX_VALUE;
    double MIN = Double.MIN_VALUE;
    //if epsilon not high enough conversions get wrecked
    double EPSILON = 1E-10;


    @Before
    public void init() throws Exception{
        one = SphericCoordinate.create(45.0, 30.0, 1.5);
        two = SphericCoordinate.create(45.0, 30.0, 1.5);
        three = SphericCoordinate.create(0,0,0);
        four = SphericCoordinate.create(20.0, 20.0, 0.0);
        nowhere = new NoWhereCoordinate();

        assertTrue(one.isEqual(two));
        assertFalse(one.isEqual(four));
    }



    @Test
    public void testInvalidArgumentInitCoordinate() throws Exception{

        double inf = MAX + MAX;

        try {
            one = SphericCoordinate.create(Double.NaN, 0, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = SphericCoordinate.create(0, Double.NaN, 0);
        } catch (IllegalArgumentException e) {
        }

        try {
            one = SphericCoordinate.create(0, 0, Double.NaN);
        } catch (IllegalArgumentException e) {
        }

        try {
            two = SphericCoordinate.create(inf, 0, 0);
            assert(Double.isInfinite(two.getLatitude()));
        } catch (IllegalArgumentException e) {
        }

        try {
            two = SphericCoordinate.create(0, inf, 0);
            assert(Double.isInfinite(two.getLongitude()));
        } catch (IllegalArgumentException e){
        }

        try {
            two = SphericCoordinate.create(0, 0, inf);
            assert(Double.isInfinite(two.getRadius()));
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void CartesianCoordinateParamConversionTest() throws Exception{

        one = SphericCoordinate.create(45.0, 30, 1.5);

        double x = one.asCartesianCoordinate().getX();
        double y = one.asCartesianCoordinate().getY();
        double z = one.asCartesianCoordinate().getZ();

        CartesianCoordinate cart = CartesianCoordinate.create(x, y, z);
        double latitude = cart.asSphericCoordinate().getLatitude();
        double longitude = cart.asSphericCoordinate().getLongitude();
        double radius = cart.asSphericCoordinate().getRadius();

//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(z);
//
//        System.out.println(latitude);
//        System.out.println(longitude);
//        System.out.println(radius);


        assertTrue(Math.abs(45 - latitude) <= EPSILON);
        assertTrue(Math.abs(30.0 - longitude) <= EPSILON);
        assertTrue(Math.abs(1.5 - radius) <= EPSILON);
    }

    @Test
    public void getDistanceTest() throws Exception{

        SphericCoordinate test = SphericCoordinate.create ( 0 , 0, MAX);
        SphericCoordinate test2 = SphericCoordinate.create(0,0,0);

        try {
            Double.isInfinite(test.getDistance(test2));
        } catch (IllegalStateException e) {
        }

        one = SphericCoordinate.create(0,0,0);
        two = SphericCoordinate.create(0,0,0);
        assert(one.getDistance(two) == two.getDistance(one) && one.getDistance(two) == 0.0);

        one = SphericCoordinate.create(1, 2, 3);
        two = SphericCoordinate.create(1, 2, 3);
        assert(one.getDistance(two) == two.getDistance(one));

        one = SphericCoordinate.create(45.0, 30.0, 1.5);
        two = SphericCoordinate.create(45.0, 30.0, 1.5);
        three = SphericCoordinate.create(0,0,0);
        four = SphericCoordinate.create(20.0, 20.0, 0.0);


        //TODO further enhance getDistanceTest
        assertFalse(one.asCartesianCoordinate().getCartesianDistance(four) ==
                    one.asSphericCoordinate().getSphericDistance(four));

    }

    @Test
    public void isEqualTest() throws Exception{

        one = SphericCoordinate.create(0,0,0);
        two = SphericCoordinate.create(0,0,0);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        one = SphericCoordinate.create(3, 2, 1);
        two = SphericCoordinate.create( 3, 2, 1);
        assert(one.isEqual(two));
        assert(two.isEqual(one));
        assert(one.isEqual(one));
        assert(two.isEqual(two));

        three = SphericCoordinate.create(1, 2, 3);
        four = SphericCoordinate.create(0, 0, 0);
        assertFalse(three.isEqual(four));
        assertFalse(four.isEqual(three));
        assert(three.isEqual(three));
        assert(four.isEqual(four));
    }

    @Test
    public void equalsTest() throws Exception{
        one = SphericCoordinate.create(0,0,0);
        two = SphericCoordinate.create(0,0,0);


        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));;

        one = SphericCoordinate.create(3, 2, 1);
        two = SphericCoordinate.create( 3, 2, 1);

        assert(one.equals(two));
        assert(two.equals(one));
        assert(one.equals(one));
        assert(two.equals(two));

        three = SphericCoordinate.create(1, 2, 3);
        four = SphericCoordinate.create(0, 0, 0);
        assertFalse(three.equals(four));
        assertFalse(four.equals(three));
        assert(three.equals(three));
        assert(four.equals(four));

        nowhere = new NoWhereCoordinate();

        assertFalse(one.equals(nowhere));
        assert(nowhere.equals(nowhere));

    }


    @Test
    public void toStringTest() throws Exception{

        one = SphericCoordinate.create(0,0,0);
        Assert.assertTrue(one.toString().compareTo("random text") < 0);
        //System.out.println(one.toString());
        Assert.assertTrue((one.toString()).compareTo("SphericCoordinate: {latitude=0.0 longitude=0.0 radius=0.0 }") == 0);
        Assert.assertFalse(one.toString().compareTo("random text") == 0);
        Assert.assertFalse(one.toString().compareTo("SphericCoordinate: {latitude=0.0 longitude=0.0 radius=0.0 }") < 0);

        two = SphericCoordinate.create(1.0E-15, 1.0E-15,1.0E-15);
        Assert.assertTrue(two.toString().compareTo("random text") < 0);
        Assert.assertTrue((two.toString()).compareTo("SphericCoordinate: {latitude=1.0E-15 longitude=1.0E-15 radius=1.0E-15 }") == 0);
        Assert.assertFalse(two.toString().compareTo("random text") == 0);
        Assert.assertFalse(two.toString().compareTo("SphericCoordinate: {latitude=1.0E-15 longitude=1.0E-15 radius=1.0E-15 }") < 0);
    }

}
