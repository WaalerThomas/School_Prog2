package tomwaa.oblig5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tomwaa.oblig5.enums.GenderType;
import tomwaa.oblig5.models.Album;
import tomwaa.oblig5.models.Artist;
import tomwaa.oblig5.models.Group;
import tomwaa.oblig5.models.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class FileHandler
{
    private static final String fileArtistPath = "datafiles\\artists.json";
    private static final String fileAlbumPath = "datafiles\\albums.json";

    public static void saveArtistsToFile(Artist artist)
    {
        File jsonFile = new File(fileArtistPath);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Check if file exists already
            ArrayNode arrayNode;
            if (jsonFile.exists())  { arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
            } else                  { arrayNode = objectMapper.createArrayNode(); }

            ObjectNode artistNode = objectMapper.valueToTree(artist);
            artistNode.put("type", (artist instanceof Person) ? "Person" : "Group"); // Add artist type
            arrayNode.add(artistNode);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, arrayNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Artist> loadArtistsFromFile()
    {
        File jsonFile = new File(fileArtistPath);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Artist> artistList = new ArrayList<>();

        if (jsonFile.exists()) {
            try {
                ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
                for (JsonNode jsonNode : arrayNode)
                {
                    ObjectNode objectNode = (ObjectNode) objectMapper.readTree(jsonNode.toString());

                    String typeStr = objectNode.get("type").asText();
                    objectNode.remove("type");
                    if (Objects.equals(typeStr, "Person")) {
                        artistList.add( objectMapper.readValue(objectNode.toString(), Person.class) );
                    } else if (Objects.equals(typeStr, "Group")) {
                        artistList.add( objectMapper.readValue(objectNode.toString(), Group.class) );
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return artistList;
    }

    public static void saveAlbumToFile(Album album)
    {
        File jsonFile = new File(fileAlbumPath);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ArrayNode arrayNode;
            if (jsonFile.exists())  { arrayNode = (ArrayNode) objectMapper.readTree(jsonFile); }
            else                    { arrayNode = objectMapper.createArrayNode(); }

            ObjectNode albumNode = objectMapper.valueToTree(album);

            // Change out the artist object with just the artist_id
            JsonNode artistNode = albumNode.findValue("artist");
            albumNode.remove("artist");
            albumNode.put("artist_id", artistNode.findValue("id").asText());

            arrayNode.add(albumNode);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, arrayNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Album> loadAlbumFromFile()
    {
        File jsonFile = new File(fileAlbumPath);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Album> albumList = new ArrayList<>();

        if (jsonFile.exists()) {
            try {
                ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
                for (JsonNode jsonNode : arrayNode) {
                    ObjectNode objectNode = (ObjectNode) objectMapper.readTree(jsonNode.toString());

                    // Gets the artist_id for later and removes it from the objectnode
                    String artistId = objectNode.get("artist_id").asText();
                    objectNode.remove("artist_id");

                    // Find the artist from the ID
                    ObjectNode artistNode = objectMapper.createObjectNode();
                    Artist albumArtist = new Person("No Artist Object", GenderType.MALE);
                    for (Artist a : loadArtistsFromFile()) {
                        if (Objects.equals(a.getId().toString(), artistId)) {
                            albumArtist = a;
                            break;
                        }
                    }

                    // Creates a temp album object and adds the artist object to it
                    Album tempAlbum = objectMapper.readValue(objectNode.toString(), Album.class);
                    tempAlbum.setArtist(albumArtist);
                    albumList.add(tempAlbum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return albumList;
    }
}
