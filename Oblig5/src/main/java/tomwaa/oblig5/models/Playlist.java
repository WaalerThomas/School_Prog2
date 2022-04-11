package tomwaa.oblig5.models;

import java.util.ArrayList;

public class Playlist extends SongContainer
{
    private String description;

    public Playlist(String name, ArrayList<Song> songList, String description)
    {
        super(name, songList);
        this.description = description;
    }
    public Playlist(String name) {
        this(name, new ArrayList<>(), "");
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
