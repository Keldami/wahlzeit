package org.wahlzeit.model;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.model.LeninPhotoManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.*;

public class LeninPhotoManagerTest {


    @Rule
    public TestRule rule = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    LeninPhotoManager manager;

    @Before
    public void setUp() {
        manager = LeninPhotoManager.getInstance();
    }

    @Test
    public void initTest() {
        manager = LeninPhotoManager.getInstance();
        assertTrue(manager instanceof LeninPhotoManager);
    }
}
