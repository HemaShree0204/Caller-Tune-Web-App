package CRBT.Tunes_Service.DTO;

public class TuneDTO {
    private Long song_id;       // auto-generated
    private String title;
    private String artist;
    private boolean active;
    private String language;
    private String genre;
    private String filename;    // store MP3 filename
    private String previewUrl;
    private double price;

    // No-args constructor
    public TuneDTO() {}

    // Constructor without song_id (for creation)
    public TuneDTO(String title, String artist, boolean active,
                   String language, String genre, String filename, String previewUrl, double price) {
        this.title = title;
        this.artist = artist;
        this.active = active;
        this.language = language;
        this.genre = genre;
        this.filename = filename;
        this.previewUrl = previewUrl;
        this.price = price;
    }

    // Full constructor (including auto-generated song_id)
    public TuneDTO(Long song_id, String title, String artist, boolean active,
                   String language, String genre, String filename, String previewUrl, double price) {
        this.song_id = song_id;
        this.title = title;
        this.artist = artist;
        this.active = active;
        this.language = language;
        this.genre = genre;
        this.filename = filename;
        this.previewUrl = previewUrl;
        this.price = price;
    }

    // Getters & Setters
    public Long getSong_id() { return song_id; }
    public void setSong_id(Long song_id) { this.song_id = song_id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getPreviewUrl() { return previewUrl; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
