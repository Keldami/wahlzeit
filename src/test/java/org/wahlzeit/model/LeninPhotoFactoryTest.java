package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LeninPhotoFactoryTest {

    @Rule
    public TestRule rule = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    LeninPhotoFactory factory;
    LeninPhotoFactory test;
    LeninPhotoFactory test2;

    @Before
    public void setUp() {
        factory = new LeninPhotoFactory();
        test.initialize();
    }

    @Test
    public void getInstanceTest(){

        test2 = LeninPhotoFactory.getInstance();

        assertTrue(test2 instanceof LeninPhotoFactory);
        assertTrue(factory instanceof LeninPhotoFactory);
    }

    @Test(expected = IllegalStateException.class)
    public void setInstanceTest() {
            LeninPhotoFactory.setInstance(new LeninPhotoFactory());
    }

    @Test
    public void createPhotosTest() {
        factory = new LeninPhotoFactory();
        Photo photo = factory.createPhoto();
        LeninPhoto lenPhoto = factory.createPhoto();

        assertTrue(lenPhoto instanceof LeninPhoto);
        assertTrue(photo instanceof LeninPhoto);
    }

    @Test
    public void createPhotoAndGetLocationTest(){
        factory = new LeninPhotoFactory();
        LeninPhoto lenPhoto = factory.createPhoto();
        lenPhoto.setLocation(new Location());
        Location loc = new Location();
        //ofc location is different here, but coordinates are the same
        assertEquals(lenPhoto.getLocation().getCoordinate(), loc.getCoordinate());

    }
}
