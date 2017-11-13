package org.wahlzeit.model;

import java.util.logging.Logger;

public class LeninPhotoManager extends PhotoManager {

    protected static LeninPhotoManager instance = new LeninPhotoManager();

    private static final Logger log = Logger.getLogger(LeninPhotoManager.class.getName());


    public LeninPhotoManager() {
        super();
    }


   public static LeninPhotoManager getInstance() {
        return instance;
    }


}
