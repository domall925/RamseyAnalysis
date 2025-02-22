/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Determines the amount of mono K_k's of graphs of size n. 
 */

package graphs;

public class KnCountThread extends Thread {
	private int n;
	private int k;
	private int iterations;
	private float avgMono;
	
	public KnCountThread(int n, int k, int iterations) {
		this.n = n;
		this.k = k;
		this.iterations = iterations;
	}
	
	private int graphInstance(int n, int k) {
		GraphColoredRandom graph = new GraphColoredRandom(n);
		return graph.countMonoK(k);
	}
	
	private void performIterations(int n, int k, int iterations) {
		
		int totalMonoCount = 0;
		for(int i = 0; i < iterations; i++) {
			totalMonoCount += graphInstance(n,k);
		}
		
		this.avgMono = (float)totalMonoCount/iterations;
	}
	
	@Override
	public void run() {
		performIterations(n,k,iterations);
	}
	
	public String toString() {
		return "n: " + n + "\nk: " + k + "\nAvg amount of mono_k: " + avgMono +"\n";
	}
	
	public int n() {
		return n;
	}
	
	public int k() {
		return k;
	}
	
	public int iterations() {
		return iterations;
	}
	
	public float avgMono_k() {
		return avgMono;
	}
}
