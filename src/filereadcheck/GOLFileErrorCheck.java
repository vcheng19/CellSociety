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
			writer.editProperty("overpop", DEFAULT_OVERPOP + "");
			break;
		case "underpop": 
			writer.editProperty("underpop", DEFAULT_UNDERPOP + "");
			break;
		case "liveagain": 
			writer.editProperty("liveagain", DEFAULT_LIVEAGAIN + "");
			break;
		default: 
		}
	}
}
