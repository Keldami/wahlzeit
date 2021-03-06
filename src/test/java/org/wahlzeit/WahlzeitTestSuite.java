package org.wahlzeit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.HandlerTestSuite;
import org.wahlzeit.services.EmailServiceTestSuite;
import org.wahlzeit.model.ModelTestSuite;
import org.wahlzeit.utils.UtilsTestSuite;


/*
 * This is the overall wahlzeit TestSuite
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        EmailServiceTestSuite.class,
        HandlerTestSuite.class,
        ModelTestSuite.class,
        UtilsTestSuite.class

})
public class WahlzeitTestSuite {
}
