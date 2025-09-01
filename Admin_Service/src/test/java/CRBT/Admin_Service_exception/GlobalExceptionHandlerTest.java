package CRBT.Admin_Service_exception;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import CRBT.Admin_Service.exception.AdminNotFoundException;
import CRBT.Admin_Service.exception.GlobalExceptionHandler;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleAdminNotFound() {
        AdminNotFoundException ex = new AdminNotFoundException("Admin not found");
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleAdminNotFound(ex);

        assertEquals(404, response.getBody().get("status"));
        assertEquals("Admin not found", response.getBody().get("message"));
    }

    @Test
    void testHandleGeneralException() {
        Exception ex = new Exception("Some error");
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleGeneralException(ex);

        assertEquals(500, response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("message"));
    }
}
