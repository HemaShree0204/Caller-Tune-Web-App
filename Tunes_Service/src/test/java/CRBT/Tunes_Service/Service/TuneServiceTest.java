package CRBT.Tunes_Service.Service;

import CRBT.Tunes_Service.DTO.TuneDTO;
import CRBT.Tunes_Service.Exception.TuneNotFoundException;
import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Respository.Tune_Respository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TuneServiceTest {

    @InjectMocks
    private Tune_Service tuneService;

    @Mock
    private Tune_Respository tuneRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTune() {
        Tunes tune = new Tunes();
        tune.setTitle("Song1");
        tune.setArtist("Artist1");

        when(tuneRepository.save(tune)).thenReturn(tune);

        TuneDTO dto = tuneService.addTune(tune);
        assertEquals("Song1", dto.getTitle());
        verify(tuneRepository).save(tune);
    }

    @Test
    void testGetAllTunes() {
        Tunes t1 = new Tunes();
        t1.setTitle("Song1");
        Tunes t2 = new Tunes();
        t2.setTitle("Song2");

        when(tuneRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<TuneDTO> result = tuneService.getAllTunes();
        assertEquals(2, result.size());
    }

    @Test
    void testGetTuneByTitle_Success() {
        Tunes t = new Tunes();
        t.setTitle("Song1");

        when(tuneRepository.findByTitle("Song1")).thenReturn(Optional.of(t));

        TuneDTO dto = tuneService.getTuneByTitle("Song1");
        assertEquals("Song1", dto.getTitle());
    }

    @Test
    void testGetTuneByTitle_NotFound() {
        when(tuneRepository.findByTitle("SongX")).thenReturn(Optional.empty());

        assertThrows(TuneNotFoundException.class, () -> tuneService.getTuneByTitle("SongX"));
    }

    @Test
    void testDeleteTune_Success() {
        when(tuneRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tuneRepository).deleteById(1L);

        assertDoesNotThrow(() -> tuneService.deleteTune(1L));
        verify(tuneRepository).deleteById(1L);
    }

    @Test
    void testDeleteTune_NotFound() {
        when(tuneRepository.existsById(1L)).thenReturn(false);
        assertThrows(TuneNotFoundException.class, () -> tuneService.deleteTune(1L));
    }
}
