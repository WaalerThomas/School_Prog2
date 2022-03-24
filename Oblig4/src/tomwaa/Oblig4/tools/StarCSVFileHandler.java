package tomwaa.Oblig4.tools;

import tomwaa.Oblig4.interfaces.ObjectFileHandler;
import tomwaa.Oblig4.models.Star;

import java.io.*;
import java.util.ArrayList;

public class StarCSVFileHandler implements ObjectFileHandler<Star>
{
    // NOTE: Will use default constructor for instantiating so won't create one

    @Override
    public void writeObjectsToFile(ArrayList<Star> list, String filename)
    {
        try (
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(filename) )
        ) {
            // Write the header of the CSV file
            bufferedWriter.write("name,radius,mass,effectiveTemp");
            bufferedWriter.newLine();
            for (Star s : list)
            {
                bufferedWriter.write(s.getName() + "," + s.getRadius() + "," + s.getMass() + "," + s.getEffectiveTemp());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Star> readObjectsFromFile(String filename)
    {
        ArrayList<Star> returnList = new ArrayList<>();

        try (
            BufferedReader bufferedReader = new BufferedReader( new FileReader(filename) )
        ) {
            String line;
            bufferedReader.readLine(); // Will read the header line so that we skip it before the loop

            while ( (line = bufferedReader.readLine()) != null )
            {
                String[] values = line.split(",");
                Star star = new Star(values[0], Double.parseDouble(values[1]),
                        Double.parseDouble(values[2]), Integer.parseInt(values[3]));
                returnList.add(star);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnList;
    }
}
