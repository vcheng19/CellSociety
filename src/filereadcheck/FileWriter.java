package filereadcheck;

import java.io.File;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;

public class FileWriter {
	
	FileReader reader; 
	Document doc; 
	private final String headTag = "cellsociety";
	final static String ERROR_RESOURCES = "resources/ErrorMsgs";
	static ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	
	FileWriter(FileReader reader) { 
		this.reader = reader;
		doc = reader.getDoc();
	}
	
	FileWriter() { 
		try { 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("cellsociety");
			doc.appendChild(rootElement);
		} catch (Exception e) { 
			System.out.println("Could not construct document");
		}
	}
	
	public void writeProperty(String property, String val) { 
		Node cell = doc.getElementsByTagName(headTag).item(0);
		Node prop = doc.createElement(property); 
		prop.appendChild(doc.createTextNode(val));
		cell.appendChild(prop);
		System.out.println(String.format(myResources.getString("BadParam"), val + "", property));
	}
	
	public void writeElementToFile(String property, String val) {
		removeNode(property);
		Node cell = doc.getElementsByTagName(headTag).item(0);
		Node prop = doc.createElement(property); 
		prop.appendChild(doc.createTextNode(val));
		cell.appendChild(prop);
	}
	
	public void removeNode(String property) { 
		Node p = retrievePropertyNode(property);
		if (p != null) { 
			p.getParentNode().removeChild(p); 
		}
	}
	
	public Node retrievePropertyNode(String property) { 
		return doc.getElementsByTagName(property).item(0);
	}
	
	public void writeDocToXML(String sim_type) { 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("xml_files\fsd.xml"));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (TransformerException e) {
			System.out.println("couldn't transform to file");
		}
	}
}
