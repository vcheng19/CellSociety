package filereadcheck;
import java.util.ResourceBundle;

import org.w3c.dom.Node;

public abstract class FileErrorCheck {
	FileReader reader; 
	FileWriter writer;
	static final String ERROR_RESOURCES = "resources/ErrorMsgs";
	static ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	final String dim_tag = "dimension"; 
	String sim_type;
	String[] needed; 
	
	public FileErrorCheck(FileReader fr) {
		reader = fr; 
		writer = reader.getWriter();
		sim_type = reader.readProperty("sim_type");
	}
	
	public void checkParams() { 
		for (int i=0;i<needed.length;i++) { 
			String val = ""; 
			try { 
				val = reader.readProperty(needed[i]); 
				validateParam(needed[i]);
			} catch (Exception e) { 
				System.out.println(String.format(myResources.getString("NoParams"), needed[i], sim_type));
				fillParam(needed[i]); 
			}
		}
	}
	
	public void validateParam(String property) { 
		int n = Integer.parseInt(reader.readProperty(property));
		if (n <= 0) { 
			writer.removeNode(property);
			fillParam(property);
		} 
	}
	
	public abstract void fillParam(String s);
}
