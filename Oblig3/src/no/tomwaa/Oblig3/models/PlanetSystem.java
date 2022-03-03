package no.tomwaa.Oblig3.models;

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

    public void printPlanets()
    {
        // Prints out the information about all the planets in this planetsystem using the toString() method
        System.out.println("===== Planets in '" + name + "' =====");
        System.out.println(centerStar);
        for (Planet planet : planets) {
            System.out.println(planet);
        }
    }

    public Planet getBiggestPlanet()
    {
        // Starting on the second planet in the list since the first one will always be the biggest at the start
        int biggestIndex = 0;
        for (int i = 1; i < planets.size(); i++)
        {
            if (planets.get(i).getRadius() > planets.get(biggestIndex).getRadius()) {
                biggestIndex = i;
            }
            else if (planets.get(i).getRadius() == planets.get(biggestIndex).getRadius()) {
                // If same radius, then check who has the biggest mass
                if (planets.get(i).getMass() > planets.get(biggestIndex).getMass()) {
                    biggestIndex = i;
                }
            }
        }
        return planets.get(biggestIndex);
    }

    public Planet getSmallestPlanet() {
        // Starting on the second planet in the list since the first one will always be the smallest at the start
        int smallestIndex = 0;
        for (int i = 1; i < planets.size(); i++)
        {
            if (planets.get(i).getRadius() < planets.get(smallestIndex).getRadius()) {
                smallestIndex = i;
            }
            else if (planets.get(i).getRadius() == planets.get(smallestIndex).getRadius()) {
                // If same radius, then check who has the smallest mass
                if (planets.get(i).getMass() < planets.get(smallestIndex).getMass()) {
                    smallestIndex = i;
                }
            }
        }
        return planets.get(smallestIndex);
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

    public void addPlanet(Planet planet)
    {
        planets.add(planet);
        planet.setCentralCelestialBody(centerStar);
    }
    public Planet getPlanet(String name)
    {
        // Return null if no planet is found
        for (Planet planet : planets) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }
        return null;
    }
}