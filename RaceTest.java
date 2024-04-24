public class RaceTest {
    public static void main(String[] args) {
        // Create a race of 100 meters
        Race race = new Race(10);

        // Add three horses
        race.addHorse(new Horse('♘', "Champion", 0.9), 1);
        race.addHorse(new Horse('♞', "Lightning", 0.8), 2);
        race.addHorse(new Horse('♜', "Thunder", 0.85), 3);

        // Start the race
        race.startRace();
    }
}