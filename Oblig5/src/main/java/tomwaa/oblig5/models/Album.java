package tomwaa.oblig5.models;

import java.util.ArrayList;

public class Album extends SongContainer
{
    private int releaseYear;
    private Artist artist;      // Albums usually only have one artist
    private float rating;
    // TODO: Add genre

    public Album(String name, ArrayList<Song> songList, Artist artist, int releaseYear, float rating)
    {
        super(name, songList);
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }
    public Album(String name) {
        this(name, new ArrayList<>(), null, 0, 0.0f);
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public void addSong(Song song) {
        // When adding a song to an album, the song inherits the albums genre
        // TODO: Implement addSong
        super.addSong(song);
    }
}
