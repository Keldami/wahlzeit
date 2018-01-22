package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LeninTypeTest {

    @Test
    public void createLeninTypeTest() {
        LeninType type = LeninManager.getInstance().createLeninType("newtype",null);

        LeninType type2 = LeninManager.getInstance().createLeninType("newtype",null);

        LeninType type3 = LeninManager.getInstance().createLeninType("newtype",null);

        LeninType lt = LeninManager.getLeninType("newtype");

       assert (lt == type);
       assert (type == type2);
       assert (type == type3);

    }

    @Test
    public void SubSuperTypeTest() {
        LeninType lt = LeninManager.getInstance().createLeninType("test", null);
        assertFalse(lt.isSubtype());

        LeninType lt2 = LeninManager.getInstance().createLeninType("test2", lt);
        assertTrue(lt2.isSubtype());

        LeninType lt3 = LeninManager.getInstance().createLeninType("test3", null);
        lt3.setSuperType(lt);
        assertTrue(lt3.isSubtype());

    }

    @Test
    public void createInstanceTest() {
        LeninType lt = LeninManager.getInstance().createLeninType("test", null);
        Lenin l = lt.createInstance();
        l.setBuildDate(1950);

        assert(l.getBuildDate() == 1950);
        assert(l.getType() == lt);

    }

}
