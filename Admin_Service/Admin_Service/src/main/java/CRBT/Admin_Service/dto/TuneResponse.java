package CRBT.Admin_Service.dto;

public class TuneResponse {
    private Long id;
    private String title;
    private String artist;
    private String category;

    public TuneResponse() {}

    public TuneResponse(Long id, String title, String artist, String category) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
