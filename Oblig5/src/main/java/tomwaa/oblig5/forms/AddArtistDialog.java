package tomwaa.oblig5.forms;

import tomwaa.oblig5.FileHandler;
import tomwaa.oblig5.enums.GenderType;
import tomwaa.oblig5.models.Artist;
import tomwaa.oblig5.models.Group;
import tomwaa.oblig5.models.Person;

import javax.swing.*;
import java.awt.event.*;



public class AddArtistDialog extends CustomDialog<Artist>
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTxtField;
    private JRadioButton personRadioBtn;
    private JRadioButton groupRadioBtn;
    private JLabel statusLbl;
    private JSpinner formedSpinner;
    private JLabel formedLbl;
    private JComboBox<GenderType> genderComboBox;
    private JLabel genderLbl;

    public AddArtistDialog()
    {
        this.setTitle("Add Artist");
        this.setContentPane(contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(buttonOK);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();

        statusLbl.setVisible(false);
        setGroupElementsVisible(false);

        DefaultComboBoxModel<GenderType> comboBoxModel = new DefaultComboBoxModel<>(GenderType.values());
        genderComboBox.setModel(comboBoxModel);

        // call onCancel() when cross is clicked
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        personRadioBtn.addActionListener(e -> {
            setGroupElementsVisible(false);
            setPersonElementsVisible(true);
        });

        groupRadioBtn.addActionListener(e -> {
            setGroupElementsVisible(true);
            setPersonElementsVisible(false);
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setGroupElementsVisible(boolean flag)
    {
        formedLbl.setVisible(flag);
        formedSpinner.setVisible(flag);
    }

    private void setPersonElementsVisible(boolean flag)
    {
        genderLbl.setVisible(flag);
        genderComboBox.setVisible(flag);
    }

    private void writeStatus(String message)
    {
        statusLbl.setText(message);
        statusLbl.setVisible(true);
    }

    private void onOK()
    {
        // Check if all the information given is correct
        if (nameTxtField.getText().isBlank()) { writeStatus("Name field is blank"); return; }

        if (personRadioBtn.isSelected()) {
            result = new Person(nameTxtField.getText(), (GenderType)genderComboBox.getSelectedItem());
        } else if (groupRadioBtn.isSelected()) {
            // TODO: Write a better check if the year is valid
            int year = (int)formedSpinner.getValue();
            if (year <= 0) {
                writeStatus("Year Formed cannot be negative");
                return;
            }

            result = new Group(nameTxtField.getText(), year);
        }

        FileHandler.saveArtistsToFile(result);
        dispose();
    }

    private void onCancel() {
        result = null;
        dispose();
    }
}
