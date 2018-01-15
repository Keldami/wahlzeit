package org.wahlzeit.model;

import org.wahlzeit.utils.DesignPattern;

/**
 * A Lenin Type Object
 */
@DesignPattern(patternName = "Type Object Pattern", participants = "LeninType.class")
public class Lenin {


    protected LeninType type = null;
    public LeninManager manager = LeninManager.getInstance();

    public Lenin(LeninType type) {
        this.type = type;
    }

    public LeninType getType() {
        return type;
    }

    /**
     * the id is the typename of a type
     * @return
     */
    public String getId() {
        return type.getName();
    }
}
