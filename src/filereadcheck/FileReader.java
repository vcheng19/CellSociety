package filereadcheck;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class FileReader{
	private static Document doc;
	FileErrorCheck errorChecker; 
	final static String ERROR_RESOURCES = "resources/ErrorMsgs";
	static ResourceBundle myResources = ResourceBundle.getBundle(ERROR_RESOURCES); 
	private static final String[] validSims = {"Fire", "Game of life", "WaTor", "Segregation"
			, "Foraging Ant", "SugarScape", "Slime"};

	private DocumentBuilder db;
	private FileWriter writer; 

	public FileReader(File f) throws ParserConfigurationException, SAXException, IOException  { 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(f);
        doc.getDocumentElement().normalize();
        writer = new FileWriter(this);
	}
	
	public void validateSim(){ 
		String name;
		try {
			name = readProperty("sim_type"); 
		} catch (Exception e) {
			throw new FileException(myResources.getString("NoSimType")); 
		}
		try { 
			validateSimName(name); 
		} catch (Exception e) {
			throw new FileException(myResources.getString("BadSimType"), name);
		}
		errorChecker = callMatchingErrorCheck(name);
		System.out.println(name);
		errorChecker.checkParams(); 
	}
	
	public FileErrorCheck callMatchingErrorCheck(String sim_type) { 
		switch (sim_type) { 
		case "Fire": 
			return new FireFileErrorCheck(this);
		case "Game of life": 
			return new GOLFileErrorCheck(this); 
		case "Segregation": 
			return new SegregationFileErrorCheck(this); 
		case "WaTor": 
			return new WaTorFileErrorCheck(this); 
		case "Foraging Ant": 
			return new ForagingAntFileErrorCheck(this); 
		case "SugarScape":
			return new SugarScapeFileErrorCheck(this);
		case "Slime":
			return new SlimeFileErrorCheck(this);
		default: 
			return new GOLFileErrorCheck(this); 
		}
	}
	
	public static boolean isValidSimName(String sim_type) { 
		for (int i=0; i < validSims.length; i++) { 
			if (sim_type.equals(validSims[i])) { 
				return true;
			}
		}
		return false;
	}
	
	public static void validateSimName(String given_sim_type) throws Exception { 
		if (!isValidSimName(given_sim_type)) {
			throw new Exception(myResources.getString("NoSimType")); 
		}
	}
	
	public String readProperty(String property) { 
		String found = doc.getElementsByTagName(property).item(0).getTextContent() + ""; 
		return found;
	}
	
	public Document getDoc() { 
		return doc;
	}
	
	public FileWriter getWriter() { 
		return writer;
	}

	public int[] populateCoorArray(String vals) { 
		String[] strArray = vals.split(" ");
		int[] coors = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    coors[i] = Integer.parseInt(strArray[i]);
		}
		return coors;
	}
}
