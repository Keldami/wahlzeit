package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

/**
 * A Lenin Type Object
 */
@DesignPattern(patternName = "Type Object Pattern", participants = "LeninType.class")
public class Lenin {

    private int buildDate;

    private final LeninType type;
    public LeninManager manager = LeninManager.getInstance();


    public Lenin(LeninType type) {
        this.type = type;
    }

    public LeninType getType() {
        return type;
    }

    public void setBuildDate(int buildDate) {
        this.buildDate = buildDate;
    }

    public int getBuildDate() {return buildDate;}

    /**
     * the name is the typename of a type
     * @return
     */
    public String getName() {
        return type.getName();
    }
}
