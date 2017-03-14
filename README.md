# MazeSolver
A maze solver that will solve a given maze

Project was inspired by the Youtube Channel - Codephile : https://www.youtube.com/watch?v=rop0W4QDOUI
This project expands on his application of the Bredth First Search Algorithm towards mazes of various shapes/sizes.

Goals of this Project:
  -Application of BFS Algorithm [ ]
  -Application of DFS Algorithm [ ]
  -Visual Representation of Solved Maze Pathway [ ]
  -Timer Function to compare Algorithms [ ]
  -Application to Orthoganal Mazes [ ]
  -Application to Circular Mazes [ ]
  
Optional:
  -Application to Triangular Mazes [ ]
  -Application to Hexagonal Mazes [ ]
  

The mazes were generated using a third party program called Daedalus: www.astrolog.org/labyrnth/daedalus.htm
The program has ben extracted and put inside the directory: ".../src/res/Daedalus"
This program DOES NOT belong to me; however it has been used to generate the pixelized mazes that will be inserted to
test cases and solve mazes. For more information regarding Daedalus and its usage, plase go to their site as I had 
posted above.

Log4j2 API was added to the application project. It will be used to help log/debug portions of the project. For further
information, you could see the comments inside of the java classes.

Topics Include: 
I/O-->
  1. Browsing Directory for PNG file as well as selecting folder for solution output ([ ])
  2. Parsing PNG image (Have to research an API that is capable of reading PNG pixels [ ])
Swing GUI
Logging-->
  1. Using Apache Logging Log4j2 API (Manually imported; however, you could also use a Maven dependency)
  2. FileAppender handling level DEBUG+ and ConsoleAppender handling level INFO+ (To be implemented [ ])
RegEx-->
  1. Possibility of incorporating RegEx to "check" filepath format ([ ])
OOP Concepts -->
  1. Proper Inheritance/Polymorphism (IS-A relationship established as well as usage of overriding -- dynamic polymorphism)
    a. Maze Abstract Class with RectMaze Child Class, CircMaze Child Class.... ([ ])
    b. MazeScan Abstract Class with RectScanner Child Class, CircScanner Child Class....([ ])
  2. Proper Encapsulation
    a. Utilizing the 'PRIVATE' access modifier to prevent access from outside classes
    b. Incorporating Getters and Setters
  3. Proper Abstraction
    a. Hiding Implementation details 
Event Handling -->
  1. Utilizing Listeners to handle events such as a user 'clicking a button'.


If you have any questions or regards feel free to contact me via e-mail.

This README.txt will be updated as the project continues...
