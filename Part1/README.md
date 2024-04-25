Horse Racing Simulation
Overview
This project simulates a horse racing event where multiple horses compete to reach the finish line. The simulation includes a Race class to manage the race and a Horse class to represent individual horses.

Horse Class
The Horse class encapsulates the attributes and behaviors of a horse participating in the race. It includes the following attributes:


in the Horse class, you'll find all the info about a horse like its name, symbol, how far it's gone, if it fell, and how confident it is. To get these details, you can use methods like getName(), getSymbol(), getDistanceTravelled(), getConfidence(), and hasFallen(). If you need to change things up, there are methods like setSymbol(char newSymbol) and setConfidence(double newConfidence) for updating symbol and confidence. The horse can also move forward with moveForward() or fall with fall().



The Race class makes sure the race runs smoothly and directs the horses toward the finish line. You can add horses, start the race, and see who wins with its methods.

Features
Tracks the length of the racetrack.
Manages multiple horses competing in separate lanes.
Simulates horse movement based on confidence levels and random chance.
Handles tie scenarios where multiple horses finish simultaneously.
Usage

To use the Race and Horse classes:
You can run the main method of the horse class to test each of the methods present using an IDE like Visual studio Code or by using command/terminal to run your code. In order to run the race, you can run the main method of the race.java file.

