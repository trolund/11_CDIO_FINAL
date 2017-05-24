package final_cdio_11.test.unit_suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * JUnit Test Suite used to run all unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ JDBCTableTests.class, JDBCViewTests.class })
public class JDBCAllTests {

}