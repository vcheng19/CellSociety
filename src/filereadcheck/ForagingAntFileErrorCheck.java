package filereadcheck;

public class ForagingAntFileErrorCheck extends FileErrorCheck {
	private final int DIMENSION; 
	private final int DEFAULT_BIRTH = 2; 
	private final int MAX_PH = 1000; 
	private final double SELECT_K = 0.001; 
	private final int SELECT_N = 10; 
	private final int DEFAULT_NUM_INITS = 2; 
	private final double correctScale = 0.2; 
	
	public ForagingAntFileErrorCheck(FileReader fr) { 
		super(fr); 
		DIMENSION = Integer.parseInt(reader.readProperty("dimension"));
		sim_type = reader.readProperty("sim_type");
		String[] antsNeeded = {"initantsnest", "birthrate", "maxph", "selectk", "selectn", "nestx", "nesty", "foodx", "foody"}; 
		needed = antsNeeded;
	}

	public void fillParam(String property) { 
		switch(property) { 
		case "birthrate": 
			writer.writeProperty(property, DEFAULT_BIRTH + "");
			break;
		case "initantsnest": 
			writer.writeProperty(property, DEFAULT_NUM_INITS + "");
			break;
		case "selectk": 
			writer.writeProperty(property, SELECT_K + "");
			break;
		case "selectn": 
			writer.writeProperty(property, SELECT_N + "");
			break;
		case "max_ph": 
			writer.writeProperty(property, MAX_PH + "");
			break;
		case "nestx": 
			writer.writeProperty(property, (int) DIMENSION * correctScale + "");
			break;
		case "nesty": 
			writer.writeProperty(property, (int) DIMENSION * correctScale + "");
			break;
		case "foodx": 
			writer.writeProperty(property, DIMENSION - (int) DIMENSION * correctScale + "");
			break;
		case "foody": 
			writer.writeProperty(property, DIMENSION - (int) DIMENSION * correctScale + "");
			break;
		default: 
		}
	}
}
