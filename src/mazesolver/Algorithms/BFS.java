/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Algorithms;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import mazesolver.Containers.Node;
import mazesolver.Containers.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class BFS extends Algorithm {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(BFS.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private Queue<Node> q;
        private Node start, exit;
    /* End of Class Variable Definition ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BFS(Node start, Node exit){
        super(start, exit);
        LOG.debug("Entering BFS Class constructor...");
        
        this.start = start;
        this.exit = exit;
        
        //initialize stack and add start node
        q = new ConcurrentLinkedQueue<Node>();
        q.add(start);
        
        LOG.debug("Exiting BFS Class constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void findSolution() {
        LOG.debug("Entering BFS.findSolution() method...");
        
        LOG.info("starting BFS algorithm -- Timer started...");
        startTime = System.currentTimeMillis();
        
        //Define Local Variables
        Node currNode = null;
        
        //While the Queue is not empty...
        while(!q.isEmpty()){   
            currNode = q.remove();                                              //Remove node on stack and set to visited
            currNode.visited();
            if(currNode.getType() == Type.EXIT){                                //If the node is the exit node,  break
                break;
            }else{
                Iterator iterator = currNode.getAdjacentNodeIterator();         //get the AdjacentNodeIterator and add everything to queue
                while(iterator.hasNext()){
                    Node tempNode = (Node)iterator.next();
                    if(!tempNode.getVisited()){
                        tempNode.setParentNode(currNode);
                        q.add(tempNode);
                    }
                }
            }
        }
        
        stopTime = System.currentTimeMillis();
        LOG.info("BFS algorithm complete. Timer stopped.");
        
        LOG.debug("Exiting BFS.findSolution() method...");
    }
    /* End of Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
