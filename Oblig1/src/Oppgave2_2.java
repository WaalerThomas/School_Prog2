public class Oppgave2_2
{
    public static void main(String[] args)
    {
        float earthGravity = 9.81f;
        float moonGravity = 1.62f;

        int weightOnEarth = 60;
        float weightOnMoon = (weightOnEarth / earthGravity) * moonGravity;

        System.out.printf("Your weight on Earth=%d kg | Your weight on the Moon=%.2f kg\n", weightOnEarth, weightOnMoon);
    }
}
