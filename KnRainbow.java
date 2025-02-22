/**
 * Ramsey Theory Analysis
 * Author: Dominic Hall
 * 2025-02-22
 * Uses the KnCountThread class to find the the proportion graphs with nodes n to N have, colored randomly with cs colors, that have a complete K_ks. 
 */

package graphs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class KnRainbow {
	private static final int n = 3;
	private static final int N = 25;
	private static final int[] ks = {5};
	private static final int iterations = 10000;
	private static final int[] cs = {10,11,12,13,14,15,16,17,18,19,20};
	private static final String filePath = "results.csv";
	
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		KnRainbowThread[] threads = new KnRainbowThread[cs.length * ks.length * (N - n + 1)];
		
		for(int c = 0; c < cs.length; c++) {
			for(int k = 0; k < ks.length; k++) {
				for(int i = n; i <= N; i++) {
					threads[c * ks.length * (N - n + 1) +  k * (N - n + 1) + i-n] = new KnRainbowThread(i,ks[k],iterations,cs[c]);
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
	        String[] header = { "n", "k", "c", "iterations", "percent with rainbow_k" }; 
	        writer.writeNext(header); 
	  
	        for(int i = 0; i < threads.length; i++) {
	        	String[] data = {String.valueOf(threads[i].n()),  String.valueOf(threads[i].k()), String.valueOf(threads[i].c()), 
	        			String.valueOf(threads[i].iterations()), String.valueOf(threads[i].percentRainbow())};
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
