package no.tomwaa.Oblig2.models;

public class Star
{
    private String name;
    private double radius;
    private double mass;
    private int effectiveTemp;

    public Star(String name, double radius, double mass, int effectiveTemp)
    {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.effectiveTemp = effectiveTemp;
    }

    @Override
    public String toString() {
        return "Star '" + name + "' has a radius of " + radius + " Rsun and a mass of " + mass +
                " Msun with an effective temperature of " + effectiveTemp + " K";
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
        return radius * 695700;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }
    public double getMassInKG() {
        return mass * 1.98892E30;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getEffectiveTemp() {
        return effectiveTemp;
    }
    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}
