package CRBT.Tunes_Service.Controller;

import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Service.Tune_Service;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tunes")
public class Tune_Controller {

    private final Tune_Service service;

    public Tune_Controller(Tune_Service service) {
        this.service = service;
    }

    // ✅ Add a tune
    @PostMapping
    public Tunes addTune(@RequestBody Tunes tune) {
        return service.addTune(tune);
    }

    // ✅ Get all tunes
    @GetMapping
    public List<Tunes> getAllTunes() {
        return service.getAllTunes();
    }

    // ✅ Get tune by Title
    @GetMapping("/{title}")
    public Optional<Tunes> getTunesByTitle(@PathVariable String title) {
        return service.getTuneByTitle(title);
    }

    // ✅ Get tunes by Genre
    @GetMapping("/genre/{genre}")
    public List<Tunes> getTunesByGenre(@PathVariable String genre) {
        return service.getTunesByGenre(genre);
    }

    // ✅ Get tunes by Language
    @GetMapping("/language/{language}")
    public List<Tunes> getTunesByLanguage(@PathVariable String language) {
        return service.getTunesByLanguage(language);
    }

    // ✅ Delete tune by ID
    @DeleteMapping("/{id}")
    public String deleteTune(@PathVariable Long id) {
        service.deleteTune(id);
        return "Tune with ID " + id + " deleted successfully!";
    }
}
