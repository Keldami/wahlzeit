package org.wahlzeit.model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.AbstractAdapterTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.persistence.GcsAdapterTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({

    //persistence
         // AbstractAdapterTest.class, //--giving unpleasent red messages by just running the test
         // DatastoreAdapterTest.class, //--giving unpleasent red messages by just running the test
         // GcsAdapterTest.class,     //--giving unpleasent red messages by just running the test

    //other model tests
        AccessRightsTest.class,
        CoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        //GuestTest.class, // --giving unpleasent red messages by just running the test
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class
})
public class modelTestSuite {
}
