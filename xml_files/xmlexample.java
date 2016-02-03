package cellsociety_team24;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class FileReader {
	private static String sim_type; 
	private static String title; 
	private static String author; 
	private static int dimension;
	private static int underpop; 
	private static int overpop; 
	private static int liveAgain;
	private static int[] aliveX; 
	private static int[] aliveY;
	
	public static void populateAliveVals(String vals, int[] coors) { 
		String[] strArray = vals.split(",");
		coors = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    coors[i] = Integer.parseInt(strArray[i]);
		}
	}
	
	public static String getNext(XMLStreamReader reader) throws XMLStreamException { 
		String temp = "";
		if (reader.hasText()) {
			while (reader.getText().trim().equals("")) {
				reader.next();
			}
			temp = reader.getText();
		}
		reader.next();
		return temp;
	}

	public static void main(string[] args) { 
		Node cellSocietyNode = getElementsByTagName("cellsociety");
		String vendorId = applicationIdChildren.getItem(0).getAttribute("value").value();
		String authAppliId = applicationIdChildren.getItem(1).getAttribute("value").value();
		String actApplID = applicationIdChildren.getItem(2).getAttribute("value").value();

	}
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException { 
		File xmlFile = new File("xml_files/gol.xml");
		InputStream is = new FileInputStream(xmlFile);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(is);
		while (reader.hasNext()) {
//			if (reader.hasText()) {
//				String temp = reader.getText();
//				if (temp.trim().equals("")) System.out.println("blank line");
//				else System.out.println(temp);
//			}
			sim_type = getNext(reader);
			System.out.println("simtype: " + sim_type);
			title = getNext(reader);
			System.out.println("title: " + title);
			author = getNext(reader);
			System.out.println("author: " + author);
			
			underpop = Integer.parseInt(getNext(reader));
			System.out.println("underpop: "+ underpop);
			overpop = Integer.parseInt(getNext(reader));
			System.out.println("overpop: "+ overpop);
			liveAgain = Integer.parseInt(getNext(reader));
			System.out.println("liveAgain: "+ liveAgain);
			String xs = getNext(reader);
			System.out.println(xs);
			String ys = getNext(reader);
			System.out.println(ys);
		}
//		while (reader.hasNext()) { 
//			System.out.println(reader.getText());
//			System.out.println(reader.next());
//			sim_type = reader.getText(); 
//			title = reader.getText(); 
//			author = reader.getText(); 
//			dimension = Integer.parseInt(reader.getText());
//			underpop = Integer.parseInt(reader.getText());
//
//			System.out.println(underpop);
//			overpop = Integer.parseInt(reader.getText());
//			liveAgain = Integer.parseInt(reader.getText());
//			//String xs = reader.getText(); 
//			//populateAliveVals(xs, aliveX);
//			//String ys = reader.getText();
//			//populateAliveVals(ys, aliveY);
//		}
//		while (reader.hasNext()) { 
//			if (reader.hasText()) System.out.println(reader.getText());
//			reader.next();
//		}
	}
}
