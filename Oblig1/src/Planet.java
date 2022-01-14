public class Planet
{
    private String name;
    private int radius;
    private double mass;

    Planet(String name, int radius, double mass)
    {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
    }

    public void printInfo() {
        System.out.println("Planeten " + name + " har en radius på "
                + radius + " km og en masse på " + mass + " kg");
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
