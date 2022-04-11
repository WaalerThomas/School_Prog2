package tomwaa.oblig5.renderers;

import tomwaa.oblig5.models.Artist;

import javax.swing.*;
import java.awt.*;

public class ArtistRenderer extends JPanel implements ListCellRenderer<Artist>
{
    private final JLabel nameLbl;

    public ArtistRenderer() {
        this.setLayout( new GridLayout() );
        this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );

        Dimension elementSize = new Dimension(100, 28);
        this.setMinimumSize(elementSize);
        this.setPreferredSize(elementSize);

        nameLbl = new JLabel();
        this.add(nameLbl);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Artist> list, Artist value, int index, boolean isSelected, boolean cellHasFocus)
    {
        nameLbl.setText(value.getName());

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
