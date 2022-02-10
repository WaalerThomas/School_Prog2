package no.tomwaa.Oblig2;

import no.tomwaa.Oblig2.models.Planet;
import no.tomwaa.Oblig2.models.PlanetSystem;
import no.tomwaa.Oblig2.models.Star;

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
        solarSystem.addPlanet( new Planet("Mercury", 0.03412549655905556, 1.7297154899894627E-4) );
        solarSystem.addPlanet( new Planet("Venus", 0.08465003077267387, 0.002564278187565859) );
        solarSystem.addPlanet( new Planet("Earth", 0.08911486599899289, 0.003146469968387777) );
        solarSystem.addPlanet( new Planet("Mars", 0.04741089912158004, 3.3667017913593256E-4) );
        solarSystem.addPlanet( new Planet("Jupiter", 1.0, 1.0) );
        solarSystem.addPlanet( new Planet("Saturn", 0.8145247020645666, 0.2994204425711275) );
        solarSystem.addPlanet( new Planet("Uranus", 0.35475297935433336, 0.04573761854583773) );
        solarSystem.addPlanet( new Planet("Neptune", 0.34440217087226543, 0.05395152792413066) );

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
