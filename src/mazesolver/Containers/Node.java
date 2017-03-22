/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Containers;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class Node {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(Node.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/   
    
    private final Type type;
    private boolean searched;
    private ArrayList<Node> adjacentNodeList;
    private Node parentNode = null;
    private int x, y, pathLength;
    
    /*Start of Constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Node(Type type){
        LOG.debug("Entering Node Class constructor...");
        
        this.type = type;
        searched = false;
        adjacentNodeList = new ArrayList<Node>();
        
        LOG.debug("Exiting Node Class constructor...");
    }
    
    public Node(Type type, int x, int y){
        LOG.debug("Entering Node Class constructor...");
        
        this.type = type;
        searched = false;
        adjacentNodeList = new ArrayList<Node>();
        this.x = x;
        this.y = y;
        
        LOG.debug("Exiting Node Class constructor...");
    }
    /*End of Constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Setters and Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* NOTE: It is not necessary to create a "setType", "setX", or "setY" for **
    ** a node because it is a value that should not be tampered with --       **
    ** unique to given node.                                                  */
    public Type getType(){
        return type;      
    }
    
    public boolean getVisited(){
        return searched;
    }
    
    public void setParentNode(Node node){
        LOG.debug("Entering Node.setParentNode() method...");
        parentNode = node;
        LOG.debug("Exiting Node.setParentNode() method...");
    }
    
    public Node getParentNode(){
        return parentNode;
    }
    
    public Iterator getAdjacentNodeIterator(){
        return adjacentNodeList.iterator();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setPathLength(int pathLength){
        this.pathLength=pathLength;
    }
    
    public int getPathLength(){
        return pathLength;
    }
    /* End of Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /* NOTE: This is not a SETTER because it is a one time call method that   **
    ** will switch the boolean value to true ONCE.                            */
        public void visited(){
            LOG.debug("Entering Node.visited() method...");
            searched = true;
            LOG.debug("Exiting Node.visited() method...");
        }
        
        public void addAdjNode(Node node){
            LOG.debug("Entering Node.addAdjNode() method...");
            adjacentNodeList.add(node);
            LOG.debug("Exiting Node.addAdjNode() method...");
        }
    /* End of Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

}
