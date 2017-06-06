package final_cdio_11.test.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IProductBatchCompValidator;
import final_cdio_11.java.data.validator.ProductBatchCompValidator;

public class TestProduktBatchCompValidator {
	
	private IProductBatchCompValidator productBatchValidator;

	@Before
	public void setUp() throws Exception {
		productBatchValidator = new ProductBatchCompValidator();
	}

	@After
	public void tearDown() throws Exception {
		productBatchValidator = null;
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
