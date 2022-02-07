package no.tomwaa.Oblig2.models;

public class Star extends Planet
{
    private int effectiveTemp;

    public Star(String name, double radius, double mass, int effectiveTemp)
    {
        super(name, radius, mass);
        this.effectiveTemp = effectiveTemp;
    }

    @Override
    public String toString() {
        return "Star '" + name + "' has a radius of " + radius + " Rsun and a mass of " + mass +
                " Msun with an effective temperature of " + effectiveTemp + " K";
    }

    @Override
    public double getMassInKG() {
        return mass * 1.98892E30;
    }
    @Override
    public double getRadiusInKM() {
        return radius * 695700;
    }

    public int getEffectiveTemp() {
        return effectiveTemp;
    }
    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}
