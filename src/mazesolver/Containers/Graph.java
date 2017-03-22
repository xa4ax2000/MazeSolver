package mazesolver.Containers;

import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import mazesolver.Mazes.CircMaze;
import mazesolver.Mazes.Maze;
import mazesolver.Mazes.RectMaze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Andrew Hyun
 */
public class Graph {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    Logger LOG = LogManager.getLogger(Graph.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define variables/containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private Maze maze = null;
        private final String typeOfMaze;
        private BufferedImage img;
        private String solutionName;
        private int width, height;
        private static Raster scannedImage;
        private Node[][] mazeToNodeArray;
        private Node start, exit;
        private ColorModel colorModel;
    /* End of defining variables/containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
    /* Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Graph(File imageFile, String solutionName, String typeOfMaze){
        LOG.debug("Entering Graph Constructor...");
        
        //store the imageFile into a BufferedImage and the absolute path to put output
        this.solutionName = solutionName;
        try{
            img = ImageIO.read(imageFile);
            colorModel = img.getColorModel();
            LOG.info("Image file has been initialized.");
        }catch(IOException ex){
            LOG.error("Error has occurred: " + ex.toString());
            LOG.info("Failed to initialize Image File.");
        }
        
        /* Initialize variables                                               */
        scannedImage = img.getData();
        this.typeOfMaze = typeOfMaze;
        
        LOG.debug("Exiting Graph Constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public int getWidth(){
            return width;
        }
        
        public int getHeight(){
            return height;
        }
    /* End of Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Object's functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Node[] createGraph() {
        LOG.debug("Entering Graph.createGraph()...");
        
        if(typeOfMaze.contains("RectMaze")){
            maze = new RectMaze(scannedImage);
        }else{
            maze = new CircMaze(scannedImage);
        }
        
        LOG.info("Obtaining and Setting Array Dimensions...");
        /* Obtain arrayDimensions for given maze                              */
        int[] arrayDimensions = maze.getArrayDimensions();
        width = arrayDimensions[0];
        height = arrayDimensions[1];
        mazeToNodeArray = new Node[width][height];
        
        /* set the array in the given maze*/
        maze.setArray(mazeToNodeArray);
        
        /* Define "START" and "EXIT" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'START' and 'EXIT' Nodes are being identified...");
        //Obtain Start and Exit Nodes
        Node[] nodesStartExit = maze.getStartExitNodes();
        start = nodesStartExit[0];
        exit = nodesStartExit[1];
        LOG.info("'START' and 'EXIT' Nodes obtained.");
        /* End of Defining "START" and "EXIT" Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        /* Filling 'MAZE' Nodes into the Array ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'MAZE' Nodes are being filled into the array...");
        //Set the 'MAZE' Nodes in the Node[][]
        maze.setMazeNodes();
        LOG.info("'MAZE' Node Filling Completed.");
        /* End of filling 'MAZE Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
                
        /* Connecting 'START' and 'EXIT' adjacent nodes ~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'START' and 'EXIT' Nodes are being connected to Adjacent Nodes...");
        maze.connectEntranceExitAdjNodes(start, exit);
        LOG.info("'START' and 'EXIT' Nodes Connection Complete.");
        /* End of Connecting 'START' and 'EXIT' adjacent nodes ~~~~~~~~~~~~~~~*/
        
        /* Defining "MAZE" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("Connecting adjacent 'MAZE' Nodes...");
        maze.connectMAZEAdjacentNodes();
        LOG.info("Adjacent 'MAZE' Node Connection Complete.");
        /* End of Defining "MAZE" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~~~~*/
        
        LOG.info("Connection of ALL Nodes Complete.");
 
        /* DEBUG CODE: We will view mazeToNodeArray[][]: ~~~~~~~~~~~~~~~~~~~~~**
        ** Comment this out when done ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        //Binary representation of maze:
        /*for(int i = 0; i < mazeToNodeArray.length; i++){
            for(int j = 0; j < mazeToNodeArray[i].length; j++){
                if(mazeToNodeArray[j][i] == null){
                    System.out.print("0 ");
                }else{System.out.print("1 ");}
            }
            System.out.println();
        }*/
        
        //Display a given node's adjacent nodes
        
        /* END OF DEBUG CODE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        
        LOG.debug("Exiting Graph.createGraph()...");
        return new Node[] {start, exit};
    }
    
    public void drawMaze(){
        LOG.debug("Entering Graph.drawMaze()...");     
        
        System.out.println(exit.getPathLength());
        WritableRaster writableImage = maze.draw(exit);        
        /* Writing BufferedImage and Output file                              */
        try{
            BufferedImage imgSolution = new BufferedImage(colorModel, writableImage, true, null);
            File outputFile = new File("./src/res/Mazes/Rectangular/Solutions/" + solutionName + ".png");
            System.out.println(solutionName);
            ImageIO.write(imgSolution, "png", outputFile);
        }catch(Exception ex){
            LOG.error("Output Exception Error: " + ex.toString());
        }
        
        LOG.debug("Exiting Graph.drawMaze()...");
    }

    /* End of Object's functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
