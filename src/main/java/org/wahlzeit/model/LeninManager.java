package org.wahlzeit.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.CoordinateUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A manager of the Lenin Type
 */
public class LeninManager {

    private static final Map<String, LeninType> leninHM = new HashMap<>();

    private final static LeninManager instance = new LeninManager();

    private static final Logger log = Logger.getLogger(LeninManager.class.getName());


    /**
     * Creates new LeninTypes and saves them in a HashMap
     * else returns LeninType in HashMap
     * @param typeName
     * @param supertype
     * @return null if typeName.isEmpty ==true else returns unique LeninType
     */
    public LeninType createLeninType(String typeName, LeninType supertype){

        if(typeName.isEmpty()) {
           return null;
        }

        if (leninHM.containsKey(typeName)){
            return leninHM.get(typeName);
        }

        LeninType lt = new LeninType(typeName);
        lt.setSuperType(supertype);

        leninHM.putIfAbsent(typeName, lt);

        return lt;
    }

    public static LeninManager getInstance() {
        return instance;
    }

    public static LeninType getLeninType(String typeName) {
        return leninHM.get(typeName);
    }

}
