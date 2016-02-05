package cellsociety_team24;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class FileReader{
	private static Document doc;

	FileReader(File f) throws SAXException, IOException, ParserConfigurationException { 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(f);
        doc.getDocumentElement().normalize();
	}
	
	public static void read() throws ParserConfigurationException, SAXException, IOException { 
		File xmlFile = new File("xml_files/gol.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        //readInProperties(doc);
	}
	
	public String readProperty(String property) { 
		String found = doc.getElementsByTagName(property).item(0).getAttributes().item(0).getNodeValue();
		return found;
	}
	
//	public static void readInProperties(Document doc) { 
//        sim_type = readProperty("sim_type");
//        author = readProperty("author");
//        title = readProperty("title");
//        dimension = Integer.parseInt(readProperty("dimension"));
//        overpop = Integer.parseInt(readProperty("overpop"));
//        underpop = Integer.parseInt(readProperty("underpop"));
//        liveAgain = Integer.parseInt(readProperty("liveagain"));
//        String xs = (readProperty("alivex"));
//        String ys = (readProperty("alivey"));
//        aliveX = populateCoorArray(xs);
//        aliveY = populateCoorArray(ys);
//	}
//	
	public int[] populateCoorArray(String vals) { 
		String[] strArray = vals.split(" ");
		int[] coors = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    coors[i] = Integer.parseInt(strArray[i]);
		}
		return coors;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{ 
		read();
//        System.out.println(sim_type);
//        System.out.println(title);
//        System.out.println(author);
//        System.out.println(dimension);
//        System.out.println(underpop);
	}
}
