package tomwaa.Oblig4.models;

public abstract class NaturalSatellite extends CelestialBody
{
    public static final int RELATION_AU_KM = 149_597_871;
    public static final double GRAVITATIONAL_CONSTANT = 6.67408E-11;

    private float semiMajorAxis;
    private float eccentricity;
    private int orbitalPeriod;
    private CelestialBody centralCelestialBody;

    protected NaturalSatellite(String name, double radius, double mass, float semiMajorAxis,
                               float eccentricity, int orbitalPeriod)
    {
        super(name, radius, mass);
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.orbitalPeriod = orbitalPeriod;
        this.centralCelestialBody = null;   // Gets set when you add to a planet system
    }

    public double distanceToCentralBody(double degrees) {
        // Not using Math.pow() for squaring something because it's faster (Not necessary here though)
        return ( (semiMajorAxis * (1 - (eccentricity * eccentricity))) /
                (1 + eccentricity * Math.cos( Math.toRadians(degrees) )) ) * RELATION_AU_KM;
    }

    public double orbitingVelocity(double distance) {
        // Returns velocity in km/s
        return Math.sqrt( GRAVITATIONAL_CONSTANT * centralCelestialBody.getMassInKG() / (distance * 1000) ) / 1000;
    }

    public double orbitingVelocitySI(double distance) {
        // Returns velocity in m/s which is the SI-unit for velocity
        // NOTE: Would rather have the other method to return as m/s as a default and then have this method be called
        //       something like orbitingVelocityInKMPS() so that I don't have to first divide by 1000 and then
        //       multiply by 1000 again
        return orbitingVelocity(distance) * 1000;
    }

    public double distanceTo(NaturalSatellite naturalSatellite, int days)
    {
        // Initializing all the information needed from body 1
        double satDegrees1 = days * (360.0 / orbitalPeriod);
        double satDistance1 = distanceToCentralBody(satDegrees1);
        double satX1 = Math.cos(Math.toRadians(satDegrees1)) * satDistance1;
        double satY1 = Math.sin(Math.toRadians(satDegrees1)) * satDistance1;

        // Initializing all the information needed from body 2
        double satDegrees2 = days * (360.0 / naturalSatellite.getOrbitalPeriod());
        double satDistance2 = naturalSatellite.distanceToCentralBody(satDegrees2);
        double satX2 = Math.cos(Math.toRadians(satDegrees2)) * satDistance2;
        double satY2 = Math.sin(Math.toRadians(satDegrees2)) * satDistance2;

        return Math.sqrt( Math.pow(satX2 - satX1, 2) + Math.pow(satY2 - satY1, 2) );
    }

    public double getMaxDistanceToCentralBody() {
        double maxDistance = 0;
        for (int i = 0; i <= 360; i++)
        {
            double distance = distanceToCentralBody(i);
            if (i == 0) {   // The first degree will always be the max distance
                maxDistance = distance;
            }
            else if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        return maxDistance;
    }

    public double getMinDistanceToCentralBody() {
        double minDistance = 0;
        for (int i = 0; i <= 360; i++)
        {
            double distance = distanceToCentralBody(i);
            if (i == 0) {   // The first degree will always be the min distance
                minDistance = distance;
            }
            else if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;
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
