# LandAnalysis
This is a basic Java 8 project that was developed and tested in Eclipse neon.

The domain objects are: Field, Point, and FertileShape.
The control objects are: 
* Controller: This is the main class that manages execution.
* IOHelper: This helper class gets user input and prints to stdout.
* Surveyor: This is a worker class that traverses a Field object and builds a list of FertileShapes within the field object as it travels.

TESTING
There is a unit test package (com.stowellperformance.LandAnalysis.Test) in which tests can be run from Eclipse by right clicking a test class->Run As->JUnit test

RUNNING
Within the top level of the project is a resources folder containing a properties file (landAnalysis.properties). 
Here you may configure the xMin,xMax,yMin, and yMax for the field as well as whether or not stats should be printed (currently only elapsed time is printed)

You can compile the project with the following:

After compiling the project can be run like this:


INTERACTING

