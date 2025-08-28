package CRBT.Tunes_Service.Service;

import CRBT.Tunes_Service.DTO.TuneDTO;
import CRBT.Tunes_Service.Exception.TuneNotFoundException;
import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Respository.Tune_Respository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Tune_Service {

    private final Tune_Respository repository;

    public Tune_Service(Tune_Respository repository) {
        this.repository = repository;
    }

    // Add a tune
    public TuneDTO addTune(Tunes tune) {
        Tunes saved = repository.save(tune);
        return convertToDTO(saved);
    }

    // Get all tunes
    public List<TuneDTO> getAllTunes() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get tune by title
    public TuneDTO getTuneByTitle(String title) {
        Tunes tune = repository.findByTitle(title)
                .orElseThrow(() -> new TuneNotFoundException("Tune with title '" + title + "' not found"));
        return convertToDTO(tune);
    }

    // Get tunes by genre
    public List<TuneDTO> getTunesByGenre(String genre) {
        return repository.findByGenre(genre).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get tunes by language
    public List<TuneDTO> getTunesByLanguage(String language) {
        return repository.findByLanguage(language).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    //Get tunes by Price
    public List<TuneDTO> getTunesByPrice(double price)
    {
    	return repository.findByPrice(price).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    // Delete tune by ID
    public void deleteTune(Long id) {
        if (!repository.existsById(id)) {
            throw new TuneNotFoundException("Tune with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    // Update tune
    public TuneDTO updateTune(Long id, Tunes updatedTune) {
        Tunes tune = repository.findById(id)
                .orElseThrow(() -> new TuneNotFoundException("Tune with ID " + id + " not found"));

        tune.setTitle(updatedTune.getTitle());
        tune.setArtist(updatedTune.getArtist());
        tune.setActive(updatedTune.isActive());
        tune.setLanguage(updatedTune.getLanguage());
        tune.setGenre(updatedTune.getGenre());
        tune.setAudio_url(updatedTune.getAudio_url());
        tune.setPreview_url(updatedTune.getPreview_url());
        tune.setPrice(updatedTune.getPrice());

        return convertToDTO(repository.save(tune));
    }

    // Convert Entity → DTO
    private TuneDTO convertToDTO(Tunes tune) {
        return new TuneDTO(
                tune.getSong_id(),
                tune.getTitle(),
                tune.getArtist(),
                tune.isActive(),
                tune.getLanguage(),
                tune.getGenre(),
                tune.getPreview_url(),
                tune.getPrice()
              
        );
    }
}
