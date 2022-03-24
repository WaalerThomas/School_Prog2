package tomwaa.Oblig4;

import tomwaa.Oblig4.models.Planet;
import tomwaa.Oblig4.models.PlanetSystem;
import tomwaa.Oblig4.models.Star;
import tomwaa.Oblig4.tools.StarCSVFileHandler;

import java.util.ArrayList;

public class Main
{
    public static void newLine() {
        System.out.println();
    }

    public static void main(String[] args)
    {
        PlanetSystem solarSystem = new PlanetSystem("Solar system",
                new Star("Sun", 1.0f, 1.0f, 577) );
        solarSystem.addPlanet( new Planet("Mercury", 0.03412549655905556, 1.7297154899894627E-4,
                0.387f, 0.206f, 88) );
        solarSystem.addPlanet( new Planet("Venus", 0.08465003077267387, 0.002564278187565859,
                0.723f, 0.007f, 225) );
        solarSystem.addPlanet( new Planet("Earth", 0.08911486599899289, 0.003146469968387777,
                1f, 0.017f, 365) );
        solarSystem.addPlanet( new Planet("Mars", 0.04741089912158004, 3.3667017913593256E-4,
                1.524f, 0.093f, 687) );
        solarSystem.addPlanet( new Planet("Jupiter", 1.0, 1.0, 5.20440f,
                0.049f, 4380) );
        solarSystem.addPlanet( new Planet("Saturn", 0.8145247020645666, 0.2994204425711275,
                9.5826f, 0.057f, 10585) );
        solarSystem.addPlanet( new Planet("Uranus", 0.35475297935433336, 0.04573761854583773,
                19.2184f, 0.046f, 30660) );
        solarSystem.addPlanet( new Planet("Neptune", 0.34440217087226543, 0.05395152792413066,
                30.11f, 0.010f, 60225) );

        // Sort planets
        System.out.println("\n----- Before sorting:");
        solarSystem.printPlanets();

        System.out.println("\n----- After sorting");
        solarSystem.sortPlanets();
        solarSystem.printPlanets();

        // Reading and Writing star objects
        StarCSVFileHandler starFileHandler = new StarCSVFileHandler();

        ArrayList<Star> starsList = new ArrayList<>();
        starsList.add(new Star("Sun", 1.0, 1.0, 5_777));
        starsList.add(new Star("MU Cas", 4.192, 4.657, 14_750));
        starsList.add(new Star("V69-47", 1.316, 0.876, 5_945));
        starsList.add(new Star("YZ Cas", 2.547, 2.308, 9_200));
        starsList.add(new Star("NGC188", 1.425, 1.102, 5_900));
        starsList.add(new Star("zet Phe", 2.853, 3.922, 14_550));

        starFileHandler.writeObjectsToFile(starsList, "stars.csv");
        ArrayList<Star> readStarsList = starFileHandler.readObjectsFromFile("stars.csv");

        newLine();
        for (Star s : readStarsList) {
            System.out.println(s);
        }
    }
}
