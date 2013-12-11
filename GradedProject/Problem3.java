package GradedProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem3 {
    private int time;
    private Scanner cleanupFile;
    
    private Set<p3Node> nodes;
    ArrayList<Integer> costs;
    ArrayList<Integer> benefits;
    private Set<p3Edge> edges;
    ArrayList<Integer> edgeLeft;
    ArrayList<Integer> edgeRight;
    
    private int numberOfNodes;
    
    private ArrayList<ArrayList<Integer>> ajacMatrix;
    private ArrayList<ArrayList<Integer>> ajacMatrixParent;
	private ArrayList<ArrayList<Boolean>> ajacMatrixBool;
	private ArrayList<ArrayList<Integer>> ajacResult;
    
    public Problem3(String hours, String cleanup) {
//        if( Integer.parseInt(hours) == 0 ){
//        	time = 0;
//        }
//        else {
        	time = Integer.parseInt(hours);
//        }
        File f = new File(cleanup);
        try {
            cleanupFile = new Scanner(f);
            
            // Read in all first row integers. These are costs associated with a robot.

     // Reading in costs/benefits
            nodes = new LinkedHashSet<p3Node>();
            costs = new ArrayList<Integer>();
            costs.add(0);
            benefits = new ArrayList<Integer>();
            benefits.add(0);
            
            StringTokenizer costInput = new StringTokenizer(cleanupFile.nextLine());
            StringTokenizer benefitInput = new StringTokenizer(cleanupFile.nextLine());
            while(costInput.hasMoreTokens()){
            	int cost = Integer.parseInt(costInput.nextToken(" "));
            	int benefit = Integer.parseInt(benefitInput.nextToken(" "));
            	costs.add(cost);
            	benefits.add(benefit);
            	p3Node aNode = new p3Node( cost, benefit );
            	nodes.add(aNode);
            }

     
     // Reading in Edge Connections
		    edges = new LinkedHashSet<p3Edge>();
		    edgeLeft = new ArrayList<Integer>();
		    edgeRight = new ArrayList<Integer>();
            while(cleanupFile.hasNextLine()){
		    	StringTokenizer edge = new StringTokenizer(cleanupFile.nextLine());
		    	int left = Integer.parseInt(edge.nextToken(" "));
		    	int right = Integer.parseInt(edge.nextToken(" "));
		    	p3Edge aEdge = new p3Edge( left, right );
		    	edgeLeft.add(left);
		    	edgeRight.add(right);
		    	edges.add(aEdge);
		    }
		    
//             System.out.println(nodes.toString());
//             System.out.println(edges.toString());

		    
		    numberOfNodes = nodes.size();
            

        } catch (FileNotFoundException e) {
            System.err.printf("Could not find <cleanup-file> (%s)\n", f.toString());
            System.exit(1);
        }
    }

    public void solve() {

    	// Solving knapsack problem by looping over nodes and time length
    	// Building 2-D Matrix time vs. node
    	ajacMatrix = new ArrayList<ArrayList<Integer>>();
    	ajacMatrixParent = new ArrayList<ArrayList<Integer>>();
    	ajacMatrixBool = new ArrayList<ArrayList<Boolean>>();
    	ajacResult = new ArrayList<ArrayList<Integer>>();
    	
    	// Build the boolean matrix of connections
    	for(int i = 0; i <= numberOfNodes; i += 1) {
    		ajacMatrixBool.add(new ArrayList<Boolean>());
    		ajacResult.add(new ArrayList<Integer>());
    		for(int j = 0; j <= numberOfNodes; j += 1) {
    			ajacMatrixBool.get(i).add(false);
    		}
    	}
    	
    	for(int i = 0; i < edgeLeft.size(); i += 1) {
    		ajacMatrixBool.get(edgeLeft.get(i)).set(edgeRight.get(i), true);
    		ajacMatrixBool.get(edgeRight.get(i)).set(edgeLeft.get(i), true);
    	}
    	
    	for(int i = 0; i < numberOfNodes; i += 1) {
//    		System.out.println(ajacMatrixBool.get(i));	
    	}
    	

    	// Build the adjac Matrix
    	for(int i = 0; i <= time; i += 1) {
    		ajacMatrix.add(new ArrayList<Integer>());
    		ajacMatrixParent.add(new ArrayList<Integer>());
    		for(int j = 0; j <= numberOfNodes; j += 1) {
	    		// First Time through. INitialize 0 connections.
    			if(i == 0 || j == 0){
    				ajacMatrix.get(i).add(0);
    				ajacMatrixParent.get(i).add(0);	
    			}
    			
    			else if(i == 1) {
	    			if( ajacMatrixBool.get(i - 1).get(j) ){
	    				ajacMatrix.get(i).add(benefits.get(j));
	    				ajacMatrixParent.get(i).add(0);	
//	    				ajacResult.get(j).add(j);
	    			}
	    			else {
	    				ajacMatrix.get(i).add(0);
	    				ajacMatrixParent.get(i).add(0);	
	    			}
	    				
	    		}
    			else {
	    					
    				// Now checking past entries connected and taking max
					// How do I keep a running total here as Im checking for links?
    	    		int max = 0;
    	    		int parentNode = 0;
    	    		boolean benefitFlag = false;
    				for(int k = 1; k <= numberOfNodes; k += 1) {
    	    			if( ajacMatrixBool.get(j).get(k)) {
    	    				if( ajacMatrix.get(i - 1).get(k) > max ) {
    	    					benefitFlag = true;
    	    					max = ajacMatrix.get(i - 1).get(k);
    	    					parentNode = k;
    	    				}
    	    			}
    	    		}
    				if(benefitFlag) {
    					max += benefits.get(j); 
//    					ajacResult.get(j).add(j);
    				}
    				ajacMatrix.get(i).add(max);
    				ajacMatrixParent.get(i).add(parentNode);
				
    				
    			}
    		}
//    		System.out.println(ajacMatrix.get(i));
    	}
    	
//    	System.out.println("Final benefit values:");
//    	System.out.println(ajacMatrix.get(time));
//    	System.out.println();
//    	System.out.println("Predecessor Matrix:");
//    	
    	for(int i = 1; i <= time; i += 1){
//    		System.out.println(ajacMatrixParent.get(i));
    	}
    	
    	for(int i = 1; i <= numberOfNodes; i += 1){
    		int temp = ajacMatrix.get(time).get(i);
    		ajacMatrix.get(time).set(i, temp - costs.get(i) ); 
//    		System.out.println(ajacMatrix.get(time));
//    		System.out.println(ajacResult.get(i));
    	}

//    	System.out.println();
//    	System.out.println("After subtraction:");
//		System.out.println(ajacMatrix.get(time));
    	
		// Setting up the reverse path
		Stack<Integer> path = new Stack<Integer>();
 
	    int maxFinal = 0;
	    int maxIndex = 0;
    	for(int j = 0; j < ajacMatrix.get(time).size(); j += 1){
	    	if(ajacMatrix.get(time).get(j) >= maxFinal){
	    		maxIndex = j;
	    	}
	    }
	    path.push(maxIndex);
	    
	    for(int i = time; i > 0; i -= 1){
	    	maxIndex = ajacMatrixParent.get(i).get(maxIndex);
	    	path.push(maxIndex);
	    }
		
	    
//		System.out.println(path);
	    if(path.peek() == 0){
	    	path.pop();
	    }
		while(!path.isEmpty()){
			System.out.print(path.pop() + " ");
		}
		
    }
}


// Construct graph with nodes represented as (cost, benefit)
// All edge weights are initialized to 1
// Traverse the matrix column by column seeing if there is a connection between two nodes that has a max benefit
// Update matrix paths with highest benefit count
// THe total benefits are in the last column of the matrix at the end
// Subtract that cost from the total and choose the max.

// Construct the PI matrix at the same time in order to traverse at the end