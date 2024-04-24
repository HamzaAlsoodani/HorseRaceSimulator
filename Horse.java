
/**
 * Write a description of class Horse here.
 * 
 * @author Hamza Alsoodani
 * @version (a version number or a date)
 */
public class Horse {
    private String name;
    private char symbol;
    private int distanceTravelled;
    private boolean fallen;
    private double confidence;

    // Constructor
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence); // Ensures the confidence is set within range
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void fall() {
        this.fallen = true;
    }

    public double getConfidence() {
        return this.confidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public boolean hasFallen() {
        return this.fallen;
    }

    public void moveForward() {
        if (!this.fallen) { // Prevents moving forward if the horse has fallen
            this.distanceTravelled += 1;
        }
    }

    public void setConfidence(double newConfidence) {
        if (newConfidence < 0 || newConfidence > 1) {
            throw new IllegalArgumentException("Confidence must be between 0 and 1.");
        }
        this.confidence = newConfidence;
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }
}
