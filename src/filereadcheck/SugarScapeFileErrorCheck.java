package filereadcheck;

public class SugarScapeFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_VISION = 3;
	private final int DEFAULT_SUGARMETABOLISM = 10;
	private final int DEFAULT_SUGARAMOUNT = 3;
	private final int DEFAULT_SUGARAGENT = 3; 
	private final int DEFAULT_SUGARGROWBACKRATE = 1;
	private final int DEFAULT_SUGARGROWBACKINTERVAL = 1;
	
	public SugarScapeFileErrorCheck(FileReader fr) { 
		super(fr); 
		String[] neededGOL = {"vision", "sugarMetabolism", "sugarAmount", "sugarAgent", "sugarGrowBackRate", "sugarGrowBackInterval"};
		needed = neededGOL; 
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "vision": 
			writer.editProperty("vision", DEFAULT_VISION + "");
			break;
		case "sugarMetabolism": 
			writer.editProperty("sugarMetabolism", DEFAULT_SUGARMETABOLISM + "");
			break;
		case "sugarAmount": 
			writer.editProperty("sugarAmount", DEFAULT_SUGARAMOUNT + "");
			break;
		case "sugarAgent": 
			writer.editProperty("sugarAgent", DEFAULT_SUGARAGENT + "");
			break;
		case "sugarGrowBackRate": 
			writer.editProperty("sugarGrowBackRate", DEFAULT_SUGARGROWBACKRATE + "");
			break;
		case "sugarGrowBackInterval": 
			writer.editProperty("sugarGrowBackInterval", DEFAULT_SUGARGROWBACKINTERVAL + "");
			break;
		default: 
		}
	}
}
