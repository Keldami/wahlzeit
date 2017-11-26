package org.wahlzeit.model;



import com.googlecode.objectify.ObjectifyService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.model.LeninPhotoManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.io.IOException;

import static org.junit.Assert.*;

public class LeninPhotoManagerTest {


    @Rule
    public TestRule rule = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    LeninPhotoManager manager;
    PhotoManager normalManager;
    LeninPhotoFactory factory;
    PhotoFactory normalFactory;

    @Before
    public void setUp() {
        manager = LeninPhotoManager.getInstance();
        normalManager = new PhotoManager().getInstance();
        factory = new LeninPhotoFactory();
        normalFactory = new PhotoFactory();


    }

    @Test
    public void initTest() {
        manager = LeninPhotoManager.getInstance();
        assertTrue(manager instanceof LeninPhotoManager);
    }

    @Test
    public void managePhotosTest() throws IOException{

        PhotoId id = PhotoId.getNextId();
        LeninPhoto lPhoto = factory.createPhoto(id);

        System.out.println(manager.photoCache);

        manager.addPhoto(lPhoto);
        manager.loadPhotos();

        System.out.println("PhotoCache " + manager.photoCache);
        System.out.println("Photo by id: " + manager.getPhoto(id));
        System.out.println("PhotoId: " + id);
        System.out.println("PhotoId at photo: " + lPhoto.getId());
        System.out.println("PhotoCache2 " + manager.photoCache.get(id));

    }
}
