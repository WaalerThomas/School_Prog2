import java.util.ArrayList;
import java.util.Scanner;

public class Oppgave3
{
    private final Scanner reader;
    private final ArrayList<Planet> planets;
    private boolean isDoneAdding;

    public Oppgave3()
    {
        reader = new Scanner(System.in);
        planets = new ArrayList<>();
        isDoneAdding = false;
    }

    public void listPlanets()
    {
        System.out.println("===== Planeter =====");
        if (planets.size() == 0) { System.out.println("Ingen planeter registrert"); }
        for (int i = 0; i < planets.size(); i++) {
            System.out.printf("%d. %s\n", i, planets.get(i).getName());
        }
        System.out.println("====================");
    }

    public void printPlanet(int index)
    {
        if (index > planets.size() - 1 || index < 0) {
            System.out.println("[WARNING] Ingen planet med indeksen " + index);
            return;
        }

        planets.get(index).printInfo();
    }

    public void removePlanet(int index)
    {
        if (index > planets.size() - 1 || index < 0) {
            System.out.println("[WARNING] Ingen planet med indeksen " + index);
            return;
        }

        System.out.println("[INFO] Fjernet planeten '" + planets.get(index).getName() + "'");
        planets.remove(index);
    }

    public static void main(String[] args)
    {
        Oppgave3 oppg = new Oppgave3();

        // NOTE: Have not added checking of correct input type
        while (!oppg.isDoneAdding)
        {
            System.out.println("Velg hva å gjøre:");
            System.out.println("\t1. Legg til ny planet");
            System.out.println("\t2. List planeter");
            System.out.println("\t3. Print planet info");
            System.out.println("\t4. Fjern planet");
            System.out.println("\t0. Avslutt");

            System.out.print("\nValg: ");
            int userChoice = oppg.reader.nextInt();
            oppg.reader.nextLine(); // Reads the newline after nextInt
            switch (userChoice)
            {
                case 1: // Adding a planet
                {
                    System.out.println("===== Lager ny planet =====");
                    System.out.print("Skriv inn navn på planeten: ");
                    String planet_name = oppg.reader.nextLine();

                    System.out.print("Skiv inn radius til planeten: ");
                    int planet_radius = oppg.reader.nextInt();

                    System.out.print("Skriv inn massen til planeten: ");
                    double planet_mass = oppg.reader.nextDouble();

                    Planet my_planet = new Planet(planet_name, planet_radius, planet_mass);
                    oppg.planets.add(my_planet);
                    System.out.println("[INFO] La til planeten " + planet_name + "\n");
                } break;
                case 2: // List planets
                    oppg.listPlanets();
                    break;
                case 3:
                {
                    System.out.print("Oppgi indeks for planeten (-1 for å avbryte): ");
                    int printIndex = oppg.reader.nextInt();
                    if (printIndex < 0) { continue; }

                    oppg.printPlanet(printIndex);
                } break;
                case 4: // Remove a planet
                {
                    System.out.print("Oppgi indeks på planeten å fjerne (-1 for å avbryte): ");
                    int removeIndex = oppg.reader.nextInt();
                    if (removeIndex < 0) { continue; }

                    oppg.removePlanet(removeIndex);
                } break;
                case 0:
                    oppg.isDoneAdding = true;
                    break;
            }
        }
    }
}
