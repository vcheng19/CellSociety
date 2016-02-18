// This entire file is part of my masterpiece.
// Carolyn Yao

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
	private Document doc; 
	private final String headTag = "cellsociety";
	final static String ERROR_RESOURCES = "resources/ErrorMsgs";
	static ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	
	// two different initializers - one for where config is loaded of a read file, so just edit the doc
	FileWriter(FileReader reader) { 
		this.reader = reader;
		doc = reader.getDoc();
	}
	
	FileWriter() {  // in case simulation does not load off a file, create a new doc to save
		Document doc = constructDoc();
		Element rootElement = doc.createElement(headTag);
		doc.appendChild(rootElement);
	}
	
	public Document constructDoc() { 
		Document newDoc; 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try { 
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();	
			newDoc = docBuilder.newDocument();
		}
		catch (Exception e) { 
			throw new FileException(myResources.getString("CouldNotMakeDoc"));
		}
		return newDoc;
	}
	
	public void editProperty(String property, String val) { 
		removeNode(property);
		writeElementToFile(property, val);
		System.out.println(String.format(myResources.getString("BadParam"), val + "", property));
	}
	
	public void writeElementToFile(String property, String val) {
		Node cell = doc.getElementsByTagName(headTag).item(0);
		Node prop = doc.createElement(property); 
		prop.appendChild(doc.createTextNode(val));
		cell.appendChild(prop);
	}
	
	public void removeNode(String property) { 
		Node p = doc.getElementsByTagName(property).item(0);
		if (p != null) { 
			p.getParentNode().removeChild(p); 
		}
	}
	
	public void writeDocToXML() { 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(myResources.getString("DefaultFileSaveName")));
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (TransformerException e) {
			throw new FileException(myResources.getString("CouldNotWriteToDoc"));
		}
	}
}
