package test.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * JUnit Test Suite used to run all JDBC unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSQLOperatorDAO.class, TestSQLProductBatchComponentDAO.class, TestSQLProductBatchDAO.class, TestSQLRaavareBatchDAO.class, TestSQLRaavareDAO.class, TestSQLReceptComponentDAO.class, TestSQLReceptDAO.class, TestSQLRoleDAO.class })
public class JDBCUnitTests {

}