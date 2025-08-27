package CRBT.Tunes_Service.Controller;

import CRBT.Tunes_Service.DTO.TuneDTO;
import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Service.Tune_Service;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tunes")
public class Tune_Controller {

    private final Tune_Service service;

    public Tune_Controller(Tune_Service service) {
        this.service = service;
    }

    // Add a tune
    @PostMapping
    public TuneDTO addTune(@RequestBody Tunes tune) {
        return service.addTune(tune);
    }

    // Get all tunes
    @GetMapping
    public List<TuneDTO> getAllTunes() {
        return service.getAllTunes();
    }

    // Get tune by Title
    @GetMapping("/title/{title}")
    public TuneDTO getTunesByTitle(@PathVariable String title) {
        return service.getTuneByTitle(title);
    }

    // Get tunes by Genre
    @GetMapping("/genre/{genre}")
    public List<TuneDTO> getTunesByGenre(@PathVariable String genre) {
        return service.getTunesByGenre(genre);
    }

    // Get tunes by Language
    @GetMapping("/language/{language}")
    public List<TuneDTO> getTunesByLanguage(@PathVariable String language) {
        return service.getTunesByLanguage(language);
    }

    // Update tune
    @PutMapping("/{id}")
    public TuneDTO updateTune(@PathVariable Long id, @RequestBody Tunes tune) {
        return service.updateTune(id, tune);
    }

    // Delete tune by ID
    @DeleteMapping("/{id}")
    public String deleteTune(@PathVariable Long id) {
        service.deleteTune(id);
        return "Tune with ID " + id + " deleted successfully!";
    }
}
