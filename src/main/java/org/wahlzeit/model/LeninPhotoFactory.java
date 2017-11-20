package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

@Subclass
public class LeninPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(LeninPhotoFactory.class.getName());

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static LeninPhotoFactory instance = null;


    public LeninPhotoFactory() {
        super();
    }

    /**
     * Public singleton access method.
     */
    public static synchronized LeninPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic LeninPhotoFactory").toString());
            setInstance(new LeninPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(LeninPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize PhotoFactory twice");
        }

        instance = photoFactory;
    }

    /**
     * @methodtype factory
     */
    public LeninPhoto createPhoto() {
        return new LeninPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    public LeninPhoto createPhoto(PhotoId id) {

        return new LeninPhoto(id);

    }
}
