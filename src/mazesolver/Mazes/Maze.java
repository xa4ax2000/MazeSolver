package mazesolver.Mazes;

import java.awt.image.Raster;
import mazesolver.Containers.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public abstract class Maze {
    
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    Logger LOG = LogManager.getLogger(Maze.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/

    /* Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Node[][] mazeToNodeArray;
    final Raster scannedImage;
    int width, height;
    /* End of Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Maze(Raster scannedImage){
        LOG.debug("Entering Maze Class constructor...");
        
        //Initialize Variables
        this.scannedImage = scannedImage;
        width = scannedImage.getWidth();
        height = scannedImage.getHeight();
        
        LOG.debug("Exiting Maze Class constructor...");
    }
    /* End of Constructor~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setArray(Node[][] mazeToNodeArray){
        this.mazeToNodeArray = mazeToNodeArray;
    }
    
    /* Declare Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public abstract int[] getArrayDimensions();
    public abstract Node[] getStartExitNodes();
    public abstract void setMazeNodes();
    public abstract void connectEntranceExitAdjNodes(Node start, Node exit);
    public abstract void connectMAZEAdjacentNodes();
    public abstract void draw();
    /* End of Declaring Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
