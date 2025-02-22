/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Determines the proportion of graphs of size n have a mono complete K_k
 */

package graphs;

public class KnThread extends Thread {
	private int n;
	private int k;
	private int iterations;
	private float percentMono;
	private int c;
	
	public KnThread(int n, int k, int iterations, int c) {
		this.n = n;
		this.k = k;
		this.iterations = iterations;
		this.c = c;
	}
	
	public KnThread(int n, int k, int iterations) {
		this(n, k, iterations, 2);
	}
	
	private int graphInstance(int n, int k) {
		GraphColoredRandom graph = new GraphColoredRandom(n,c);
		return (graph.containsMonoK(k) ? 1 : 0);
	}
	
	private void performIterations(int n, int k, int iterations) {
		
		int totalMono = 0;
		for(int i = 0; i < iterations; i++) {
			totalMono += graphInstance(n,k);
		}
		
		this.percentMono = (float)totalMono/iterations;
	}
	
	@Override
	public void run() {
		performIterations(n,k,iterations);
	}
	
	public String toString() {
		return "n: " + n + "\nk: " + k + "\nc: " + c + "\\nP(mono k): " + percentMono +"\n";
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
	
	public float percentMono() {
		return percentMono;
	}
	
	public int c() {
		return c;
	}
}
