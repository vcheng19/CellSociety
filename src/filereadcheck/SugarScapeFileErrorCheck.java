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
		String[] neededGOL = {"underpop", "overpop", "liveagain"};
		needed = neededGOL; 
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "vision": 
			writer.writeProperty("vision", DEFAULT_VISION + "");
			break;
		case "sugarMetabolism": 
			writer.writeProperty("sugarMetabolism", DEFAULT_SUGARMETABOLISM + "");
			break;
		case "sugarAmount": 
			writer.writeProperty("sugarAmount", DEFAULT_SUGARAMOUNT + "");
			break;
		case "sugarAgent": 
			writer.writeProperty("sugarAgent", DEFAULT_SUGARAGENT + "");
			break;
		case "sugarGrowBackRate": 
			writer.writeProperty("sugarGrowBackRate", DEFAULT_SUGARGROWBACKRATE + "");
			break;
		case "sugarGrowBackInterval": 
			writer.writeProperty("sugarGrowBackInterval", DEFAULT_SUGARGROWBACKINTERVAL + "");
			break;
		default: 
		}
	}
}
