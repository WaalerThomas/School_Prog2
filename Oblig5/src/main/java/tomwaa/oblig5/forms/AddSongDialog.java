package tomwaa.oblig5.forms;

import tomwaa.oblig5.models.Song;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddSongDialog extends CustomDialog<Song>
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel statusLbl;
    private JTextField nameTextField;
    private JSpinner ratingSpinner;
    private JSpinner durationSpinner;

    public AddSongDialog()
    {
        this.setTitle("Add Song");
        this.setContentPane(contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(buttonOK);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();

        statusLbl.setVisible(false);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

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
        // Check if all the information given is correct
        if (nameTextField.getText().isBlank()) { writeStatus("Name field is blank"); return; }

        int duration = (int)durationSpinner.getValue();
        if (duration <= 0) {
            writeStatus("Duration cannot be negative");
            return;
        }

        float rating = Float.parseFloat(ratingSpinner.getValue().toString());
        if (rating < 0 || rating > 5) {
            writeStatus("Rating needs to from 0 to 5 (Including)");
            return;
        }

        result = new Song(nameTextField.getText(), duration, new ArrayList<>(), 0, rating);
        dispose();
    }

    private void onCancel()
    {
        result = null;
        dispose();
    }
}
