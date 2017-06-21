package final_cdio_11.test.unit_suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import final_cdio_11.test.dao.view.TestSQLUserTableDAO;

/*
 * JUnit Test Suite used to run all View unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSQLUserTableDAO.class })
public class JDBCViewTests {

}