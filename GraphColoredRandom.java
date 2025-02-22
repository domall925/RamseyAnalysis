/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Represents an undirected unweighted colored complete graph, colored at random with c colors and n nodes. 
 */

package graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GraphColoredRandom {
	private int[][] graph;
	private int n;
	private int c; // stores the number of colors
	
	public GraphColoredRandom(int n, int c) {
		if(n < 3) {
			throw new IllegalArgumentException("Need n > 2 to make a graph");
		}
		
		if(c < 2) {
			throw new IllegalArgumentException("Need c >= 2");
		}
		
		this.n = n;
		this.c = c;
		initializeGraph();
	}
	
	public GraphColoredRandom(int n) {
		this(n,2);
	}
	
	private void initializeGraph() {
		graph = new int[n-1][];
		
		for(int i = 0; i < n - 1; i++) {
			graph[i] = new int[n - i - 1];
		}
		
		Random random = new Random();
		
		for(int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph[i].length; j++) {
				graph[i][j] = random.nextInt(c);
			}
		}
	}
	
	public boolean containsMonoK(int k) {
		if(k < 3) {
			throw new IllegalArgumentException("containsMonoK requires k > 2");
		}
		
		//need to check all possible values of n choose k
		int[] initNodes = new int[k];
		for(int i = 0; i < k; i++) {
			initNodes[i] = i;
		}
		
		return recurs(initNodes, 0, k);
	}
	
	public boolean containsRainbowK(int k) {
		if(c < k * (k - 1) / 2) {
			throw new IllegalArgumentException("Must have at least k choose 2 colors");
		}
		
		int[] initNodes = new int[k];
		for(int i = 0; i < k; i++) {
			initNodes[i] = i;
		}
		
		return recursRainbow(initNodes, 0, k);
	}
	
	public int countMonoK(int k) {
		if(k < 3) {
			throw new IllegalArgumentException("containsMonoK requires k > 2");
		}
		
		//need to check all possible values of n choose k
		int[] initNodes = new int[k];
		for(int i = 0; i < k; i++) {
			initNodes[i] = i;
		}
		
		return countRecurs(initNodes, 0, k);
	}
	
	private int countRecurs(int[] nodes, int index, int k) {
		
		if(index == k - 1) { //the case that we're looping through the that last value in the set of k
			//want to go from starting at the current value until the max value of a node (n-1)
			//for(int i = nodes[index]; i < n; i++) {
			
			int original_val = nodes[index];
			int count = 0;
			while(nodes[index] < n) {
				if(checkMono(nodes)) {
					count++;
				}
				nodes[index]++;
			}
			
			nodes[index] = original_val;
			return count;
		}
		
		int count = 0;
		
		if(index < k - 1) { //want to update the latest index first
			int[] original_nodes = new int[nodes.length];
			copyIntArray(nodes, original_nodes);
			
			count += countRecurs(nodes, index + 1, k);
			
			nodes = original_nodes;
			
		}
		
		//we didn't find a mono yet, now need to increase some values
		for(int i = index; i < k; i++) {
			 nodes[i]++;
		}
		
		if(nodes[k-1] >= n) { //no longer a valid combination, no mono found in this rabbit hole
			return count;
		}
		
		return count + countRecurs(nodes, index, k);
	}
	
	private boolean recurs(int[] nodes, int index, int k) {
		
		if(index == k - 1) { //the case that we're looping through the that last value in the set of k
			//want to go from starting at the current value until the max value of a node (n-1)
			//for(int i = nodes[index]; i < n; i++) {
			
			int original_val = nodes[index];
			while(nodes[index] < n) {
				if(checkMono(nodes)) {
					return true;
				}
				nodes[index]++;
			}
			
			nodes[index] = original_val;
			return false;
		}
		
		if(index < k - 1) { //want to update the latest index first
			int[] original_nodes = new int[nodes.length];
			copyIntArray(nodes, original_nodes);
			
			if(recurs(nodes, index + 1, k)) {
				return true;
			}
			
			nodes = original_nodes;
			
		}
		
		//we didn't find a mono yet, now need to increase some values
		for(int i = index; i < k; i++) {
			 nodes[i]++;
		}
		
		if(nodes[k-1] >= n) { //no longer a valid combination, no mono found in this rabbit hole
			return false;
		}
		
		return recurs(nodes, index, k);
	}
	
	private boolean recursRainbow(int[] nodes, int index, int k) {
		
		if(index == k - 1) { //the case that we're looping through the that last value in the set of k
			//want to go from starting at the current value until the max value of a node (n-1)
			//for(int i = nodes[index]; i < n; i++) {
			
			int original_val = nodes[index];
			while(nodes[index] < n) {
				if(checkRainbow(nodes)) {
					return true;
				}
				nodes[index]++;
			}
			
			nodes[index] = original_val;
			return false;
		}
		
		if(index < k - 1) { //want to update the latest index first
			int[] original_nodes = new int[nodes.length];
			copyIntArray(nodes, original_nodes);
			
			if(recursRainbow(nodes, index + 1, k)) {
				return true;
			}
			
			nodes = original_nodes;
			
		}
		
		//we didn't find a mono yet, now need to increase some values
		for(int i = index; i < k; i++) {
			 nodes[i]++;
		}
		
		if(nodes[k-1] >= n) { //no longer a valid combination, no mono found in this rabbit hole
			return false;
		}
		
		return recursRainbow(nodes, index, k);
	}
	
	private void copyIntArray(int[] orig, int[] copy_into) {
		for(int i = 0; i < orig.length; i++) {
			copy_into[i] = orig[i];
		}
	}
	
	private boolean checkMono(int[] nodes) {
		//assume nodes is increasing
		
		for(int i = 1; i < nodes.length; i++) {
			if(nodes[i] <= nodes[i-1]) {
				throw new IllegalArgumentException("programmer error: make sure your nodes are increasing");
			}
		}
		
		int col = graph[nodes[0]][nodes[1] - nodes[0] - 1];
		
		for(int i = 0; i < nodes.length - 1; i++) {
			for(int j = i + 1; j < nodes.length; j++) {
				try {
					if(col != graph[nodes[i]][nodes[j] - nodes[i] - 1]) {
						return false;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					System.err.println(e.getMessage() + "\ni has value: " + i + " and nodes[i] has value: " + nodes[i]);
					System.err.println("j has value: " + j + " and nodes[j] has value: " + nodes[j]);
					System.err.println("attempted to get graph[" + nodes[i] + "][" + (nodes[j] - nodes[i] - 1) + "]");
					System.err.println(this.toString());
					throw e;
				}
			}
		}
		
		return true;
	}
	
	private boolean checkRainbow(int[] nodes) {
		//assume nodes is increasing
		
		Set<Integer> colSet = new HashSet<Integer>();
		
		for(int i = 0; i < nodes.length - 1; i++) {
			for(int j = i + 1; j < nodes.length; j++) {
				if(colSet.contains(graph[nodes[i]][nodes[j] - nodes[i] - 1])) {
					return false;
				}
				colSet.add(graph[nodes[i]][nodes[j] - nodes[i] - 1]);
			}
		}
		
		return true;
	}
	
	public int n() {
		return n;
	}
	
	public String toString() {
		String out = "";
		
		for(int i = 0; i < graph.length; i++) {
			out += Arrays.toString(graph[i]) + "\n";
		}
		
		return out;
	}
}
