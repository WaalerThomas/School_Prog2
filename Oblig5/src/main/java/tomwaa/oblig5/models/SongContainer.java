package tomwaa.oblig5.models;

import java.util.ArrayList;
import java.util.UUID;

public abstract class SongContainer
{
    private UUID id;
    private String name;
    private ArrayList<Song> songList;

    public SongContainer() {}
    public SongContainer(String name, ArrayList<Song> songList)
    {
        this.id = UUID.randomUUID();
        this.name = name;
        this.songList = songList;
    }
    public SongContainer(String name) {
        this(name, new ArrayList<>());
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

    public int totalSongCount() {
        return songList.size();
    }
    public int totalDuration() {
        // Returns the total duration in seconds
        int dur = 0;
        for (Song s : songList) {
            dur += s.getDuration();
        }

        return dur;
    }

    public Song getSong(UUID id) {
        for (Song s : songList) {
            if (s.getId().equals(id)) {
                return s;
            }
        }

        return null;
    }
    public ArrayList<Song> getSongList() {
        // So that I can list the songs in the GUI
        return songList;
    }
    public void addSong(Song song) {
        songList.add(song);
    }
    public void removeSong(UUID id) {
        songList.removeIf(s -> s.getId().equals(id));
    }
}
