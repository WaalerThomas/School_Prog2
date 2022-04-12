package tomwaa.oblig5.renderers;

import tomwaa.oblig5.models.Album;
import tomwaa.oblig5.models.Artist;

import javax.swing.*;
import java.awt.*;

public class AlbumRenderer extends JPanel implements ListCellRenderer<Album>
{
    private final JLabel nameLbl;
    private final JLabel artistLbl;

    public AlbumRenderer() {
        this.setLayout( new GridLayout() );
        this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );

        Dimension elementSize = new Dimension(100, 28);
        this.setMinimumSize(elementSize);
        this.setPreferredSize(elementSize);

        nameLbl = new JLabel();
        artistLbl = new JLabel();
        this.add(nameLbl);
        this.add(artistLbl);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Album> list, Album value, int index, boolean isSelected, boolean cellHasFocus)
    {
        nameLbl.setText(value.getName());
        artistLbl.setText(value.getArtist().getName());

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
