package cellsociety_team24;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class FileReader{
	private static String sim_type; 
	private static String title; 
	private static String author; 
	private static int dimension;
	private static int underpop; 
	private static int overpop; 
	private static int liveAgain;
	private static int[] aliveX; 
	private static int[] aliveY;

	public static void read() throws ParserConfigurationException, SAXException, IOException { 
		File xmlFile = new File("xml_files/gol.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        readInProperties(doc);
	}
	
	public static void readInProperties(Document doc) { 
        sim_type = fillProperty("sim_type", doc);
        author = fillProperty("author", doc);
        title = fillProperty("title", doc);
        dimension = Integer.parseInt(fillProperty("dimension", doc));
        overpop = Integer.parseInt(fillProperty("overpop", doc));
        underpop = Integer.parseInt(fillProperty("underpop", doc));
        liveAgain = Integer.parseInt(fillProperty("liveagain", doc));
        String xs = (fillProperty("alivex", doc));
        String ys = (fillProperty("alivey", doc));
        aliveX = populateAliveVals(xs);
        aliveY = populateAliveVals(ys);
	}
	public static String fillProperty(String property, Document doc) { 
		property = doc.getElementsByTagName(property).item(0).getAttributes().item(0).getNodeValue();
		return property;
	}
	
	public static int[] populateAliveVals(String vals) { 
		String[] strArray = vals.split(" ");
		int[] coors = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    coors[i] = Integer.parseInt(strArray[i]);
		}
		return coors;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{ 
		read();
        System.out.println(sim_type);
        System.out.println(title);
        System.out.println(author);
        System.out.println(dimension);
        System.out.println(underpop);
	}
}
