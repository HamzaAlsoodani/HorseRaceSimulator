
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

    public static void main(String[] args) {
        // Create a horse instance
        Horse myHorse = new Horse('H', "Thunder", 0.8);

        // Test get methods
        System.out.println("Name: " + myHorse.getName());
        System.out.println("Symbol: " + myHorse.getSymbol());
        System.out.println("Distance Travelled: " + myHorse.getDistanceTravelled());
        System.out.println("Confidence: " + myHorse.getConfidence());
        System.out.println("Fallen: " + myHorse.hasFallen());

        // Test set methods
        myHorse.setSymbol('T');
        myHorse.setConfidence(0.9);
        System.out.println("\nAfter setting:");
        System.out.println("Symbol: " + myHorse.getSymbol());
        System.out.println("Confidence: " + myHorse.getConfidence());

        // Test moveForward method
        System.out.println("\nTesting moveForward:");
        myHorse.moveForward();
        System.out.println("Distance Travelled: " + myHorse.getDistanceTravelled());

        // Test fall method
        System.out.println("\nTesting fall:");
        myHorse.fall();
        System.out.println("Fallen: " + myHorse.hasFallen());

        // Test goBackToStart method
        System.out.println("\nTesting goBackToStart:");
        myHorse.goBackToStart();
        System.out.println("Distance Travelled: " + myHorse.getDistanceTravelled());
        System.out.println("Fallen: " + myHorse.hasFallen());
    }

    // Constructor
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence);
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
        if (!this.fallen) {
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
