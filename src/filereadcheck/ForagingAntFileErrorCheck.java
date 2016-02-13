package filereadcheck;

public class ForagingAntFileErrorCheck extends FileErrorCheck {
	private final String sim_type;
	private String[] needed = {"initantsnest", "birthrate", "maxph", "selectk", "selectn", "nestx", "nesty", "foodx", "foody"}; 
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
	}
			
	public void checkParams() { 
		for (int i=0;i<this.needed.length;i++) { 
			String val = ""; 
			try { 
				val = reader.readProperty(needed[i]); 
				validateParam(needed[i]);
			} catch (Exception e) { 
				System.out.println(needed[i]);
				System.out.println(String.format(myResources.getString("NoParams"), needed[i], sim_type));
				fillParam(val); 
			}
		}
	}

	public void fillParam(String property) { 
		switch(property) { 
		case "birthrate": 
			reader.writeProperty(property, DEFAULT_BIRTH + "");
			//System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_OVERPOP + "", property));
			break;
		case "initantsnest": 
			reader.writeProperty(property, DEFAULT_NUM_INITS + "");
			//System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_UNDERPOP + "", property));
			break;
		case "selectk": 
			reader.writeProperty(property, SELECT_K + "");
			break;
		case "selectn": 
			reader.writeProperty(property, SELECT_N + "");
			//System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_LIVEAGAIN + "", property));
			break;
		case "max_ph": 
			reader.writeProperty(property, MAX_PH + "");
			break;
		case "nestx": 
			reader.writeProperty(property, (int) DIMENSION * correctScale + "");
			break;
		case "nesty": 
			reader.writeProperty(property, (int) DIMENSION * correctScale + "");
			break;
		case "foodx": 
			reader.writeProperty(property, DIMENSION - (int) DIMENSION * correctScale + "");
			break;
		case "foody": 
			reader.writeProperty(property, DIMENSION - (int) DIMENSION * correctScale + "");
			break;

		default: 
		}
	}
}
