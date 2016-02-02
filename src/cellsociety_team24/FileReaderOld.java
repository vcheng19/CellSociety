package cellsociety_team24;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class FileReaderOld {
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
			if (!reader.getText().trim().equals("")) temp = reader.getText();
		}
		reader.next();
		return temp;
	}
	
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException { 
		File xmlFile = new File("xml_files/gol.xml");
		InputStream is = new FileInputStream(xmlFile);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(is);
		while (reader.hasNext()) {
			sim_type = getNext(reader);
			System.out.println("simtype: " + sim_type);
			title = getNext(reader);
			System.out.println("title: " + title);
			author = getNext(reader);
			System.out.println("author: " + author);
			dimension = Integer.parseInt(getNext(reader));
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
	}
}
