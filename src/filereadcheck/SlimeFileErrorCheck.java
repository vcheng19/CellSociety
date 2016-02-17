package filereadcheck;


public class SlimeFileErrorCheck extends FileErrorCheck {
	private final int DEFAULT_SLIMECELLS = 50; 
	private final int DEFAULT_SNIFFTHRESHHOLD = 1; 
	private final int DEFAULT_SNIFFANGLE = 45; 
	private final int DEFAULT_WIGGLEANGLE = 45;
	private final int DEFAULT_WIGGLEBIAS = 0;
	
	public SlimeFileErrorCheck(FileReader fr) { 
		super(fr); 
		String[] neededSlime = {"numSlimeCells", "sniffThreshhold","sniffAngle","wiggleAngle","wiggleBias"};
		needed = neededSlime; 
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "numSlimeCells": 
			writer.editProperty("numSlimeCells", DEFAULT_SLIMECELLS + "");
			break;
		case "sniffThreshhold": 
			writer.editProperty("sniffThreshhold", DEFAULT_SNIFFTHRESHHOLD + "");
			break;
		case "sniffAngle": 
			writer.editProperty("sniffAngle", DEFAULT_SNIFFANGLE + "");
			break;
		case "wiggleAngle": 
			writer.editProperty("wiggleAngle", DEFAULT_WIGGLEANGLE + "");
			break;
		case "wiggleBias": 
			writer.editProperty("wiggleBias", DEFAULT_WIGGLEBIAS + "");
			break;
		default: 
		}
	}
}