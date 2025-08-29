package CRBT.Tunes_Service.Service;

import CRBT.Tunes_Service.DTO.TuneDTO;
import CRBT.Tunes_Service.Model.Tunes;
import CRBT.Tunes_Service.Respository.Tune_Respository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Tune_Service {

    private final Tune_Respository repository;
    private final String AUDIO_FOLDER;

    public Tune_Service(Tune_Respository repository) {
        this.repository = repository;
        // Absolute path to uploaded-audio folder
        this.AUDIO_FOLDER = new File("uploaded-audio").getAbsolutePath() + File.separator;

        // Ensure folder exists
        File folder = new File(AUDIO_FOLDER);
        if (!folder.exists()) folder.mkdirs();
    }

    // Convert Tunes entity to DTO
    private TuneDTO mapToDTO(Tunes tune) {
        TuneDTO dto = new TuneDTO();
        dto.setSong_id(tune.getSong_id());
        dto.setTitle(tune.getTitle());
        dto.setArtist(tune.getArtist());
        dto.setGenre(tune.getGenre());
        dto.setLanguage(tune.getLanguage());
        dto.setPrice(tune.getPrice());
        dto.setFilename(tune.getFileName());
        dto.setPreviewUrl("/tunes/play/" + tune.getFileName());
        dto.setActive(true);
        return dto;
    }

    // Add tune with uploaded file
    public TuneDTO addTuneWithFile(String title, String artist, String genre, String language, Double price, MultipartFile file) {
        if (file.isEmpty()) throw new RuntimeException("No file uploaded");

        String originalFilename = file.getOriginalFilename();
        String filename = System.currentTimeMillis() + "_" + originalFilename.replaceAll("\\s+", "_");

        File dest = new File(AUDIO_FOLDER + filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        }

        Tunes tune = new Tunes();
        tune.setTitle(title);
        tune.setArtist(artist);
        tune.setGenre(genre);
        tune.setLanguage(language);
        tune.setPrice(price);
        tune.setFileName(filename);
        repository.save(tune);

        return mapToDTO(tune);
    }

    public List<TuneDTO> getAllTunes() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TuneDTO getTuneByTitle(String title) {
        Tunes tune = repository.findByTitleIgnoreCase(title)
                .orElseThrow(() -> new RuntimeException("Tune not found: " + title));
        return mapToDTO(tune);
    }

    public TuneDTO getTuneByFileName(String filename) {
        Tunes tune = repository.findByFileNameIgnoreCase(filename)
                .orElseThrow(() -> new RuntimeException("Tune not found: " + filename));
        return mapToDTO(tune);
    }
 // Get tunes by language (case-insensitive)
    public List<TuneDTO> getTunesByLanguage(String language) {
        return repository.findAll().stream()
                .filter(t -> t.getLanguage() != null && t.getLanguage().equalsIgnoreCase(language))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get tunes by genre (case-insensitive)
    public List<TuneDTO> getTunesByGenre(String genre) {
        return repository.findAll().stream()
                .filter(t -> t.getGenre() != null && t.getGenre().equalsIgnoreCase(genre))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TuneDTO updateTune(Long id, Tunes tuneDetails) {
        Tunes tune = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tune not found"));

        tune.setTitle(tuneDetails.getTitle());
        tune.setArtist(tuneDetails.getArtist());
        tune.setGenre(tuneDetails.getGenre());
        tune.setLanguage(tuneDetails.getLanguage());
        tune.setPrice(tuneDetails.getPrice());

        // Add this line to update filename
        tune.setFileName(tuneDetails.getFileName());

        repository.save(tune);
        return mapToDTO(tune);
    }


    public void deleteTune(Long id) {
        repository.deleteById(id);
    }

    public long getTotalTunesCount() {
        return repository.count();
    }

    public Resource getAudioFile(String filename) throws IOException {
        File file = new File(AUDIO_FOLDER + filename);
        if (!file.exists()) throw new IOException("File not found: " + filename);
        return new FileSystemResource(file);
    }

    public List<String> listAllAudioFiles() {
        File folder = new File(AUDIO_FOLDER);
        List<String> files = new ArrayList<>();
        for (File f : folder.listFiles()) {
            if (f.isFile() && f.getName().endsWith(".mp3")) {
                files.add(f.getName());
            }
        }
        return files;
    }
}
