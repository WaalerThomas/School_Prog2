package no.tomwaa.Oblig2.models;

public class Planet
{
    // Protected so that Star class has access to them
    protected String name;
    protected double radius;
    protected double mass;

    public Planet(String name, double radius, double mass)
    {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Planet '" + name + "' has a radius of " + radius + " Rjup and a mass of " + mass + " Mjup";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }
    public double getRadiusInKM() {
        return radius * 71492;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }
    public double getMassInKG() {
        return mass * 1.898E27;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
}
