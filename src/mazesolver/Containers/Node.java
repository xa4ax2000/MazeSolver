/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Containers;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Andrew Hyun
 */
public class Node {
    
    private final Type type;
    private boolean searched;
    private ArrayList<Node> adjacentNodeList;
    private Node parentNode = null;
    private int x, y;
    
    /*Start of Constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Node(Type type){
        this.type = type;
        searched = false;
        adjacentNodeList = new ArrayList<Node>();
    }
    
    public Node(Type type, int x, int y){
        this.type = type;
        searched = false;
        adjacentNodeList = new ArrayList<Node>();
        this.x = x;
        this.y = y;
    }
    /*End of Constructors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Setters and Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* NOTE: It is not necessary to create a "setType", "setX", or "setY" for **
    ** a node because it is a value that should not be tampered with --       **
    ** unique to given node.                                                  */
    public Type getType(){
        return type;
    }
    
    public boolean getSearched(){
        return searched;
    }
    
    public void setParentNode(Node node){
        parentNode = node;
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
    /* End of Getters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /* NOTE: This is not a SETTER because it is a one time call method that   **
    ** will switch the boolean value to true ONCE.                            */
        public void searched(){
            searched = true;
        }
        
        public void addAdjNode(Node node){
            adjacentNodeList.add(node);
        }
    /* End of Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

}
