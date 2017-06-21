package final_cdio_11.test.unit_suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import final_cdio_11.test.dao.TestSQLOperatorDAO;
import final_cdio_11.test.dao.TestSQLProductBatchComponentDAO;
import final_cdio_11.test.dao.TestSQLProductBatchDAO;
import final_cdio_11.test.dao.TestSQLRaavareBatchDAO;
import final_cdio_11.test.dao.TestSQLRaavareDAO;
import final_cdio_11.test.dao.TestSQLReceptComponentDAO;
import final_cdio_11.test.dao.TestSQLReceptDAO;
import final_cdio_11.test.dao.TestSQLRoleDAO;

/*
 * JUnit Test Suite used to run all Table unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSQLOperatorDAO.class, TestSQLProductBatchComponentDAO.class, TestSQLProductBatchDAO.class, TestSQLRaavareBatchDAO.class, TestSQLRaavareDAO.class, TestSQLReceptComponentDAO.class, TestSQLReceptDAO.class, TestSQLRoleDAO.class })
public class JDBCDAOTests {

}