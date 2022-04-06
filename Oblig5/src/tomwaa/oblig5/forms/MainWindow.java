package tomwaa.oblig5.forms;

import javax.swing.*;

public class MainWindow extends JFrame
{
    private JPanel mainPanel;

    public MainWindow(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
}
