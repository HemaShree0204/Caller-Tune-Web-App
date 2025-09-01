package CRBT.Tunes_Service.Controller;

import CRBT.Tunes_Service.Service.Tune_Service;
import CRBT.Tunes_Service.DTO.TuneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TuneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Tune_Service tuneService;

    @InjectMocks
    private Tune_Controller tuneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tuneController).build();
        System.out.println("Initialized mocks for Tune_ControllerTest");
    }

    @Test
    void testGetAllTunes() throws Exception {
        System.out.println("Running testGetAllTunes...");

        // Mock service to return an empty list
        when(tuneService.getAllTunes()).thenReturn(Collections.emptyList());

        // Perform GET request
        mockMvc.perform(get("/tunes"))
               .andExpect(status().isOk());

        // Verify service was called
        verify(tuneService).getAllTunes();

        System.out.println("testGetAllTunes completed.");
    }
}
