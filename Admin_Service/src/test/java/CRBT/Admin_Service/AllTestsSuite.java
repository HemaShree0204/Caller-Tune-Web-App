package CRBT.Admin_Service;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Suite
@SelectPackages("CRBT.Admin_Service")
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AllTestsSuite {
    // Empty class, just a suite to run all tests
}
