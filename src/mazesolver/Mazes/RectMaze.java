package mazesolver.Mazes;

import java.awt.image.Raster;
import java.io.File;
import mazesolver.Containers.Graph;
import mazesolver.Containers.Node;
import mazesolver.Containers.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class RectMaze extends Maze{
    
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    Logger LOG = LogManager.getLogger(RectMaze.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define variables/containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private static int width, height;
        private static Raster scannedImage;
        private Node[][] mazeToNodeArray;
        private Node start, exit;
    //  Graph imgGraph;
    /* End of defining variables/containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public RectMaze(File imageFile, String pathSolution){
        super(imageFile, pathSolution);
        LOG.debug("Entering RectMaze Constructor...");
        
        if(this.img != null){
            scannedImage = img.getData();
            width = scannedImage.getWidth();
            height = scannedImage.getHeight();
            
            LOG.info("Pixel width and height adjustment (if necessary) occurring...");
            /* The rectangular mazes created by the Daedalus program has an   **
            ** odd pixel width and height. This can be seen with an extra     **
            ** "white" line of pixels on the FINAL EVEN ROW and COLUMN. To    **
            ** account for this, I have put these adjustments:                */
            if(width % 2 == 0){width -= 1;}
            if(height % 2 == 0){height -= 1;}
            LOG.info("Adjusted Width is: " + width + " and Height is: " + height);
        }else{
            LOG.info("Failed to initialize Image File.");
        }
        
        LOG.debug("Exiting RectMaze Constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* Start of Object's functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void toGraph() {
        LOG.debug("Entering RectMaze.toGraph()...");
        
        /* Initialize an array to hold Nodes or null values                   */
        mazeToNodeArray = new Node[width][height];
        
        /* Initialize Graph to create Graph                                   */
        Graph graph = new Graph(mazeToNodeArray, scannedImage, width, height);
        
        /* Define "START" and "EXIT" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'START' and 'EXIT' Nodes are being identified...");
        //Obtain Start and Exit Nodes
        Node[] nodesStartExit = graph.getStartExitNodes();
        start = nodesStartExit[0];
        exit = nodesStartExit[1];
        LOG.info("'START' and 'EXIT' Nodes obtained.");
        /* End of Defining "START" and "EXIT" Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        /* Filling 'MAZE' Nodes into the Array ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'MAZE' Nodes are being filled into the array...");
        //Set the 'MAZE' Nodes in the Node[][]
        graph.setMazeNodes();
        LOG.info("'MAZE' Node Filling Completed.");
        /* End of filling 'MAZE Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
                
        /* Connecting 'START' and 'EXIT' adjacent nodes ~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("'START' and 'EXIT' Nodes are being connected to Adjacent Nodes...");
        graph.connectEntranceExitAdjNodes(start, exit);
        LOG.info("'START' and 'EXIT' Nodes Connection Complete.");
        /* End of Connecting 'START' and 'EXIT' adjacent nodes ~~~~~~~~~~~~~~~*/
        
        /* Defining "MAZE" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        LOG.info("Connecting adjacent 'MAZE' Nodes...");
        graph.connectMAZEAdjacentNodes();
        LOG.info("Adjacent 'MAZE' Node Connection Complete.");
        /* End of Defining "MAZE" Node's adjacent Nodes ~~~~~~~~~~~~~~~~~~~~~~*/
        
        LOG.info("Connection of ALL Nodes Complete.");
 
        /* DEBUG CODE: We will view mazeToNodeArray[][]: ~~~~~~~~~~~~~~~~~~~~~**
        ** Comment this out when done ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        //Binary representation of maze:
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(mazeToNodeArray[j][i] == null){
                    System.out.print("0 ");
                }else{System.out.print("1 ");}
            }
            System.out.println();
        }
        
        //Display a given node's adjacent nodes
        
        /* END OF DEBUG CODE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        
        LOG.debug("Exiting RectMaze.toGraph()...");
    }

    @Override
    public void draw() {

    }
    /* End of Object's functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
