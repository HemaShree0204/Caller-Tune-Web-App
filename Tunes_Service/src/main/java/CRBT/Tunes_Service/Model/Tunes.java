package CRBT.Tunes_Service.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "tunes")
public class Tunes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long song_id;

    private String title;
    private String artist;
    private boolean active; // if currently active as caller tune
    private String language;
    private String genre;
    private String audio_url;
    private String preview_url;
    private double price;

    // Constructors
    public Tunes() {}

    public Tunes(Long song_id, String title, String artist, boolean active, String language, String genre,
                 String audio_url, String preview_url) {
        this.song_id = song_id;
        this.title = title;
        this.artist = artist;
        this.active = active;
        this.language = language;
        this.genre = genre;
        this.audio_url = audio_url;
        this.preview_url = preview_url;
        this.price= price;
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

    public String getAudio_url() { return audio_url; }
    public void setAudio_url(String audio_url) { this.audio_url = audio_url; }

    public String getPreview_url() { return preview_url; }
    public void setPreview_url(String preview_url) { this.preview_url = preview_url; }

	public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}

