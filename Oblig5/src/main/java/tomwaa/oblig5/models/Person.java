package tomwaa.oblig5.models;

import tomwaa.oblig5.enums.GenderType;

public class Person extends Artist
{
    private GenderType gender;

    public Person() {};
    public Person(String name, GenderType gender)
    {
        super(name);
        this.gender = gender;
    }

    public GenderType getGender() {
        return gender;
    }
    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
