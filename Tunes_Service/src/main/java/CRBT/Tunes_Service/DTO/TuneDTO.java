package CRBT.Tunes_Service.DTO;

public class TuneDTO {
    private Long song_id;
    private String title;
    private String artist;
    private boolean active;
    private String language;
    private String genre;
    private String previewUrl;
    private double price;

    public TuneDTO(Long song_id, String title, String artist, boolean active,
                   String language, String genre, String previewUrl, double price) {
        this.song_id = song_id;    
        this.title = title;
        this.artist = artist;
        this.active = active;
        this.language = language;
        this.genre = genre;
        this.previewUrl = previewUrl;
        this.price = price;         
    }

    // Getters
    public Long getSong_id() { return song_id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public boolean isActive() { return active; }
    public String getLanguage() { return language; }
    public String getGenre() { return genre; }
    public String getPreviewUrl() { return previewUrl; }
    public double getPrice() { return price; }  
}
