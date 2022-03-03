package no.tomwaa.Oblig3;

/*
    Solar System Data

    name, radius, mass, effectiveTemp
    Sun, 1.0, 1.0, 5777

    name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod
    Mercury, 0.03412549655905556, 1.7297154899894627E-4, 0.387, 0.206, 88
    Venus, 0.08465003077267387, 0.002564278187565859, 0.723, 0.007, 225
    Earth, 0.08911486599899289, 0.003146469968387777, 1, 0.017, 365
    Mars, 0.04741089912158004, 3.3667017913593256E-4, 1.524, 0.093, 687
    Jupiter, 1.0, 1.0, 5.20440, 0.049, 4380
    Saturn, 0.8145247020645666, 0.2994204425711275, 9.5826, 0.057, 10585
    Uranus, 0.35475297935433336, 0.04573761854583773, 19.2184, 0.046, 30660
    Neptune, 0.34440217087226543, 0.05395152792413066, 30.11, 0.010, 60225
*/

import no.tomwaa.Oblig3.models.Planet;
import no.tomwaa.Oblig3.models.PlanetSystem;
import no.tomwaa.Oblig3.models.Star;
import no.tomwaa.Oblig3.models.CelestialBody;

public class Main
{
    // Makes it look nicer when needing to make a newline
    public static void newLine() {
        System.out.println();
    }

    public static void main(String[] args)
    {
        // Create a planet system and add some planets
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

        // Print out planet-system information and all planets info
        System.out.println(solarSystem);
        solarSystem.printPlanets();

        // Print out mass and radius in SI-units for saturn and the sun
        newLine();
        Planet saturn = solarSystem.getPlanet("Saturn");
        Star sun = solarSystem.getCenterStar();
        System.out.println(saturn.getName() + " mass: " + saturn.getMassInKG() + " kg | radius: " + saturn.getRadiusInKM() + " km");
        System.out.println(sun.getName() + " mass: " + sun.getMassInKG() + " kg | radius: " + sun.getRadiusInKM() + " km");

        // What is the surface gravity on Neptune
        newLine();
        System.out.println("Surface gravity on Neptune: " + solarSystem.getPlanet("Neptune").getSurfaceGravity()
                + " m/s^2");

        // Find the biggest and smallest planets
        newLine();
        System.out.println("Biggest planet: " + solarSystem.getBiggestPlanet());
        System.out.println("Smallest planet: " + solarSystem.getSmallestPlanet());

        // Print out mass and radius in Mearth and Rearch for mars
        newLine();
        Planet mars = solarSystem.getPlanet("Mars");
        System.out.println(mars.getName() + " mass: " + mars.getMassEarth() + " Mearth | radius: " + mars.getRadiusEarth() + " Rearth");
    }
}
