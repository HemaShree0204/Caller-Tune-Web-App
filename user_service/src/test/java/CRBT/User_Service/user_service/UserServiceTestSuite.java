package CRBT.User_Service.user_service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


import org.junit.jupiter.api.BeforeAll;

import CRBT.User_Service.user_service.Controller.UserControllerTest;
import CRBT.User_Service.user_service.Service.UserServiceTest;
@Suite
@SelectClasses({
        UserControllerTest.class,
        UserServiceTest.class
})
public class UserServiceTestSuite {

    @BeforeAll
    static void init() {
        System.out.println("=== UserServiceTestSuite Started ===");
        System.out.flush();
        System.out.println("Running testRegisterUser...");
        System.out.flush();

    }
}

