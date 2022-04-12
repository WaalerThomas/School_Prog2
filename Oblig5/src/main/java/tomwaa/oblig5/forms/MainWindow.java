package tomwaa.oblig5.forms;

import tomwaa.oblig5.FileHandler;
import tomwaa.oblig5.models.*;
import tomwaa.oblig5.renderers.AlbumRenderer;
import tomwaa.oblig5.renderers.ArtistRenderer;
import tomwaa.oblig5.renderers.SongRenderer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainWindow extends JFrame {
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

    // ===== Album Page =====
    // ======================
    private JButton addAlbumBtn;
    private JList<Album> list2;
    private JPanel selectedAlbumInfoPnl;
    private JButton editAlbumBtn;
    private JLabel albumNameLbl;
    private JLabel albumArtistLbl;
    private JLabel albumInfoLbl;
    private JLabel albumIdLbl;
    private JList<Song> selectedAlbumSongList;
    // ======================

    // List-Models
    private final DefaultListModel<Artist> artistListModel = new DefaultListModel<>();
    private final DefaultListModel<Album> albumListModel = new DefaultListModel<>();
    private final DefaultListModel<Song> albumSongListModel = new DefaultListModel<>();

    public MainWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(800, 600);

        // Make a menu-bar
        JMenuBar menuBar = new JMenuBar();
        JMenu addMenu = new JMenu("Add");
        JMenuItem addPlaylist = new JMenuItem("Add playlist...");
        addPlaylist.setEnabled(false);
        addMenu.add(addPlaylist);
        menuBar.add(addMenu);
        this.setJMenuBar(menuBar);

        initArtistPage();
        initAlbumPage();
    }


    private void initArtistPage()
    {
        selectedArtistInfoPnl.setVisible(false);

        // Assign the cell renderer for artist object then add a list model
        list1.setCellRenderer( new ArtistRenderer() );
        list1.setModel(artistListModel);

        // Create a temp list to hold the artists for then to sort it and add that to the GUI
        ArrayList<Artist> tempArtistList = FileHandler.loadArtistsFromFile();
        Collections.sort(tempArtistList);
        artistListModel.addAll( tempArtistList );

        addArtistBtn.addActionListener(e -> {
            AddArtistDialog addArtistDialog = new AddArtistDialog();
            Artist newArtist = addArtistDialog.showDialog();
            if (newArtist != null) {
                System.out.println("New artist has been added");
                artistListModel.addElement(newArtist);

                // Get all the elements in the listmodel, add the new artist. Lastly add the sorted list to the listmodel
                ArrayList<Artist> artistSortList = new ArrayList<>();
                for (int i = 0; i < artistListModel.size(); i++) {
                    artistSortList.add(artistListModel.get(i));
                }
                Collections.sort(artistSortList);
                artistListModel.removeAllElements();
                artistListModel.addAll(artistSortList);
            }
        });

        editSelectedArtistBtn.addActionListener(e -> {
            // TODO: Open artist edit dialog, delete button will be there
        });

        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())   // Wait to do stuff until it's finished validating value
            {
                selectedArtistInfoPnl.setVisible(true);
                Artist selectedArtist = list1.getSelectedValue();
                if (selectedArtist == null) { return; } // Selection has been removed

                artistNameLbl.setText( selectedArtist.getName() );
                artistIdLbl.setText( selectedArtist.getId().toString() );

                if (selectedArtist instanceof Person)
                {
                    artistTypeLbl.setText("Person");

                    artistYearFoundedLbl.setVisible(false);
                    artistYearFoundedValue.setVisible(false);
                    artistGenderLbl.setVisible(true);
                    artistGenderValue.setVisible(true);

                    artistGenderValue.setText( ((Person)selectedArtist).getGender().label );
                }
                else if (selectedArtist instanceof Group)
                {
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

    private void initAlbumPage()
    {
        selectedAlbumInfoPnl.setVisible(false);

        selectedAlbumSongList.setCellRenderer( new SongRenderer() );
        selectedAlbumSongList.setModel(albumSongListModel);

        list2.setCellRenderer( new AlbumRenderer() );
        list2.setModel(albumListModel);

        // Create a temp list to hold the artists for then to sort it and add that to the GUI
        ArrayList<Album> tempAlbumList = FileHandler.loadAlbumFromFile();
        Collections.sort(tempAlbumList);
        albumListModel.addAll( tempAlbumList );

        addAlbumBtn.addActionListener(e -> {
            AddAlbumDialog addAlbumDialog = new AddAlbumDialog();
            Album newAlbum = addAlbumDialog.showDialog();
            if (newAlbum != null) {
                System.out.println("New album has been added");
                albumListModel.addElement(newAlbum);

                // Get all the elements in the listmodel, add the new album. Lastly add the sorted list to the listmodel
                ArrayList<Album> albumSortList = new ArrayList<>();
                for (int i = 0; i < albumListModel.size(); i++) {
                    albumSortList.add(albumListModel.get(i));
                }
                Collections.sort(albumSortList);
                albumListModel.removeAllElements();
                albumListModel.addAll(albumSortList);
            }
        });

        editAlbumBtn.addActionListener(e -> {
            // TODO: Open Album edit dialog, edit dialog will have the delete button
        });

        list2.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
            {
                // Clear the songsList
                albumSongListModel.clear();

                selectedAlbumInfoPnl.setVisible(true);
                Album selectedAlbum = list2.getSelectedValue();
                if (selectedAlbum == null) { return; } // Selection has been removed

                albumNameLbl.setText( selectedAlbum.getName() );
                albumArtistLbl.setText( selectedAlbum.getArtist().getName() );
                albumIdLbl.setText( selectedAlbum.getId().toString() );

                // Convert from seconds to hours, minutes, seconds
                int totalDuration = selectedAlbum.totalDuration();
                int hours = totalDuration / 3600;
                int minutes = (totalDuration % 3600) / 60;
                int seconds = totalDuration % 60;
                albumInfoLbl.setText("Genre - " +
                        selectedAlbum.getReleaseYear() + " | " +
                        selectedAlbum.totalSongCount() + " songs | " +
                        hours + " hours, " + minutes + " minutes, " + seconds + " seconds"
                );

                albumSongListModel.addAll(selectedAlbum.getSongList());
            }
        });
    }
}
