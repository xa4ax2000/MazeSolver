/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Containers;

import java.awt.image.Raster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class Graph {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(Graph.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private final int width, height;
    private Node[][] mazeToNodeArray;
    private final Raster scannedImage;
    private int[] pixel;
    /* End of Definition of Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Graph(Node[][] mazeToNodeArray, Raster scannedImage, int width, int height){
        LOG.debug("Entering Graph Constructor...");
        
        this.mazeToNodeArray = mazeToNodeArray;
        this.scannedImage = scannedImage;
        this.width = width;
        this.height = height;
        pixel = new int[scannedImage.getNumBands()];
        
        LOG.debug("Entering Graph Constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Node[] getStartExitNodes(){
        LOG.debug("Entering getStartExitNodes...");
        
        /* Declare local variables                                            */
        boolean startFound = false;
        Node[] nodesStartExit = new Node[2];
        
        //Define "Start" and "End" Nodes
        LOG.info("Traversing outer edge of PNG, Defining Start and End Nodes...");
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
        LOG.info("Outer Edge Traversal Complete.");
        
        LOG.debug("Exiting getStartExitNodes...");
        return nodesStartExit;
    }
    
    public void setMazeNodes(){
        LOG.debug("Entering setMazeNodes...");
        
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
                        mazeToNodeArray[col][row] = new Node(Type.MAZE);        //If a given pixel has 0, 1, 3, or 4 "paths", then it HAS to be a node.
                    }else{                                                      //If a given pixel has 2 "paths" we have to check if they are a node position or not
                        if(leftWhite && rightWhite){}
                        else if(upWhite && downWhite){}
                        else{
                            mazeToNodeArray[col][row] = new Node(Type.MAZE);
                        }
                    }
                }
            }
        }
        
        LOG.debug("Exiting setMazeNodes...");
    }
    
    public void connectEntranceExitAdjNodes(Node start, Node exit){
        LOG.debug("Entering connectEntranceExitAdjNodes...");
        
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
                        LOG.debug("rightWhite counter == " + counter);
                        if(mazeToNodeArray[node.getX()+counter][node.getY()] != null){
                            LOG.debug("adding adj node at: " + (node.getX()+counter) + ", " + node.getY() + ").");
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
                        LOG.debug("leftWhite counter == " + counter);
                        if(mazeToNodeArray[node.getX()-counter][node.getY()] != null){
                            LOG.debug("adding adj node at: " + (node.getX()-counter) + ", " + node.getY() + ").");
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
                        LOG.debug("downWhite counter == " + counter);
                        if(mazeToNodeArray[node.getX()][node.getY()+counter] != null){
                            LOG.debug("adding adj node at: " + node.getX() + ", " + (node.getY()+counter) + ").");
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
                        LOG.debug("upWhite counter == " + counter);
                        if(mazeToNodeArray[node.getX()][node.getY()-counter] != null){
                            LOG.debug("adding adj node at: " + node.getX() + ", " + (node.getY()-counter) + ").");
                            mazeToNodeArray[node.getX()][node.getY()].addAdjNode(mazeToNodeArray[node.getX()][node.getY()-counter]);
                            counter = 0;                            
                            downWhite = false;
                        }
                    }
                }while(upWhite == true);
            }
        }
        
        LOG.debug("Exiting connectEntranceExitAdjNodes...");
    }
    
    public void connectMAZEAdjacentNodes(){
        LOG.debug("Entering connectMAZEAdjacentNodes...");
        
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
                    
                    LOG.debug("Adding adjacent Nodes for 'MAZE' Nodes...");
                    
                    /* Defining adjacency list for current Node ~~~~~~~~~~~~~~*/
                    
                    // If upWhite is true, add the adjacency Node above
                    do{
                        if(upWhite == true){
                            counter++;
                            LOG.debug("upWhite counter == " + counter);
                            if(mazeToNodeArray[col][row-counter] != null){
                                LOG.debug("adding adj node at: " + col + ", " + (row-counter) + ").");
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
                            LOG.debug("downWhite counter == " + counter);
                            if(mazeToNodeArray[col][row+counter] != null){
                                LOG.debug("adding adj node at: " + col + ", " + (row+counter) + ").");
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
                            LOG.debug("leftWhite counter == " + counter);
                            if(mazeToNodeArray[col-counter][row] != null){
                                LOG.debug("adding adj node at: " + (col-counter) + ", " + row + ").");
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
                            LOG.debug("rightWhite counter == " + counter);
                            if(mazeToNodeArray[col+counter][row] != null){
                                LOG.debug("adding adj node at: " + (col+counter) + ", " + row + ").");
                                mazeToNodeArray[col][row].addAdjNode(mazeToNodeArray[col+counter][row]);
                                counter = 0;
                                rightWhite = false;
                            }
                        }
                    }while(rightWhite == true);
                }
            }
        }
        
        LOG.debug("Exiting connectMAZEAdjacentNodes...");
    }
    /* End of Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
