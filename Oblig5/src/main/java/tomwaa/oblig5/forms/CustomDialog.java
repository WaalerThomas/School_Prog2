package tomwaa.oblig5.forms;

import javax.swing.*;

public class CustomDialog<T> extends JDialog
{
    protected T result;

    public T showDialog()
    {
        this.setVisible(true);
        return result;
    }
}
