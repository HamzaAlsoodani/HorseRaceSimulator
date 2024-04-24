import org.junit.Assert;
import org.junit.Test;

public class RaceTest {

    @Test
    public void testRaceInitialization() {
        Race race = new Race(100);
        Assert.assertNotNull("Race should be initialized", race);
    }

    @Test
    public void testAddHorse() {
        Race race = new Race(100);
        Horse horse = new Horse('H', "Lightning", 0.8);
        race.addHorse(horse, 1);
        Assert.assertTrue("Horse should be added to lane 1", race.getLane1Horse() != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLane() {
        Race race = new Race(100);
        Horse horse = new Horse('H', "Lightning", 0.8);
        race.addHorse(horse, 4); // Assuming only 3 lanes exist
    }

    @Test
    public void testRaceCompletion() {
        Race race = new Race(5);
        Horse horse = new Horse('H', "Lightning", 0.99);
        race.addHorse(horse, 1);
        while (!race.isFinished()) {
            race.startRace(); // Simulate the race in a controlled loop
        }
        Assert.assertTrue("Race should be finished", race.isFinished());
        Assert.assertEquals("Lightning should win", "Lightning", race.getWinner().getName());
    }
}
