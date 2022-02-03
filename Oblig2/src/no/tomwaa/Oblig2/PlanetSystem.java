package no.tomwaa.Oblig2;

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

    public ArrayList<Planet> getPlanets() {
        return planets;
    }
    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }
}
