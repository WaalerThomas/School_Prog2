package tomwaa.oblig5.models;

public class Group extends Artist
{
    private int yearFounded;

    public Group() {};
    public Group(String name, int yearFounded)
    {
        super(name);
        this.yearFounded = yearFounded;
    }

    public int getYearFounded() {
        return yearFounded;
    }
    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }
}
