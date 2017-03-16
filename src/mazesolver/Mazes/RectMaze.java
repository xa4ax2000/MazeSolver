package mazesolver.Mazes;

import java.awt.image.Raster;
import java.io.File;
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

    @Override
    public void toGraph() {
        LOG.debug("Entering RectMaze.toGraph()...");
        
        
        
        LOG.debug("Exiting RectMaze.toGraph()...");
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
