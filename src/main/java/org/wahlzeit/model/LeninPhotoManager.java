package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

import java.util.logging.Logger;

//@Subclass
public class LeninPhotoManager extends PhotoManager {

    protected static LeninPhotoManager instance = new LeninPhotoManager();

    private static final Logger log = Logger.getLogger(LeninPhotoManager.class.getName());


    public static LeninPhotoManager getInstance() {
        return instance;
    }


}
