package tomwaa.oblig5.renderers;

import tomwaa.oblig5.models.Song;

import javax.swing.*;
import java.awt.*;

public class SongRenderer extends JPanel implements ListCellRenderer<Song>
{
    private final JLabel nameLbl;
    private final JLabel durationLbl;

    public SongRenderer()
    {
        this.setLayout( new GridLayout() );
        this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );

        Dimension elementSize = new Dimension(100, 28);
        this.setMinimumSize(elementSize);
        this.setPreferredSize(elementSize);

        nameLbl = new JLabel();
        durationLbl = new JLabel();
        durationLbl.setHorizontalAlignment(JLabel.RIGHT);

        this.add(nameLbl);
        this.add(durationLbl);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Song> list, Song value, int index, boolean isSelected, boolean cellHasFocus)
    {
        int hours = value.getDuration() / 3600;
        int minutes = (value.getDuration() % 3600) / 60;
        int seconds = value.getDuration() % 60;

        nameLbl.setText(value.getName());
        durationLbl.setText(hours + ":" + minutes + ":" + seconds);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
