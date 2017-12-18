package org.wahlzeit.utils;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateUtilTest {

    @Test
    public void assertCartesian() throws Exception{
        try {
            CoordinateUtil.assertCartesianParameter(Double.NaN, "NaN");
            Assert.fail("this is NaN");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertCartesianParameter(Double.POSITIVE_INFINITY, "POSITIVE_INFINITY");
            Assert.fail("this is POSITIVE_INFINITY");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertCartesianParameter(Double.NEGATIVE_INFINITY, "NEGATIVE_INFINITY");
            Assert.fail("this is NEGATIVE_INFINITY");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void assertSpheric() throws Exception{
        try {
            CoordinateUtil.assertSphericParameter(100, 50, 10, "too much");
            Assert.fail("this is too much");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertSphericParameter(0, 50, 10, "too low");
            Assert.fail("this is too low");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertSphericParameter(Double.NaN, 50, 10, "NaN");
            Assert.fail("this is NaN");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertSphericParameter(Double.POSITIVE_INFINITY, 50, 10, "POSITIVE_INFINITY");
            Assert.fail("this is POSITIVE_INFINITY");
        } catch (IllegalArgumentException e) {}
        try {
            CoordinateUtil.assertSphericParameter(Double.NEGATIVE_INFINITY, 50, 10, "NEGATIVE_INFINITY");
            Assert.fail("this is NEGATIVE_INFINITY");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void notNullTest() throws Exception{
        try {
            CoordinateUtil.isNotNull(null, "null");
            Assert.fail("This should fail");
        } catch (IllegalArgumentException e){}
    }

}
