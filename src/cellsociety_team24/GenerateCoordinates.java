package cellsociety_team24;

public class GenerateCoordinates {

	public static void gen (int dimension, int numPoints) { 
		Integer[] xs = new Integer[numPoints];
		Integer[] ys = new Integer[numPoints];
		for (int i=0;i<numPoints;i++) { 
			int x = (int) Math.floor(Math.random() * dimension);
			xs[i] = x;
			int y = (int) Math.floor(Math.random() * dimension);
			ys[i] = y;
		}
		for (int i=0;i<xs.length;i++) { 
			System.out.print(xs[i] + " ");
		}
		System.out.println();
		for (int i=0;i<ys.length;i++) { 
			System.out.print(ys[i] + " ");
		}
	}
	
	public static void main(String[] args) { 
<<<<<<< HEAD
		gen(40, 200);
=======
		gen(50, 1300);
		System.out.println("");
		gen(50, 1300);
>>>>>>> 356bf44a80f49efa3cd28a1ab573c83cfaa2b2a8
	}

}
