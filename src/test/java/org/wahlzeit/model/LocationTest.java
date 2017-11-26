package org.wahlzeit.model;

import org.junit.*;

public class LocationTest {

    Location loc1 = new Location();

    @Test
    public void NullTest() {

        Location loc2;
        Location loc3;

        try {
            loc2 = null;
            loc2.getDistance(loc1.getCoordinate());
        } catch (NullPointerException e) {
        }

        try {
            loc3 = new Location(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    Location loc4;
    Location loc5;
    Location loc6;

    @Test
    public void initTest() {

        loc4 = new Location();
        loc5 = new Location(new CartesianCoordinate());
        loc6 = new Location(new CartesianCoordinate(1, 2, 3));
        System.out.println(loc5.getCoordinate());


    }

    @Test
    public void setCoordinateTest() {

        loc4 = new Location();
        loc5 = new Location();
        loc6 = new Location();

        try {
            loc4.setCoordinate(null);
        } catch (IllegalArgumentException e) {
        }

        loc5.setCoordinate(new CartesianCoordinate(1, 2, 3));
        assert(loc5.getCoordinate().asCartesianCoordinate().getX() == 1.0);
        assert(loc5.getCoordinate().asCartesianCoordinate().getY() == 2.0);
        assert(loc5.getCoordinate().asCartesianCoordinate().getZ() == 3.0);

        loc6.setCoordinate(new CartesianCoordinate());
        assert(loc6.getCoordinate().asCartesianCoordinate().getX() == 0);
        assert(loc6.getCoordinate().asCartesianCoordinate().getY() == 0);
        assert(loc6.getCoordinate().asCartesianCoordinate().getZ() == 0);
    }

}
