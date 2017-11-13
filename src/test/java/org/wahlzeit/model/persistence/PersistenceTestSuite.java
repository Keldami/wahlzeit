package org.wahlzeit.model.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        //persistence
        //AbstractAdapterTest.class, --abstract class not to test directly
        DatastoreAdapterTest.class,
        GcsAdapterTest.class,



})
public class PersistenceTestSuite {
}