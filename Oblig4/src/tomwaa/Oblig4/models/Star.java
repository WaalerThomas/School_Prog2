package tomwaa.Oblig4.models;

public class Star extends CelestialBody
{
    public static final int RELATION_RSUN_KM = 695_700;
    public static final double RELATION_MSUN_KG = 1.98892E30;

    private int effectiveTemp;

    public Star(String name, double radius, double mass, int effectiveTemp)
    {
        super(name, radius, mass);
        this.effectiveTemp = effectiveTemp;
    }

    @Override
    public String toString() {
        return "Star '" + getName() + "' has a radius of " + getRadius() + " Rsun and a mass of " + getMass() +
                " Msun with an effective temperature of " + effectiveTemp + " K";
    }

    @Override
    public double getRadiusInKM() {
        return getRadius() * RELATION_RSUN_KM;
    }

    @Override
    public double getMassInKG() {
        return getMass() * RELATION_MSUN_KG;
    }

    public int getEffectiveTemp() {
        return effectiveTemp;
    }
    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}
