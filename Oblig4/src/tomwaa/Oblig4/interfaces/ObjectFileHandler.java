package tomwaa.Oblig4.interfaces;

import java.util.ArrayList;

public interface ObjectFileHandler<T>
{
    void writeObjectsToFile(ArrayList<T> list, String filename);
    ArrayList<T> readObjectsFromFile(String filename);
}
