package tomwaa.oblig5.forms;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tomwaa.oblig5.enums.GenderType;
import tomwaa.oblig5.models.Artist;
import tomwaa.oblig5.models.Group;
import tomwaa.oblig5.models.Person;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;

// TODO: Clamp formed spinner

public class AddArtistDialog extends JDialog
{
    private Artist result;

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

    public Artist showDialog()
    {
        this.setVisible(true);
        return result;
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

        // Save the artist to file
        File jsonFile = new File("datafiles\\artists.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Check if file exists already
            ArrayNode arrayNode;
            if (jsonFile.exists())  { arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
            } else                  { arrayNode = objectMapper.createArrayNode(); }

            ObjectNode artistNode = objectMapper.valueToTree(result);
            artistNode.put("type", (personRadioBtn.isSelected()) ? "Person" : "Group"); // Add artist type
            arrayNode.add(artistNode);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, arrayNode);
        } catch (IOException e) {
            writeStatus(e.getMessage());
            return;
        }
        dispose();
    }

    private void onCancel() {
        result = null;
        dispose();
    }
}
