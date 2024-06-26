import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell
 * @version 1.0
 */
public class Race {
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    private boolean finished;
    private List<Horse> winningHorses; // Store multiple potential winners

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance) {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
        finished = false; // Initialize as not finished
        winningHorses = new ArrayList<>();
    }

    public static void main(String[] args) {

        Race race = new Race(15);

        // Add three horses
        // race.addHorse(new Horse('♘', "PIPPI LONGSTOCKING", 0.6), 1);
        // race.addHorse(new Horse('♞', "KOKOMO", 0.6), 2);
        // race.addHorse(new Horse('♜', "EL JEFE", 0.4), 3);

        // Start the race
        race.startRace();
    }

    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse   the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber == 1) {
            lane1Horse = theHorse;
        } else if (laneNumber == 2) {
            lane2Horse = theHorse;
        } else if (laneNumber == 3) {
            lane3Horse = theHorse;
        } else {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */
    public void startRace() {
        finished = false;

        // Ensure at least one horse is in the race to start
        if (lane1Horse == null && lane2Horse == null && lane3Horse == null) {
            System.out.println("No horses are in the race.");
            return; // Exit if no horses are added
        }

        while (!finished) {
            // Only move and check horses that are present in their lanes
            if (lane1Horse != null) {
                moveHorse(lane1Horse);
            }
            if (lane2Horse != null) {
                moveHorse(lane2Horse);
            }
            if (lane3Horse != null) {
                moveHorse(lane3Horse);
            }

            printRace();
            checkWinCondition();

            // Check if all participating horses have fallen
            if ((lane1Horse != null && lane1Horse.hasFallen() || lane1Horse == null) &&
                    (lane2Horse != null && lane2Horse.hasFallen() || lane2Horse == null) &&
                    (lane3Horse != null && lane3Horse.hasFallen() || lane3Horse == null)) {
                System.out.println("All participating horses have fallen! Race is over.");
                finished = true;
            }

            // Delay to simulate time passing between race steps
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        printRace();
        announceWinners();
    }

    private void checkWinCondition() {
        if (lane1Horse != null && raceWonBy(lane1Horse) && !winningHorses.contains(lane1Horse)) {
            winningHorses.add(lane1Horse);
        }
        if (lane2Horse != null && raceWonBy(lane2Horse) && !winningHorses.contains(lane2Horse)) {
            winningHorses.add(lane2Horse);
        }
        if (lane3Horse != null && raceWonBy(lane3Horse) && !winningHorses.contains(lane3Horse)) {
            winningHorses.add(lane3Horse);
        }
        if (!winningHorses.isEmpty()) {
            finished = true;
        }
    }

    private void announceWinners() {
        if (winningHorses.isEmpty()) {
            // Check if all horses have fallen to determine the appropriate announcement
            if ((lane1Horse != null && lane1Horse.hasFallen()) &&
                    (lane2Horse != null && lane2Horse.hasFallen()) &&
                    (lane3Horse != null && lane3Horse.hasFallen())) {
                System.out.println("All horses have fallen! No winners in this race.");
            } else {
                System.out.println("No horse finished the race.");
            }
        } else if (winningHorses.size() == 1) {
            System.out.println("And the winner is " + winningHorses.get(0).getName() + "!");
        } else {
            System.out.print("It's a tie between: ");
            for (Horse horse : winningHorses) {
                System.out.print(horse.getName() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) {
        // if the horse has fallen it cannot move,
        // so only run if it has not fallen

        if (!theHorse.hasFallen()) {
            // the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            // the probability that the horse will fall is very small (max is 0.1)
            // but will also will depends exponentially on confidence
            // so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1 * theHorse.getConfidence() * theHorse.getConfidence())) {
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse) {
        return theHorse.getDistanceTravelled() >= raceLength;
    }

    /***
     * Print the race on the terminal
     */
    private void printRace() {
        System.out.print('\u000C'); // Clear the terminal window

        multiplePrint('=', raceLength + 3); // top edge of track
        System.out.println();

        printLane(lane1Horse); // Print each lane safely
        System.out.println();

        printLane(lane2Horse);
        System.out.println();

        printLane(lane3Horse);
        System.out.println();

        multiplePrint('=', raceLength + 3); // bottom edge of track
        System.out.println();
    }

    /**
     * print a horse's lane during the race
     * for example
     * | X |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse) {
        if (theHorse == null) {
            System.out.println(" ");
            return; // Exit the method if there is no horse in the lane
        }

        // The rest of the method remains unchanged
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - spacesBefore;

        System.out.print('|');
        multiplePrint(' ', spacesBefore);

        if (theHorse.hasFallen()) {
            System.out.print('\u2716'); // Unicode for a fallen horse symbol
        } else {
            System.out.print(theHorse.getSymbol());
        }

        multiplePrint(' ', spacesAfter);
        System.out.print('|');

        // Print the confidence next to the horse lane
        System.out.printf(" %s (Confidence: %.2f)", theHorse.getName(), theHorse.getConfidence());
    }

    public boolean isFinished() {
        return finished;
    }

    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times) {
        int i = 0;
        while (i < times) {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    public void setNumberOfLanes(int newLanes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNumberOfLanes'");
    }
}
