/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Uses the KnCountThread class to find the the amount of complete K_k graphs with nodes n to N have. 
 */

package graphs;

import java.io.*; 
import com.opencsv.CSVWriter;

public class KnCount {
	private static final int n = 3;
	private static final int N = 15;
	//private static final int k = 3;
	private static final int[] ks = {3,4,5,6};
	private static final int iterations = 10000;
	
	private static String filePath = "results.csv";
	
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		
		
		KnCountThread[] threads = new KnCountThread[ks.length * (N - n + 1)];
		
		for(int k = 0; k < ks.length; k++) {
			for(int i = n; i <= N; i++) {
				threads[k * (N - n + 1) + i-n] = new KnCountThread(i,ks[k],iterations);
			}
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
			//System.out.println(threads[i]);
		}
	
	
		long elapsedTime = System.currentTimeMillis() - start;
		
		File file = new File(filePath); 
	    try { 
	        FileWriter outputfile = new FileWriter(file); 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        String[] header = { "n", "k", "iterations", "Avg mono_k" }; 
	        writer.writeNext(header); 
	  
	        for(int i = 0; i < threads.length; i++) {
	        	String[] data = {String.valueOf(threads[i].n()),  String.valueOf(threads[i].k()), 
	        			String.valueOf(threads[i].iterations()), String.valueOf(threads[i].avgMono_k())};
	        	writer.writeNext(data);
	        }
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
		
		System.out.println("Elapsed time: " + elapsedTime);
		
	}

}
