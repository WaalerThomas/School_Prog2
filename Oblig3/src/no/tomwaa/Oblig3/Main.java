package no.tomwaa.Oblig3;

import no.tomwaa.Oblig3.models.Planet;
import no.tomwaa.Oblig3.models.PlanetSystem;
import no.tomwaa.Oblig3.models.Star;

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

        // ===== Oblig 3 =====
        // Print out the distance from earth to the sun
        newLine();
        Planet earth = solarSystem.getPlanet("Earth");
        int[] degrees1 = {0, 90, 180, 270, 360};
        for (int degree : degrees1) {
            System.out.println("Earth has a distance of " + earth.distanceToCentralBody(degree) + " km to the Sun at " +
                    degree + " degrees");
        }

        // Print out earth velocity at different degrees
        newLine();
        int[] degrees2 = {0, 45, 90, 135, 180};
        for (int degree : degrees2)
        {
            double distance = earth.distanceToCentralBody(degree);
            System.out.println("At a distance of " + distance + " km, Earth has a velocity of "
                    + earth.orbitingVelocity(distance) + " km/s");
        }

        // Find the min and max distance between earth and the sun during a year
        newLine();
        double maxDistance = 0;
        double minDistance = 0;
        double degreesPerDay = 360.0 / earth.getOrbitalPeriod();
        for (int i = 1; i <= 365; i++)
        {
            double distance = earth.distanceToCentralBody(degreesPerDay * i);
            if (i == 1)     // The first degree max and min is the first distance calculated
            {
                maxDistance = distance;
                minDistance = distance;
                continue;
            }

            if      (distance > maxDistance) { maxDistance = distance; }
            else if (distance < minDistance) { minDistance = distance; }
        }
        System.out.println("Earth has a maximum distance of " + (int)maxDistance + " km");
        System.out.println("Earth has a minimum distance of " + (int)minDistance + " km");

        // Find the min and max distance between a planet and the sun
        newLine();
        System.out.println("Mars has a maximum distance of " + (int)mars.getMaxDistanceToCentralBody() + " km");
        System.out.println("Mars has a minimum distance of " + (int)mars.getMinDistanceToCentralBody() + " km");

        // Finding the distance between Mars and Saturn
        newLine();
        double marsDegrees = 180 * (360.0 / mars.getOrbitalPeriod());
        double marsDistance = mars.distanceToCentralBody(marsDegrees);
        double marsX = Math.cos(Math.toRadians(marsDegrees)) * marsDistance;
        double marsY = Math.sin(Math.toRadians(marsDegrees)) * marsDistance;

        double saturnDegrees = 180 * (360.0 / saturn.getOrbitalPeriod());
        double saturnDistance = saturn.distanceToCentralBody(saturnDegrees);
        double saturnX = Math.cos(Math.toRadians(saturnDegrees)) * saturnDistance;
        double saturnY = Math.sin(Math.toRadians(saturnDegrees)) * saturnDistance;

        double distanceBetween = Math.sqrt( Math.pow(saturnX - marsX, 2) + Math.pow(saturnY - marsY, 2) );
        System.out.println("Distance between Mars and Saturn at day 180: " + distanceBetween + " km");

        // Finding the distance between two planets using a method
        newLine();
        System.out.println("Distance between Mars and Saturn at day 180: " +
                mars.distanceTo(saturn, 180) + " km");
        System.out.println("Distance between Earth and Neptune at day 42: " +
                earth.distanceTo(solarSystem.getPlanet("Neptune"), 42) + " km");
    }
}
