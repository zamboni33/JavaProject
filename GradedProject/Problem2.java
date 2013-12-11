package GradedProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Problem2 {
    private Scanner taskTimesFile;
    private Scanner taskDependenciesFile;
    
    private ArrayList<Integer> weights;
    private ArrayList<Integer> dependLeft;
    private ArrayList<Integer> dependRight;
    private ArrayList<Integer> removedLeft;
    private ArrayList<Integer> removedRight;
    
    private ArrayList<Integer> listFinal;
    private ArrayList<Integer> dayAmount;
    private Queue<Integer> S;
    
    // Returns
    private Queue<Integer> sourceNodes;
    private int dayTotal;
    private ArrayList<Integer> lastNodes;
    
    
    public Problem2(String taskTimes, String taskDependencies) {
        File f = new File(taskTimes);
        try {
            taskTimesFile = new Scanner(f);   
            
            //Reading in node weights
            weights = new ArrayList<Integer>();
            weights.add(0);
            StringTokenizer weightInput = new StringTokenizer(taskTimesFile.nextLine());
            while(weightInput.hasMoreTokens()){
            	int weight = Integer.parseInt(weightInput.nextToken(" "));
            	weights.add(weight);
            }
            
        } catch (FileNotFoundException e) {
            System.err.printf("Could not find <task-times-file> (%s)\n", f.toString());
            System.exit(1);
        }
        
        File g = new File(taskDependencies);
        try {
            taskDependenciesFile = new Scanner(g);
            
            // Reading in Dependencies
            dependLeft = new ArrayList<Integer>();
            dependRight = new ArrayList<Integer>();
            removedLeft = new ArrayList<Integer>();
            removedRight = new ArrayList<Integer>();
            
            while(taskDependenciesFile.hasNextLine()){
		    	StringTokenizer edge = new StringTokenizer(taskDependenciesFile.nextLine());
		    	int left = Integer.parseInt(edge.nextToken(" "));
		    	int right = Integer.parseInt(edge.nextToken(" "));
		    	dependLeft.add(left);
		    	dependRight.add(right);
		    }
            
            
            
        } catch (FileNotFoundException e) {
            System.err.printf("Could not find <task-dependencies-file> (%s)\n", g.toString());
            System.exit(1);
        }
    }

    public void solve() {

        ArrayList<Integer> listFinal = new ArrayList<Integer>();
        ArrayList<Integer> dayAmount = new ArrayList<Integer>();
        Queue<Integer> S = new LinkedList<Integer>();
        Queue<Integer> sourceNodes = new LinkedList<Integer>();
        HashMap<Integer, ArrayList<Integer>> children = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> parents = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> tempArray;
        ArrayList<Integer> tempArray2;
        ArrayList<Integer> done;
        
        // Initializing day length Array
        for(int i = 0; i < weights.size(); i += 1){
        	dayAmount.add(0);
        }
    	
    	// Adding the indices of source nodes
        for(int i = 1; i < weights.size(); i += 1){
    		if(!dependRight.contains(i)) {
    			// Source Node
    			// Update dayAmount
    			dayAmount.set(i, weights.get(i));
    			
    			S.add(i);
    			sourceNodes.add(i);
    			
    			
    			tempArray = new ArrayList<Integer>();
    			for(int j = 0; j < dependLeft.size(); j += 1){
    				if(i == dependLeft.get(j)) {
    					tempArray.add(dependRight.get(j));
    				}
    			}
    			children.put(i, tempArray);
    		}
    		else{
    			tempArray = new ArrayList<Integer>();
    			for(int j = 0; j < dependLeft.size(); j += 1){
    				if(i == dependLeft.get(j)) {
    					tempArray.add(dependRight.get(j));
    				}
    			}
    			children.put(i, tempArray);
    		}
	    		// Creating map of reverse dependencies
	    		tempArray2 = new ArrayList<Integer>();
	    		for(int j = 0; j < dependRight.size(); j += 1){
	    			if(i == dependRight.get(j)){
	    				tempArray2.add(dependLeft.get(j));
	    			}
	    		}
	    		parents.put(i, tempArray2);
    		
    	}
        
        
//        System.out.println(S);
//        System.out.println();
//        for(int key:children.keySet()){
//        	System.out.println(children.get(key));
//        }
//        System.out.println();
//        for(int key:parents.keySet()){
//        	System.out.println(key + "  " + parents.get(key));
//        }
    	
        
        // Beginning topological sort
        int dayIndex = 0;
//        done = new ArrayList<Integer>();
        while(!S.isEmpty()){
        	// Adding retired node to final list.
        	int currentNode = S.remove();
        	listFinal.add(currentNode);
        	boolean retire = true;
        	
        	int childToRetire = 0;
        	for(int child: children.get(currentNode)){
        		childToRetire = child;
        		for(int parent: parents.get(childToRetire)){
        			if(!listFinal.contains(parent)){
        				retire = false;
        			}
        		}
        		if(retire){
            		int max = 0;
                	for(int parent: parents.get(childToRetire)){
                		if(weights.get(parent) > max) {
                			max = weights.get(parent);
                		}
                	}
                	if(!S.contains(childToRetire)){
                		S.add(childToRetire);
                	}
                	// Update the max wait time for this node.
                	weights.set(childToRetire, max + weights.get(childToRetire));
                	dayAmount.set(childToRetire, weights.get(childToRetire));
            	}
        	}
        	
        	       	
        }
        
        lastNodes = new ArrayList<Integer>();
        dayTotal = Collections.max(dayAmount);
        for(int i = 0; i < weights.size(); i += 1){
        	if(dayTotal == weights.get(i)) {
        		lastNodes.add(i);
        	}
        }
//        System.out.println(dayAmount);
//        System.out.println();
//        System.out.println(Collections.max(dayAmount));
//        System.out.println();
        System.out.println("Start nodes: " + sourceNodes);
        System.out.println("Time taken: " + dayAmount);
        System.out.println("Final nodes: " + lastNodes);
    }
}

//Push every incoming number into a set 
//Push every second number into a different set
//Take the set difference and that is our set of source nodes


//
//L ← Empty list that will contain the sorted elements
//S ← Set of all nodes with no incoming edges
//Q <- Empty list size(nodes) initialized to 0
//	save a copy of S for returns
//while S is non-empty do
//  remove a node n from S
//  insert n into L
//  for each node m with an edge e from n to m do
//      remove edge e from the graph
//		  if(edge > Q[index(A)]) Q[index(A)] = edge weight
//      if m has no other incoming edges then
//          insert m into S
//if graph has edges then
//  return error (graph has at least one cycle)
//else 
//  return L (a topologically sorted order)
