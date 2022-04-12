package tomwaa.oblig5.forms;

import tomwaa.oblig5.FileHandler;
import tomwaa.oblig5.models.Album;
import tomwaa.oblig5.models.Artist;
import tomwaa.oblig5.models.Song;
import tomwaa.oblig5.renderers.SongRenderer;

import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;

// NOTE: Artist needs to exist already before making album, don't have enough time to
//       add the ability to make a new artist from this window

public class AddAlbumDialog extends CustomDialog<Album>
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel statusLbl;
    private JTextField nameTextField;
    private JSpinner releaseYearSpinner;
    private JComboBox<Artist> artistComboBox;
    private JSpinner ratingSpinner;
    private JList<Song> list1;
    private JButton addSongBtn;

    private DefaultListModel<Song> songListModel = new DefaultListModel<>();

    public AddAlbumDialog()
    {
        this.setTitle("Add Album");
        this.setContentPane(contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(buttonOK);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();

        statusLbl.setVisible(false);
        list1.setCellRenderer( new SongRenderer() );
        list1.setModel(songListModel);
        result = new Album("Temp"); // Needed for the song elements

        DefaultComboBoxModel<Artist> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll( FileHandler.loadArtistsFromFile() );
        artistComboBox.setModel(comboBoxModel);
        artistComboBox.setSelectedIndex(0);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        addSongBtn.addActionListener(e -> {
            AddSongDialog addSongDialog = new AddSongDialog();
            Song newSong = addSongDialog.showDialog();
            if (newSong != null) {
                System.out.println("New song has been added");
                newSong.setReleaseYear(result.getReleaseYear());
                songListModel.addElement(newSong);
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void writeStatus(String message)
    {
        statusLbl.setText(message);
        statusLbl.setVisible(true);
    }

    private void onOK()
    {
        if (nameTextField.getText().isBlank()) { writeStatus("Name field is blank"); return; }
        // TODO: Write a better check if the year is valid
        int year = (int)releaseYearSpinner.getValue();
        if (year <= 0) {
            writeStatus("Year released cannot be negative");
            return;
        }

        float rating = Float.parseFloat(ratingSpinner.getValue().toString());
        if (rating < 0 || rating > 5) {
            writeStatus("Rating needs to from 0 to 5 (Including)");
            return;
        }

        result.setName(nameTextField.getText());
        result.setArtist((Artist) artistComboBox.getSelectedItem());
        result.setReleaseYear(year);
        result.setRating(rating);
        for (Iterator<Song> it = songListModel.elements().asIterator(); it.hasNext(); ) {
            Song s = it.next();
            result.addSong( s );
        }

        FileHandler.saveAlbumToFile(result);
        dispose();
    }

    private void onCancel()
    {
        result = null;
        dispose();
    }
}
