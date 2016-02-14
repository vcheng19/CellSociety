package filereadcheck;

import java.util.ResourceBundle;

import org.w3c.dom.Document;
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
	
	public void writeProperty(String property, String val) { 
		Node cell = doc.getElementsByTagName(headTag).item(0);
		Node prop = doc.createElement(property); 
		prop.appendChild(doc.createTextNode(val));
		cell.appendChild(prop);
		System.out.println(String.format(myResources.getString("BadParam"), val + "", property));
	}
	
	public void removeNode(String property) { 
		Node p = retrievePropertyNode(property);
		p.getParentNode().removeChild(p); 
	}
	
	public Node retrievePropertyNode(String property) { 
		return doc.getElementsByTagName(property).item(0);
	}
}
