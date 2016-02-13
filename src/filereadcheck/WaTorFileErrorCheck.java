package filereadcheck;


public class WaTorFileErrorCheck extends FileErrorCheck {
	private final int DEFAULT_SHARK_TURNS_SPAWN = 6; 
	private final int DEFAULT_FISH_TURNS_SPAWN = 2;
	private final int DEFAULT_SHARK_INIT_ENERGY = 2; 
	private final int DEFAULT_FISH_ENERGY = 3; 

	public WaTorFileErrorCheck (FileReader fr) { 
		super(fr);
		String[] watorNeeded = {"sharkenergy", "fishspawn", "sharkspawn", "fishenergy"}; 
		needed = watorNeeded;
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "sharkspawn": 
			writer.writeProperty("sharkspawn", DEFAULT_SHARK_TURNS_SPAWN + "");
			break;
		case "fishspawn": 
			writer.writeProperty("fishspawn", DEFAULT_FISH_TURNS_SPAWN + "");
			break;
		case "sharkenergy": 
			writer.writeProperty("sharkenergy", DEFAULT_SHARK_INIT_ENERGY + "");
			break;
		case "fishenergy": 
			writer.writeProperty("fishenergy", DEFAULT_FISH_ENERGY + "");
			break;
		default: 
		}
	}
}
