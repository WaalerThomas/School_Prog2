package no.tomwaa.Oblig2.models;

import java.util.ArrayList;

public class PlanetSystem
{
    private String name;
    private Star centerStar;
    private ArrayList<Planet> planets;

    public PlanetSystem(String name, Star centerStar)
    {
        this.name = name;
        this.centerStar = centerStar;
        planets = new ArrayList<>();
    }

    // Prints out the information about all the planets in this planetsystem
    public void printPlanets()
    {
        System.out.println("===== Planets in '" + name + "' =====");
        System.out.println(centerStar);
        for (Planet planet : planets) {
            System.out.println(planet);
        }
    }

    @Override
    public String toString() {
        return "Planet-system '" + name + "' has a center star called '" +
                centerStar.getName() + "' and contains " + planets.size() + " planets";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Star getCenterStar() {
        return centerStar;
    }
    public void setCenterStar(Star centerStar) {
        this.centerStar = centerStar;
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }
    public Planet getPlanet(String name) {
        // Return null if no planet found
        for (Planet planet : planets) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }

        return null;
    }
}
