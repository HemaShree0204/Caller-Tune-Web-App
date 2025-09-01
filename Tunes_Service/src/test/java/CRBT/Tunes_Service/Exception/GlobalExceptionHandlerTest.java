package CRBT.Tunes_Service.Exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleTuneNotFound() {
        TuneNotFoundException ex = new TuneNotFoundException("Not found");
        ResponseEntity<String> response = handler.handleTuneNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Not found", response.getBody());
    }
}
