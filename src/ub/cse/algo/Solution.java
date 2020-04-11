package ub.cse.algo;

import java.util.*;

public class Solution {
    
    private int _startNode;
    private int _endNode;
    private HashMap<Integer, ArrayList<Integer>> graph;
    public Solution(int startNode, int endNode, HashMap<Integer, ArrayList<Integer>> g){
        _startNode = startNode;
        _endNode = endNode;
        graph = g;
    }
    
    public ArrayList<Integer> outputPath(){
        /*
         * Find the smallest weighted path between _startNode and _endNode
         * The first number of graph's adjacency list is the weight of it's node
         */
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<>();
        int base = 0;
        Integer[] distance = new Integer[graph.size()];
        for (int i = 0; i < graph.size(); i++){
            distance[i] = 2147483647;
        }
        s.add(_startNode);
        distance[_startNode] = 0;
        System.out.println(Arrays.toString(distance));
        PriorityQueue<Integer>touched = new PriorityQueue<>(Comparator.comparingInt(node -> distance[node]));
        for (int i = 1; i < graph.get(_startNode).size(); i++){
            distance[graph.get(_startNode).get(i)] =  graph.get(graph.get(_startNode).get(i)).get(0);
            touched.add(graph.get(_startNode).get(i));
        }
        System.out.println(touched);
        while (touched.size() != 0){
            for (int i = 1; i < graph.get(touched.peek()).size(); i++){
                int node = graph.get(touched.peek()).get(i);
                int dis = base + graph.get(graph.get(touched.peek()).get(i)).get(0);
                if(dis < distance[node]){
                    distance[node] = dis;
                    touched.add(node);
                }
            }
            base = distance[touched.peek()];
            s.add(touched.poll());
        }
        System.out.println(base);
        if (!s.contains(_endNode)){
            return path;
        }
        else return s;

    }
}

