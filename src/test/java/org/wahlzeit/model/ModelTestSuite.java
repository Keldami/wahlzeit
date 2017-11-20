package org.wahlzeit.model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.PersistenceTestSuite;

@RunWith(Suite.class)

@Suite.SuiteClasses({

    //persistence
        PersistenceTestSuite.class,

    //other model tests
        AccessRightsTest.class,
        CartesianCoordinateTest.class,
        SphericCoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        LeninPhotoFactoryTest.class,
        LeninPhotoManagerTest.class,

})
public class ModelTestSuite {
}
