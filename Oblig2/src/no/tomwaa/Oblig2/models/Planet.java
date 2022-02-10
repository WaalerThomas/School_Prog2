package no.tomwaa.Oblig2.models;

public class Planet
{
    private String name;
    private double radius;
    private double mass;

    public Planet(String name, double radius, double mass)
    {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public double getSurfaceGravity()
    {
        double gravConstant = 6.67408E-11;
        double radiusInMeters = getRadiusInKM() * 1000;
        return (gravConstant * getMassInKG()) / (radiusInMeters * radiusInMeters);
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
    public double getRadiusEarth() {
        return getRadiusInKM() / 6371;
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
    public double getMassEarth() {
        return getMassInKG() / 5.972E24;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
}
