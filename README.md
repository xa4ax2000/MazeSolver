# MazeSolver
A maze solver that will solve a given maze

Project was inspired by the Youtube Channel - Codephile : https://www.youtube.com/watch?v=rop0W4QDOUI
This project expands on his application of the Bredth First Search Algorithm towards mazes of various shapes/sizes.

Goals of this Project:
  -Application of BFS Algorithm [x]
  -Application of DFS Algorithm [x]
  -Visual Representation of Solved Maze Pathway [x]
  -Timer Function to compare Algorithms [x]
  -Application to Orthoganal Mazes [x]
  -Application to Circular Mazes [ ]
  
Optional:
  -Application to Triangular Mazes [ ]
  -Application to Hexagonal Mazes [ ]
  

The mazes were generated using a third party program called Daedalus: www.astrolog.org/labyrnth/daedalus.htm

The Daedalus package has been REMOVED from the repository (you can download it for yourself in the link above).
This package has been removed because it was a tool used to generate mazes -- it did not serve a purpose with 
the underlying logic of my program nor was it an extension of the maze generation from Daedalus.

This program (Daedalus) DOES NOT belong to me; however it has been used to generate the pixelized mazes that will be inserted to
test cases and solve mazes. For more information regarding Daedalus and its usage, plase go to their site as I had 
posted above.

Log4j2 API was added to the application project. It will be used to help log/debug portions of the project. For further
information, you could see the comments inside of the java classes.

Topics Include: 
I/O-->
  1. Browsing Directory for PNG file as well as selecting folder for solution output ([x])
  2. Parsing PNG image (Have to research an API that is capable of reading PNG pixels [x])
Swing GUI
Logging-->
  1. Using Apache Logging Log4j2 API (Manually imported; however, you could also use a Maven dependency)
  2. FileAppender handling level DEBUG+ and ConsoleAppender handling level INFO+ (To be implemented [x])
RegEx-->
  1. Possibility of incorporating RegEx to "check" filepath format ([ ])
OOP Concepts -->
  1. Proper Inheritance/Polymorphism (IS-A relationship established as well as usage of overriding -- dynamic polymorphism)
    a. Maze Abstract Class with RectMaze Child Class, CircMaze Child Class.... ([x])
    b. MazeScan Abstract Class with RectScanner Child Class, CircScanner Child Class....([o]) -- Better design utilized...*
  2. Proper Encapsulation
    a. Utilizing the 'PRIVATE' access modifier to prevent access from outside classes
    b. Incorporating Getters and Setters
  3. Proper Abstraction
    a. Hiding Implementation details 
Event Handling -->
  1. Utilizing Listeners to handle events such as a user 'clicking a button'.

*NOTE: 'Better design utilized' meaning that this program functions under a hierarchy of classes.
        This 'hierarchy' follows in the order (From top to bottom) of: 
            GUI Classes: which CALL the 'logic/model' holding classes.
            Algorithm/Maze Class: Abstract classes that CONTAIN the IDEA of the varying Maze Types and Search Algorithms.
            Graph Class: Handler of the Maze Type classes -- utilizing the maze type classes to create a fully-functional graph
                         representation.
            Maze Type Classes (CircMaze, RectMaze...): Utilizes Node Class objects to build and re-draw the maze
            Node Class: Basic building block.
 In retrospect, I should have called the 'Graph Class' the 'MazeHandler Class' because the Node class establishes the role of what
 a graph is SUPPOSED to be via the ArrayList of adjacent nodes.

If you have any questions or regards feel free to contact me via e-mail.

3/19/17: 
  Removed some Debug log messages (technically LOG.LEVEL-TRACE), keeping the method 'ENTER' and 'EXIT' DEBUG logs.
  Implemented BFS algorithm logic using a Queue (considering utilizing a different Queue for multithreading???)
  Have to incorporate the Graph.draw() method which should call its respective maze.draw() to re-color the maze path,
  showing the solution.
  Finally, have to implement the Output of the program in the Graph.draw() method to the folder path
  Probably need to refine the INFO logs.
  What is after that? Determining a method to convert Circular mazes into an array or some container.
  
3/22/17:
  Added DFS algorithm
  Retouched on the GUI and Event Handling logic -
    1.  Added ChangedEvent Lisener to JTabbedPane to handle tab event change.
    2.  Added/Extended on Circular Layout GUI
  

This README.txt will be updated as the project continues....
