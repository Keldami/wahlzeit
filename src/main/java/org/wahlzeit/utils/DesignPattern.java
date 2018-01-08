package org.wahlzeit.utils;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

/**
 * DesignPattern-Annotation used to annotate Design Patterns in code
 * no fixed names or participants for standart design patterns
 */
@Retention(RetentionPolicy.SOURCE) //so that compiler will ignore this
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}) //just for annotations
public @interface DesignPattern {

    /**
     * name of the desing pattern
     */
    String patternName() default "";

    /**
     * participants of the design pattern
     */
    String[] participants() default {};

}


