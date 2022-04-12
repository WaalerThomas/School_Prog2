package tomwaa.oblig5.models;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Song
{
    private  UUID id;
    private String name;
    private int duration;   // In seconds
    private ArrayList<Artist> artistList; // Can be more than just one artist
    private int releaseYear;
    private float rating;
    // TODO: Add genre

    public Song() {}
    public Song(String name, int duration, ArrayList<Artist> artistList, int releaseYear, float rating)
    {
        id = UUID.randomUUID();
        this.name = name;
        this.duration = duration;
        this.artistList = artistList;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
    public Song(String name) {
        this(name, 0, new ArrayList<>(), 0, 0.0f);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Artist getArtist(UUID id) {
        for (Artist a : artistList) {
            // Why not just comparing the UUID objects instead of turning them both to strings?
            if (Objects.equals(id.toString(), a.getId().toString())) { return a; }
        }
        return null;
    }
    public void addArtist(Artist artist) {
        this.artistList.add(artist);
    }
    public void removeArtist(UUID id) {
        artistList.removeIf(s -> s.getId().equals(id));
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
}
