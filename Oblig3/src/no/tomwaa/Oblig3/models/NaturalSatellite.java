package no.tomwaa.Oblig3.models;

public abstract class NaturalSatellite extends CelestialBody
{
    public static final int RELATION_AU_KM = 149_597_871;
    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    private float semiMajorAxis;
    private float eccentricity;
    private int orbitalPeriod;
    private CelestialBody centralCelestialBody;

    protected NaturalSatellite(float semiMajorAxis, float eccentricity, int orbitalPeriod)
    {
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = null;   // Gets set when you add to a planet system
    }

    public int distanceToCentralBody(double degrees) {
        // TODO: Implement
        return 0;
    }

    public double orbitingVelocity(double distance) {
        // TODO: Implement
        return 0.0;
    }

    public float getSemiMajorAxis() {
        return semiMajorAxis;
    }
    public void setSemiMajorAxis(float semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public float getEccentricity() {
        return eccentricity;
    }
    public void setEccentricity(float eccentricity) {
        this.eccentricity = eccentricity;
    }

    public int getOrbitalPeriod() {
        return orbitalPeriod;
    }
    public void setOrbitalPeriod(int orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }
    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }
}
