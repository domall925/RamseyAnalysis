package graphs;

public class KnRainbowThread extends Thread {
	private int n;
	private int k;
	private int iterations;
	private float percentRainbow;
	private int c;
	
	public KnRainbowThread(int n, int k, int iterations, int c) {
		this.n = n;
		this.k = k;
		this.iterations = iterations;
		this.c = c;
	}
	
	public KnRainbowThread(int n, int k, int iterations) {
		this(n, k, iterations, 2);
	}
	
	private int graphInstance(int n, int k) {
		GraphColoredRandom graph = new GraphColoredRandom(n,c);
		return (graph.containsRainbowK(k) ? 1 : 0);
	}
	
	private void performIterations(int n, int k, int iterations) {
		
		int rainbow = 0;
		for(int i = 0; i < iterations; i++) {
			rainbow += graphInstance(n,k);
		}
		
		this.percentRainbow = (float)rainbow/iterations;
	}
	
	@Override
	public void run() {
		performIterations(n,k,iterations);
	}
	
	public String toString() {
		return "n: " + n + "\nk: " + k + "\nc: " + c + "\\nP(mono k): " + percentRainbow +"\n";
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
	
	public float percentRainbow() {
		return percentRainbow;
	}
	
	public int c() {
		return c;
	}
}
