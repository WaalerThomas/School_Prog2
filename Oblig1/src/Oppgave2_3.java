public class Oppgave2_3
{
    public static void main(String[] args)
    {
        Planet planet_1 = new Planet("Mercury", 2439, 3.3E23);
        Planet planet_2 = new Planet("Venus", 6052, 4.87E24);
        Planet planet_3 = new Planet("Earth", 6378, 5.97E24);
        Planet planet_4 = new Planet("Mars", 3396, 6.42E23);
        Planet planet_5 = new Planet("Jupiter", 71492, 1898E24);

        planet_1.printInfo();
        planet_2.printInfo();
        planet_3.printInfo();
        planet_4.printInfo();
        planet_5.printInfo();
    }
}
