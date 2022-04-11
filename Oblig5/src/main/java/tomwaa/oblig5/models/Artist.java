package tomwaa.oblig5.models;

import java.util.UUID;

public abstract class Artist
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
        return name + ", " + id;
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
