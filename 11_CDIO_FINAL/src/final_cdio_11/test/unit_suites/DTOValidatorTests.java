package final_cdio_11.test.unit_suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import final_cdio_11.test.validator.TestOperatorValidator;
import final_cdio_11.test.validator.TestProductBatchValidator;
import final_cdio_11.test.validator.TestProduktBatchCompValidator;
import final_cdio_11.test.validator.TestRaavareBatchValidator;
import final_cdio_11.test.validator.TestRaavareValidator;
import final_cdio_11.test.validator.TestReceptCompValidator;
import final_cdio_11.test.validator.TestReceptValidator;
import final_cdio_11.test.validator.TestRoleValidator;

/*
 * JUnit Test Suite used to run all unit tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestOperatorValidator.class, TestProductBatchValidator.class, TestProduktBatchCompValidator.class, TestRaavareBatchValidator.class, TestRaavareValidator.class, TestReceptCompValidator.class, TestReceptValidator.class, TestRoleValidator.class })
public class DTOValidatorTests {

}