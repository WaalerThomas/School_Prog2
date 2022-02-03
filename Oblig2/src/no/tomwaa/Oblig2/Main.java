package no.tomwaa.Oblig2;

/*
    Solar System Data

    name, radius, mass, effectiveTemp
    Sun, 1.0, 1.0, 5777

    name, radius, mass
    Mercury, 0.03412549655905556, 1.7297154899894627E-4
    Venus, 0.08465003077267387, 0.002564278187565859
    Earth, 0.08911486599899289, 0.003146469968387777
    Mars, 0.04741089912158004, 3.3667017913593256E-4
    Jupiter, 1.0, 1.0
    Saturn, 0.8145247020645666, 0.2994204425711275
    Uranus, 0.35475297935433336, 0.04573761854583773
    Neptune, 0.34440217087226543, 0.05395152792413066
*/

public class Main
{
    public static void main(String[] args)
    {
        PlanetSystem solarSystem = new PlanetSystem("Solar system",
                new Star("Sun", 1.0f, 1.0f, 577));
    }
}
