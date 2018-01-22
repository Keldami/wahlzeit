package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

import java.util.Iterator;
import java.util.Set;


/**
 * A Type of Lenin
 */
@DesignPattern(patternName = "Type Object Pattern", participants = "Lenin.class")
public class LeninType{

    private String name;
    public LeninManager manager = LeninManager.getInstance();
    private LeninType superType;
    protected Set<LeninType> subTypes = new java.util.HashSet<LeninType>();

    /**
     * Constructor of LeninType
     * @param name
     */
    public LeninType(String name){
        this.name = name;
    }

    public LeninType getSuperType() {
        return superType;
    }

    public void setSuperType(LeninType superType) {
        this.superType = superType;
    }

    public Iterator<LeninType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public Lenin createInstance() {
        return new Lenin(this);
    }

    public void addSubType(LeninType lt) {
        assert (lt != null) : "null as sub-type not allowed";
        lt.setSuperType(this);
        subTypes.add(lt);
    }

    public boolean hasInstance (Lenin lenin) {
        assert (lenin != null) : "hastInstance mustn't be null";
        if (lenin.getType() == this) {
            return true;
        }
        for (LeninType type : subTypes) {
            if (type.hasInstance(lenin)){
                return true;
            }
        }
        return false;
    }

    public boolean isSubtype() {
        if (superType == null) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
