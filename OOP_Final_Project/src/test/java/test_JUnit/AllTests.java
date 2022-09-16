package test_JUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.Controller;

@RunWith(Suite.class)
@SuiteClasses({ JUnitTest_InputValidation.class})
public class AllTests {

}
