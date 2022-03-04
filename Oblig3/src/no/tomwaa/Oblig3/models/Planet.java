package no.tomwaa.Oblig3.models;

public class Planet extends NaturalSatellite
{
    public static final int RELATION_RJUP_KM = 71_492;
    public static final double RELATION_MJUP_KG = 1.898E27;

    public Planet(String name, double radius, double mass, float semiMajorAxis, float eccentricity, int orbitalPeriod) {
        super(name, radius, mass, semiMajorAxis, eccentricity, orbitalPeriod);
    }

    public double getSurfaceGravity()
    {
        double gravConstant = 6.67408E-11;
        double radiusInMeters = getRadiusInKM() * 1000;
        return (gravConstant * getMassInKG()) / (radiusInMeters * radiusInMeters);
    }

    @Override
    public String toString() {
        return "Planet '" + getName() + "' has a radius of " + getRadius() + " Rjup and a mass of " + getMass() + " Mjup";
    }

    @Override
    public double getRadiusInKM() {
        return getRadius() * RELATION_RJUP_KM;
    }

    @Override
    public double getMassInKG() {
        return getMass() * RELATION_MJUP_KG;
    }
}