package org.wahlzeit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.handlerTestSuite;
import org.wahlzeit.services.EmailServiceTestSuite;
import org.wahlzeit.model.modelTestSuite;
import org.wahlzeit.utils.utilsTestSuite;


/*
 * This is the overall wahlzeit TestSuite
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        EmailServiceTestSuite.class,
        // handlerTestSuite.class, //--offlimit for now
        modelTestSuite.class,
        utilsTestSuite.class

})
public class wahlzeitTestSuite {
}
