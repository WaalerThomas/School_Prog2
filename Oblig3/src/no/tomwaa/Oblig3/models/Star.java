package no.tomwaa.Oblig3.models;

public class Star extends CelestialBody
{
    public static final int RELATION_RSUN_KM = 695_700;
    public static final double RELATION_MSUN_KG = 1.98892E30;

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

    @Override
    public double getRadiusInKM() {
        return radius * RELATION_RSUN_KM;
    }

    @Override
    public double getMassInKG() {
        return mass * RELATION_MSUN_KG;
    }

    public int getEffectiveTemp() {
        return effectiveTemp;
    }
    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}