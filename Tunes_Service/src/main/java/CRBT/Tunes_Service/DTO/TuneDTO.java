package CRBT.Tunes_Service.DTO;

public class TuneDTO {
	private Long id;
    private String title;
    private String artist;
    private boolean active;
    private String language;
    private String genre;
    private String previewUrl;

    public TuneDTO(Long id, String title, String artist, boolean active,
                   String language, String genre, String previewUrl) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.active = active;
        this.language = language;
        this.genre = genre;
        this.previewUrl = previewUrl;
    }

    // Getters only (no setters for immutability)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public boolean isActive() { return active; }
    public String getLanguage() { return language; }
    public String getGenre() { return genre; }
    public String getPreviewUrl() { return previewUrl; }

}
