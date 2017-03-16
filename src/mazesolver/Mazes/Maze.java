package mazesolver.Mazes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    BufferedImage img = null;
    String pathSolution;
    /* End of Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Maze(File imageFile, String pathSolution){
        LOG.debug("Entering Maze Class constructor...");
        
        //store the imageFile into a BufferedImage and the absolute path to put output
        this.pathSolution = pathSolution;
        try{
            img = ImageIO.read(imageFile);
            LOG.info("Image file has been initialized.");
        }catch(IOException ex){
            LOG.error("Error has occurred: " + ex.toString());
            LOG.info("Failed to initialize Image File.");
        }
        
        LOG.debug("Exiting Maze Class constructor...");
    }
    /* End of Constructor~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Universal Method inherited by Child Class ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void solve(/*Algorithm alg*/){
        LOG.debug("Entering Maze.solve()...");
        
        //solve Algorithm
        
        LOG.debug("Exiting Maze.solve()...");
    }
    /* End of Universal Method(s) inherited by Child Class ~~~~~~~~~~~~~~~~~~~*/
    
    /* Declare Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public abstract void toGraph();
    public abstract void draw();
    /* End of Declaring Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
