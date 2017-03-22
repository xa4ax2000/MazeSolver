/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Algorithms;

import java.util.Iterator;
import java.util.Stack;
import mazesolver.Containers.Node;
import mazesolver.Containers.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class DFS extends Algorithm{
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(DFS.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private int pathLength;
        private Stack s;
    /* End of Class Variable Definition ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Class Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public DFS(Node start, Node exit){
        super(start, exit);
        LOG.debug("Entering DFS Class Constructor...");
        
        pathLength = 1;
        //initialize stack and add start node
        s = new Stack();
        start.setPathLength(pathLength);
        s.add(start);
        
        LOG.debug("Exiting DFS Class Constructor...");
    }
    /* End of Class Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void findSolution() {
        LOG.debug("Entering DFS.findSolution() method...");
        
        //Start timer
        LOG.info("starting DFS algorithm -- Timer started...");
        startTime = System.currentTimeMillis();
        
        //Define Local Variables
        Node currNode;
        
        //While the Stack is not empty
        while(!s.empty()){                                                         
            currNode = (Node)s.pop();                                           
            currNode.visited();
            if(currNode.getType() == Type.EXIT){
                break;
            }else{
                Iterator iterator = currNode.getAdjacentNodeIterator();

                while(iterator.hasNext()){
                    Node adjNode = (Node)iterator.next();
                    if(!adjNode.getVisited()){
                        adjNode.setParentNode(currNode);
                        adjNode.setPathLength(currNode.getPathLength()+1);
                        s.add(adjNode);
                    }
                }
            }
        }
        
        //Stop Timer
        stopTime = System.currentTimeMillis();
        LOG.info("DFS algorithm complete. Timer stopped.");
        LOG.debug("Exiting DFS.findSolution() method...");
    }
    /* End of Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
