package cellsociety_team24;

import javafx.scene.Group;

public class GridInitializer {

	private static FileReader reader;
	private static Group g; 
	
	GridInitializer (Group gr, FileReader fr) { 
		reader = fr;
		g = gr; 
	}
	
	// ask for properties from reader
	public void test() { 
		System.out.println(reader.readProperty("title"));
		System.out.println(reader.readProperty("underpop"));
		System.out.println(reader.readProperty("alivex"));
	}
	
	public static void main(String[] args) { 
		
	}

}
