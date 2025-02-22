/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Uses the KnThread class to find the proportion of graphs with nodes n to N have a complete K_k. 
 */

package graphs;

public class Kn{
	private static final int n = 6;
	private static final int N = 15;
	private static final int k = 6;
	private static final int iterations = 1000;
	
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		KnThread[] threads = new KnThread[N - n + 1];
		
		for(int i = n; i <= N; i++) {
			threads[i-n] = new KnThread(i,k,iterations);
		}
		
		for(int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		for(int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < threads.length; i++) {
			System.out.println(threads[i]);
		}
		
		
		long elapsedTime = System.currentTimeMillis() - start;
		
		System.out.println("Elapsed time: " + elapsedTime);
		
	}
	
	

	
}
