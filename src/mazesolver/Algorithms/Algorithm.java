/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.Algorithms;

import mazesolver.Containers.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public abstract class Algorithm {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    Logger LOG = LogManager.getLogger(Algorithm.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Define Class Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Node start, exit;
    long startTime, stopTime;
    int debugCount = 0;
    /* End of Class Variable Definition ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Start of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Algorithm(Node start, Node exit){
        LOG.debug("Entering Algorithm Class constructor...");
        
        this.start = start;
        this.exit = exit;
        
        LOG.debug("Exiting Algorithm Class constructor...");
    }
    /* End of Constructor ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* Universal Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public double getTime(){
            return (double)(stopTime-startTime)/1000;
        }
        
        /* DEBUG METHOD: This method is to determine the number of nodes in   **
        ** the solution pathway. Comment it out after usage...                */
        /*public void debugNodes(Node node){
            debugCount++;
            System.out.println(debugCount + ": " + node.getX() + ", " + node.getY());
            if(node.getParentNode()!= null){
                debugNodes(node.getParentNode());
            }else{
                System.out.println("Total Number of nodes: " + debugCount);
            }
        }*/
    /* End of Universal Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public abstract void findSolution();
    /* End of Abstract Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
