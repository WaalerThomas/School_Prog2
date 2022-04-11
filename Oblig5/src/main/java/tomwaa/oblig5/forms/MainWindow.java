package tomwaa.oblig5.forms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tomwaa.oblig5.models.Artist;
import tomwaa.oblig5.models.Group;
import tomwaa.oblig5.models.Person;
import tomwaa.oblig5.renderers.ArtistRenderer;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainWindow extends JFrame
{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;

    // ==== Artist Page ====
    // =====================
    private JButton addArtistBtn;
    private JList<Artist> list1;
    private JLabel artistNameLbl;
    private JPanel selectedArtistInfoPnl;
    private JLabel artistTypeLbl;
    private JLabel artistIdLbl;
    private JButton editSelectedArtistBtn;
    private JLabel artistYearFoundedLbl;
    private JLabel artistYearFoundedValue;
    private JLabel artistGenderLbl;
    private JLabel artistGenderValue;
    // =====================

    private final DefaultListModel<Artist> listModel = new DefaultListModel<>();

    public MainWindow(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(800, 600);

        // TODO: Add ability to make playlists
        // Make a menu-bar
        JMenuBar menuBar = new JMenuBar();
        JMenu addMenu = new JMenu("Add");
        JMenuItem addPlaylist = new JMenuItem("Add playlist...");
        addMenu.add(addPlaylist);
        menuBar.add(addMenu);
        this.setJMenuBar(menuBar);

        initArtistPage();
    }

    private void initArtistPage()
    {
        selectedArtistInfoPnl.setVisible(false);

        list1.setCellRenderer( new ArtistRenderer() );
        list1.setModel(listModel);
        loadArtistsFromFile();

        addArtistBtn.addActionListener(e -> {
            AddArtistDialog addArtistDialog = new AddArtistDialog();
            Artist newArtist = addArtistDialog.showDialog();
            if (newArtist != null) {
                System.out.println("New artist has been added");
                listModel.addElement(newArtist);
            }
        });

        editSelectedArtistBtn.addActionListener(e -> {
            // TODO: Open artist edit dialog, delete button will be there
        });

        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Wait to do stuff until it's finished validating value
                selectedArtistInfoPnl.setVisible(true);
                Artist selectedArtist = list1.getSelectedValue();

                artistNameLbl.setText( selectedArtist.getName() );
                artistIdLbl.setText( selectedArtist.getId().toString() );

                if (selectedArtist instanceof Person) {
                    artistTypeLbl.setText("Person");

                    artistYearFoundedLbl.setVisible(false);
                    artistYearFoundedValue.setVisible(false);
                    artistGenderLbl.setVisible(true);
                    artistGenderValue.setVisible(true);

                    artistGenderValue.setText( ((Person)selectedArtist).getGender().label );
                }
                else if (selectedArtist instanceof Group) {
                    artistTypeLbl.setText("Group");

                    artistYearFoundedLbl.setVisible(true);
                    artistYearFoundedValue.setVisible(true);
                    artistGenderLbl.setVisible(false);
                    artistGenderValue.setVisible(false);

                    artistYearFoundedValue.setText( Integer.toString(((Group) selectedArtist).getYearFounded()) );
                }
            }
        });
    }

    private void loadArtistsFromFile()
    {
        // Finds every artist from the datafile and makes them into the correct type of artist class,
        // then adds those to the listModel

        File jsonFile = new File("datafiles\\artists.json");
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Artist> artistList = new ArrayList<>();

        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
            for (JsonNode jsonNode : arrayNode) {
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

        listModel.addAll(artistList);
    }
}
