package final_cdio_11.test.unit_suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import final_cdio_11.test.dao.view.TestSQLAdminForemanPBCDAO;
import final_cdio_11.test.dao.view.TestSQLAdminOperatorDAO;
import final_cdio_11.test.dao.view.TestSQLForemanOperatorDAO;
import final_cdio_11.test.dao.view.TestSQLOperatorRBDAO;
import final_cdio_11.test.dao.view.TestSQLOperatorRecipeDAO;
import final_cdio_11.test.dao.view.TestSQLPharmacistRecipeDAO;

/*
 * JUnit Test Suite used to run all View unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSQLAdminForemanPBCDAO.class, TestSQLAdminOperatorDAO.class, TestSQLForemanOperatorDAO.class, TestSQLOperatorRBDAO.class, TestSQLOperatorRecipeDAO.class, TestSQLPharmacistRecipeDAO.class })
public class JDBCViewTests {

}