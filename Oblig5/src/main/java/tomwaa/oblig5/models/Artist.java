package tomwaa.oblig5.models;

import java.util.UUID;

public abstract class Artist implements Comparable<Artist>
{
    private UUID id;
    private String name;

    public Artist() {};
    public Artist(String name)
    {
        id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Artist o) {
        return this.name.compareTo(o.getName());
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
}
