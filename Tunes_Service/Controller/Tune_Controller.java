package CRBT.Tunes_Service.Controller;

import CRBT.Tunes_Service.DTO.TuneDTO;
import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Service.Tune_Service;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tunes")
public class Tune_Controller {

    private final Tune_Service service;

    public Tune_Controller(Tune_Service service) {
        this.service = service;
    }

    // Add tune with file
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TuneDTO> addTune(
            @RequestParam String title,
            @RequestParam String artist,
            @RequestParam String genre,
            @RequestParam String language,
            @RequestParam Double price,
            @RequestParam("file") MultipartFile file) {

        TuneDTO savedTune = service.addTuneWithFile(title, artist, genre, language, price, file);
        return ResponseEntity.ok(savedTune);
    }


    // Get all tunes
    @GetMapping
    public List<TuneDTO> getAllTunes() {
        return service.getAllTunes();
    }

    // Get tune by title
    @GetMapping("/title/{title}")
    public TuneDTO getTunesByTitle(@PathVariable String title) {
        return service.getTuneByTitle(title);
    }

    // Get tune by filename
    @GetMapping("/file/{fileName}")
    public TuneDTO getTuneByFileName(@PathVariable String fileName) {
        return service.getTuneByFileName(fileName);
    }

    // Update tune [Admin]
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TuneDTO updateTune(@PathVariable Long id, @RequestBody Tunes tune) {
        return service.updateTune(id, tune);
    }
 // Get tunes by language
    @GetMapping("/language/{language}")
    public List<TuneDTO> getTunesByLanguage(@PathVariable String language) {
        return service.getAllTunes().stream()
                .filter(t -> t.getLanguage().equalsIgnoreCase(language))
                .toList();
    }

    // Get tunes by genre
    @GetMapping("/genre/{genre}")
    public List<TuneDTO> getTunesByGenre(@PathVariable String genre) {
        return service.getAllTunes().stream()
                .filter(t -> t.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    // Delete tune
    @DeleteMapping("/{id}")
    public String deleteTune(@PathVariable Long id) {
        service.deleteTune(id);
        return "Tune with ID " + id + " deleted successfully!";
    }

    // Total tunes
    @GetMapping("/count")
    public long getTotalTunesCount() {
        return service.getTotalTunesCount();
    }

    // Stream/Play MP3
    @GetMapping("/play/{fileName}")
    public ResponseEntity<Resource> playSong(@PathVariable String fileName) {
        try {
            Resource file = service.getAudioFile(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // List all files
    @GetMapping("/all-files")
    public List<String> listAllFiles() {
        return service.listAllAudioFiles();
    }
}
