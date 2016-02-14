package filereadcheck;


public class GOLFileErrorCheck extends FileErrorCheck {
	private final int DEFAULT_OVERPOP = 3; 
	private final int DEFAULT_UNDERPOP = 2; 
	private final int DEFAULT_LIVEAGAIN = 3; 
	
	public GOLFileErrorCheck(FileReader fr) { 
		super(fr); 
		String[] neededGOL = {"underpop", "overpop", "liveagain"};
		needed = neededGOL; 
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "overpop": 
			writer.writeProperty("overpop", DEFAULT_OVERPOP + "");
			break;
		case "underpop": 
			writer.writeProperty("underpop", DEFAULT_UNDERPOP + "");
			break;
		case "liveagain": 
			writer.writeProperty("liveagain", DEFAULT_LIVEAGAIN + "");
			break;
		default: 
		}
	}
}
