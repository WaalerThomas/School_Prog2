package tomwaa.Oblig4.models;

public abstract class CelestialBody implements Comparable<CelestialBody>
{
    public static final int RELATION_REARTH_KM = 6_371;
    public static final double RELATION_MEARTH_KG = 5.972E24;

    private String name;
    private double radius;
    private double mass;

    protected CelestialBody(String name, double radius, double mass)
    {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    @Override
    public int compareTo(CelestialBody o) {
        // Returns negative if this has the smallest mass, and returns positive if this
        // has the biggest mass
        // Will sort in ascending order
        if (this.mass > o.mass)         { return  1; }
        else if (this.mass < o.mass)    { return -1; }
        else                            { return  0; }
    }

    public abstract double getRadiusInKM();
    public abstract double getMassInKG();

    public double getRadiusEarth() {
        return getRadiusInKM() / RELATION_REARTH_KM;
    }
    public double getMassEarth() {
        return getMassInKG() / RELATION_MEARTH_KG;
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
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
}
