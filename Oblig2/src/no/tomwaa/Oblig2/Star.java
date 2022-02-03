package no.tomwaa.Oblig2;

public class Star extends Planet
{
    private int effectiveTemp;

    public Star(String name, double radius, double mass, int effectiveTemp)
    {
        super(name, radius, mass);
        this.effectiveTemp = effectiveTemp;
    }

    public int getEffectiveTemp() {
        return effectiveTemp;
    }
    public void setEffectiveTemp(int effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }
}
