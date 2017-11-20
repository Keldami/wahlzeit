package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class LeninPhoto extends Photo {

    //TODO make LeninStatue-class for attributes

    public LeninPhoto() {
        super();
    }

    public LeninPhoto(PhotoId id) {
        super(id);
    }
}
