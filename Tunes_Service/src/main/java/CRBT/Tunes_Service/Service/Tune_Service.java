package CRBT.Tunes_Service.Service;

import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Respository.Tune_Respository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Tune_Service {

    private final Tune_Respository repository;

    public Tune_Service(Tune_Respository repository) {
        this.repository = repository;
    }

    // Add a tune
    public Tunes addTune(Tunes tune) {
        return repository.save(tune);
    }
    // Get all tunes
    public List<Tunes> getAllTunes() {
        return repository.findAll();
    }

    // Get tune by title
    public Optional<Tunes> getTuneByTitle(String title) {
        return repository.findByTitle(title);
    }

    // Get tunes by genre
    public List<Tunes> getTunesByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    // Get tunes by language
    public List<Tunes> getTunesByLanguage(String language) {
        return repository.findByLanguage(language);
    }

    // Delete tune by ID
    public void deleteTune(Long id) {
        repository.deleteById(id);
    }
}
