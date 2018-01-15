package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * A manager of the Lenin Type
 */
public class LeninManager extends ObjectManager {

    LinkedList<LeninType> leninList= new LinkedList<>();
    HashMap<String, Lenin> leninHM = new HashMap<>();
    public static LeninManager instance = new LeninManager();


    public LeninManager() {}

    public Lenin createLeninType(String typeName){
        assertIsValidLeninTypeName(typeName);

        LeninType lt = getLeninType(typeName);

        Lenin result = lt.createInstance();
        leninHM.put(result.getId(), result);
        return result;
    }

    public static LeninManager getInstance() {
        return instance;
    }

    private LeninType getLeninType(String typeName) {
        return leninHM.get(typeName).getType();
    }

    public void assertIsValidLeninTypeName(String typeName) {
        if (typeName.isEmpty()){
            try {
                throw new Exception("typeName can't be empty");
            } catch (Exception e){}
        }
    }
}
