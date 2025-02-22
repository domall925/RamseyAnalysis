/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Uses the KnCountThread class to find the the amount of mono complete K_k cliques with nodes n to N have colored with c colors. 
 */

package graphs;

import java.io.*; 
import com.opencsv.CSVWriter;

public class KnMultiColor {
	private static final int n = 20;
	private static final int N = 20;
	private static final int[] ks = {3};
	private static final int iterations = 10000;
	private static final int[] cs = {2,3,4,5,6,7,8,9,10,12,14,16,18,20,22,24,26,28,30,35,40,45,50,55,60,70,80,90,100,110,120,130,140,150,175,200,250,300,400,500};
	private static final String filePath = "results.csv";
	
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		KnThread[] threads = new KnThread[cs.length * ks.length * (N - n + 1)];
		
		for(int c = 0; c < cs.length; c++) {
			for(int k = 0; k < ks.length; k++) {
				for(int i = n; i <= N; i++) {
					threads[c * ks.length * (N - n + 1) +  k * (N - n + 1) + i-n] = new KnThread(i,ks[k],iterations,cs[c]);
				}
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
		
		long elapsedTime = System.currentTimeMillis() - start;
		
		File file = new File(filePath); 
	    try { 
	        FileWriter outputfile = new FileWriter(file); 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        String[] header = { "n", "k", "c", "iterations", "percent with mono_k" }; 
	        writer.writeNext(header); 
	  
	        for(int i = 0; i < threads.length; i++) {
	        	String[] data = {String.valueOf(threads[i].n()),  String.valueOf(threads[i].k()), String.valueOf(threads[i].c()), 
	        			String.valueOf(threads[i].iterations()), String.valueOf(threads[i].percentMono())};
	        	writer.writeNext(data);
	        }
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        e.printStackTrace(); 
	    }
		
		System.out.println("Elapsed time: " + elapsedTime);
	}

}
