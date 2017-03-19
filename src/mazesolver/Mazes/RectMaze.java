package mazesolver.Mazes;

import java.awt.image.Raster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import mazesolver.Containers.Node;
import mazesolver.Containers.Type;

/**
 *
 * @author Andrew Hyun
 */
public class RectMaze extends Maze{
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(RectMaze.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int[] pixel;
    /* End of Definition of Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public RectMaze(Raster scannedImage){
        super(scannedImage);
        LOG.debug("Entering RectMaze Class Constructor...");
        
        LOG.info("Pixel width and height adjustment (if necessary) occurring...");
        /* The rectangular mazes created by the Daedalus program has an   **
        ** odd pixel width and height. This can be seen with an extra     **
        ** "white" line of pixels on the FINAL EVEN ROW and COLUMN. To    **
        ** account for this, I have put these adjustments:                */
        if(width % 2 == 0){width -= 1;}
        if(height % 2 == 0){height -= 1;}
        LOG.info("Adjusted Width is: " + width + " and Height is: " + height);
        
        /* Initialize variables                                               */
        pixel = new int[scannedImage.getNumBands()];
        
        LOG.debug("Exiting RectMaze Class Constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    @Override
    public int[] getArrayDimensions(){
        return new int[]{width, height};
    }
    
    @Override
    public Node[] getStartExitNodes() {
        LOG.debug("Entering RectMaze.getStartExitNodes()...");
        
        /* Declare local variables                                            */
        boolean startFound = false;
        Node[] nodesStartExit = new Node[2];
        
        //Define "Start" and "End" Nodes
        for(int row=0; row < height; row++){
            for(int col=0; col < width; col++){
                if(row == 0 || row == height-1){
                    scannedImage.getPixel(col, row, pixel);
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){   //If rgb == white, then we have a "start" or "exit"
                        if(startFound == false){
                            mazeToNodeArray[col][row] = new Node(Type.START, col, row);
                            nodesStartExit[0] = mazeToNodeArray[col][row];
                            startFound = true;
                        }else{
                            mazeToNodeArray[col][row] = new Node(Type.EXIT, col, row);
                            nodesStartExit[1] = mazeToNodeArray[col][row];
                        }
                    }
                }else{
                    if(col == 0 || col == width-1){
                        scannedImage.getPixel(col, row, pixel);
                        if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){   //If rgb == white, then we have a "start" or "exit"
                            if(startFound == false){
                                mazeToNodeArray[col][row] = new Node(Type.START, col, row);
                                nodesStartExit[0] = mazeToNodeArray[col][row];
                                startFound = true;
                            }else{
                                mazeToNodeArray[col][row] = new Node(Type.EXIT, col, row);
                                nodesStartExit[1] = mazeToNodeArray[col][row];
                            }
                        }
                    }
                }
            }
        }
        
        LOG.debug("Exiting RectMaze.getStartExitNodes()...");
        return nodesStartExit;
    }

    @Override
    public void setMazeNodes() {
        LOG.debug("Entering RectMaze.setMazeNodes()...");
        
        /* Declare local variables                                            */
        boolean upWhite, downWhite, leftWhite, rightWhite;
        int counter;

        for(int row = 1; row < height-1; row++){
            for(int col = 1; col < width-1; col++){
                scannedImage.getPixel(col, row, pixel);
                if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){      //If current pixel is white, check its neighbors to see if they're white
                    /* These boolean values and the counter will help determine if pixel is a node*/
                    upWhite = downWhite = leftWhite = rightWhite = false;
                    counter = 0;
                    
                    /* Checking to see if the pixel ABOVE is white ~~~~~~~~~~~*/
                    scannedImage.getPixel(col, row-1, pixel);                   //Get the samples in the pixel above the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel above is white, set upWhite boolean to true
                        upWhite = true;
                        counter++;
                    }
                    /* Checking to see if the pixel BELOW is white ~~~~~~~~~~~*/
                    scannedImage.getPixel(col, row+1, pixel);                   //Get the samples in the pixel below the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel below is white, set downWhite boolean to true
                        downWhite = true;
                        counter++;
                    }
                    /* Checking to see if the pixel to the LEFT is white ~~~~~*/
                    scannedImage.getPixel(col-1, row, pixel);                   //Get the samples in the pixel to the left of the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel to the left is white, set leftWhite boolean to true
                        leftWhite = true;
                        counter++;
                    }
                    /* Checking to see if the pixel to the RIGHT is white ~~~~*/
                    scannedImage.getPixel(col+1, row, pixel);                   //Get the samples in the pixel to the right of the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel to the right is white, set rightWhite boolean to true
                        rightWhite = true;
                        counter++;
                    }
                    if(counter != 2){
                        mazeToNodeArray[col][row] = new Node(Type.MAZE, col, row);        //If a given pixel has 0, 1, 3, or 4 "paths", then it HAS to be a node.
                    }else{                                                      //If a given pixel has 2 "paths" we have to check if they are a node position or not
                        if(leftWhite && rightWhite){}
                        else if(upWhite && downWhite){}
                        else{
                            mazeToNodeArray[col][row] = new Node(Type.MAZE, col, row);
                        }
                    }
                }
            }
        }
        
        LOG.debug("Exiting RectMaze.setMazeNodes()...");
    }

    @Override
    public void connectEntranceExitAdjNodes(Node start, Node exit) {
        LOG.debug("Entering RectMaze.connectEntranceExitAdjNodes()...");
        
        /* Define and Initialize Local Variables                              */
        int counter = 0;
        boolean upWhite, downWhite, leftWhite, rightWhite;
        Node[] nodeArray = new Node[2];
        nodeArray[0] = start;
        nodeArray[1] = exit;
        /* Connecting Node to its adjacent node                               */
        //If Node is found on the left side of maze
        for(Node node : nodeArray){
            if(node.getX() == 0){
                rightWhite = true;
                //Keep checking right pixel until adjacent node is found and added
                do{
                    if(rightWhite == true){
                        counter++;
                        if(mazeToNodeArray[node.getX()+counter][node.getY()] != null){
                            mazeToNodeArray[node.getX()][node.getY()].addAdjNode(mazeToNodeArray[node.getX()+counter][node.getY()]);
                            counter = 0;
                            rightWhite = false;
                        }
                    }
                }while(rightWhite == true);
            }
            else if(node.getX() == width-1){
                leftWhite = true;
                //Keep checking left pixel until adjacent node is found and added
                do{
                    if(leftWhite == true){
                        counter++;
                        if(mazeToNodeArray[node.getX()-counter][node.getY()] != null){
                            mazeToNodeArray[node.getX()][node.getY()].addAdjNode(mazeToNodeArray[node.getX()-counter][node.getY()]);
                            counter = 0;                            
                            leftWhite = false;
                        }
                    }
                }while(leftWhite == true);
            }else if(node.getY() == 0){
                downWhite = true;
                //Keep checking the pixel below until adjacent node is found and added
                do{
                    if(downWhite == true){
                        counter++;
                        if(mazeToNodeArray[node.getX()][node.getY()+counter] != null){
                            mazeToNodeArray[node.getX()][node.getY()].addAdjNode(mazeToNodeArray[node.getX()][node.getY()+counter]);
                            counter = 0;                           
                            downWhite = false;
                        }
                    }
                }while(downWhite == true);
            }else{
                upWhite = true;
                //Keep checking the pixel below until adjacent node is found and added
                do{
                    if(upWhite == true){
                        counter++;
                        if(mazeToNodeArray[node.getX()][node.getY()-counter] != null){
                            mazeToNodeArray[node.getX()][node.getY()].addAdjNode(mazeToNodeArray[node.getX()][node.getY()-counter]);
                            counter = 0;                            
                            upWhite = false;
                        }
                    }
                }while(upWhite == true);
            }
        }
        
        LOG.debug("Exiting RectMaze.connectEntranceExitAdjNodes()...");
    }

    @Override
    public void connectMAZEAdjacentNodes() {
        LOG.debug("Entering RectMaze.connectMAZEAdjacentNodes()...");
        
        /* Define Local Variables                                             */
        boolean upWhite, downWhite, leftWhite, rightWhite;
        int counter;
        
        for(int row = 1; row < height-1; row++){
            for(int col = 1; col < width-1; col++){
                if(mazeToNodeArray[col][row] != null){                          //If [x,y] of image contains a node look for neighboring nodes
                    /* These boolean values and counter will help find neighboring nodes   */
                    upWhite = downWhite = leftWhite = rightWhite = false;
                    counter = 0;
                    
                    /* Checking to see if the pixel ABOVE is white ~~~~~~~~~~~*/
                    scannedImage.getPixel(col, row-1, pixel);                   //Get the samples in the pixel above the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel above is white, set upWhite boolean to true
                        upWhite = true;
                    }
                    /* Checking to see if the pixel BELOW is white ~~~~~~~~~~~*/
                    scannedImage.getPixel(col, row+1, pixel);                   //Get the samples in the pixel below the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel below is white, set downWhite boolean to true
                        downWhite = true;
                    }
                    /* Checking to see if the pixel to the LEFT is white ~~~~~*/
                    scannedImage.getPixel(col-1, row, pixel);                   //Get the samples in the pixel to the left of the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel to the left is white, set leftWhite boolean to true
                        leftWhite = true;
                    }
                    /* Checking to see if the pixel to the RIGHT is white ~~~~*/
                    scannedImage.getPixel(col+1, row, pixel);                   //Get the samples in the pixel to the right of the current pixel
                    if(pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255){  //If the pixel to the right is white, set rightWhite boolean to true
                        rightWhite = true;
                    }
                    
                    /* Defining adjacency list for current Node ~~~~~~~~~~~~~~*/
                    
                    // If upWhite is true, add the adjacency Node above
                    do{
                        if(upWhite == true){
                            counter++;
                            if(mazeToNodeArray[col][row-counter] != null){
                                mazeToNodeArray[col][row].addAdjNode(mazeToNodeArray[col][row-counter]);
                                counter = 0;
                                upWhite = false;
                            }
                        }
                    }while(upWhite == true);
                    // If downWhite is true, add the adjacency Node below
                    do{
                        if(downWhite == true){
                            counter++;
                            if(mazeToNodeArray[col][row+counter] != null){
                                mazeToNodeArray[col][row].addAdjNode(mazeToNodeArray[col][row+counter]);
                                counter = 0;                                
                                downWhite = false;
                            }
                        }
                    }while(downWhite == true);
                    // If leftWhite is true, add the adjacency Node to the left
                    do{
                        if(leftWhite == true){
                            counter++;
                            if(mazeToNodeArray[col-counter][row] != null){
                                mazeToNodeArray[col][row].addAdjNode(mazeToNodeArray[col-counter][row]);
                                counter = 0;
                                leftWhite = false;
                            }
                        }
                    }while(leftWhite == true);
                    // If rightWhite is true, add the adjacency Node to the right
                    do{
                        if(rightWhite == true){
                            counter++;
                            if(mazeToNodeArray[col+counter][row] != null){
                                mazeToNodeArray[col][row].addAdjNode(mazeToNodeArray[col+counter][row]);
                                counter = 0;
                                rightWhite = false;
                            }
                        }
                    }while(rightWhite == true);
                }
            }
        }
        
        LOG.debug("Exiting RectMaze.connectMAZEAdjacentNodes...");
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
