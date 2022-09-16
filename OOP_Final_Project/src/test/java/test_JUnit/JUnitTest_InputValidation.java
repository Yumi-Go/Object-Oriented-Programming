package test_JUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest_InputValidation {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after class"); 
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("before test method"); 
	}

	@After
	public void tearDown() throws Exception {
		 System.out.println("after test method");
	}

	@Test
	public void testTab1_isInputValid() {
		assertTrue(application.InputValidation.isNumeric("a"));
	}

	@Test
	public void testTab2_isInputValid() {
		assertFalse(application.InputValidation.tab2_isGradeValid("35"));
	}

}
