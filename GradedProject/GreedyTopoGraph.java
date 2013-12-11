package GradedProject;

public class GreedyTopoGraph {

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
//    remove a node n from S
//    insert n into L
//    for each node m with an edge e from n to m do
//        remove edge e from the graph
//		  if(edge > Q[index(A)]) Q[index(A)] = edge weight
//        if m has no other incoming edges then
//            insert m into S
//if graph has edges then
//    return error (graph has at least one cycle)
//else 
//    return L (a topologically sorted order)