package graphs;

public class GraphsTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Graph2ColorRandom graph = new Graph2ColorRandom(3);
		
		//System.out.println("successfully made graph of size 3");
		
		//boolean result = graph.containsMonoK(3);
		
		//System.out.println(result);

		//Graph2ColorRandom graph2 = new Graph2ColorRandom(4);
		
		//System.out.println("successfully made graph of size 4\n" + graph2);
		
		//boolean result2 = graph2.containsMonoK(3);
		
		//System.out.println(result2);
		
		//Graph2ColorRandom graph3 = new Graph2ColorRandom(5);
		
		//System.out.println("successfully made graph of size 5\n" + graph3);
		
		//boolean result3 = graph3.containsMonoK(3);
		
		//System.out.println(result3);
		/*
		Graph2ColorRandom graph4 = new Graph2ColorRandom(5);
		
		System.out.println("successfully made graph of size 5\n" + graph4);
		
		boolean result4 = graph4.containsMonoK(4);
		
		System.out.println(result4);
		*/
		
		/*
		Graph2ColorRandom graph5 = new Graph2ColorRandom(8);
		
		System.out.println("successfully made graph of size 5\n" + graph5);
		
		boolean result5 = graph5.containsMonoK(4);
		
		System.out.println(result5);*/
		
		GraphColoredRandom g = new GraphColoredRandom(3,3);
		g.containsRainbowK(3);
		System.out.println(g.containsRainbowK(3));
		System.out.println(g);
		
		
	}
}
