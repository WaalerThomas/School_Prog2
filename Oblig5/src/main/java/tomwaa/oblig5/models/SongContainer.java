package tomwaa.oblig5.models;

import java.util.ArrayList;
import java.util.UUID;

public abstract class SongContainer
{
    private final UUID id;
    private String name;
    private final ArrayList<Song> songList;

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

    public int getSongCount() {
        return songList.size();
    }
    public int getDuration() {
        // Returns the total duration in seconds
        int duration = 0;
        for (Song s : songList) {
            duration += s.getDuration();
        }

        return duration;
    }

    public Song getSong(UUID id) {
        for (Song s : songList) {
            if (s.getId().equals(id)) {
                return s;
            }
        }

        return null;
    }
    public void addSong(Song song) {
        songList.add(song);
    }
    public void removeSong(UUID id) {
        songList.removeIf(s -> s.getId().equals(id));
    }
}
