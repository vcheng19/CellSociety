package filereadcheck;


public class GOLFileErrorCheck extends FileErrorCheck {
	private final String sim_type = "Game of Life";
	private final int DEFAULT_OVERPOP = 3; 
	private final int DEFAULT_UNDERPOP = 2; 
	private final int DEFAULT_LIVEAGAIN = 3; 
	String[] needed = {"underpop", "overpop", "liveagain"};
	
	public GOLFileErrorCheck(FileReader fr) { 
		super(fr); 
	}
	
	public void checkParams() { 
		for (int i=0;i<this.needed.length;i++) { 
			try { 
				String val = reader.readProperty(needed[i]);
				System.out.println(needed[i] +" " +val);
			} catch (Exception e) { 
				System.out.println(String.format(myResources.getString("NoParams"), needed[i], sim_type));
				fillParam(needed[i]);
			}
			validateParam(needed[i]);
		}
	}
	
	public void fillParam(String property) { 
		switch(property) { 
		case "overpop": 
			reader.writeProperty("overpop", DEFAULT_OVERPOP + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_OVERPOP + "", property));
			break;
		case "underpop": 
			reader.writeProperty("underpop", DEFAULT_UNDERPOP + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_UNDERPOP + "", property));
			break;
		case "liveagain": 
			reader.writeProperty("liveagain", DEFAULT_LIVEAGAIN + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_LIVEAGAIN + "", property));
			break;
		default: 
		}
	}
}
