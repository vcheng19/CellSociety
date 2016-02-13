package filereadcheck;
import java.util.ResourceBundle;

import org.w3c.dom.Node;

public abstract class FileErrorCheck {
	FileReader reader; 
	final String ERROR_RESOURCES = "resources/ErrorMsgs";
	ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	final String dim_tag = "dimension"; 
	
	public FileErrorCheck(FileReader fr) {
		reader = fr; 
	}
	
	public void validateParam(String property) { 
		int n = Integer.parseInt(reader.readProperty(property));
		if (n <= 0) { 
			Node p = reader.retrievePropertyNode(property);
			p.getParentNode().removeChild(p); 
			fillParam(property);
		} 
	}
	
	public abstract void checkParams(); 
	public abstract void fillParam(String s);
}
