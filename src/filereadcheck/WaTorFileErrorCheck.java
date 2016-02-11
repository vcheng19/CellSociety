package filereadcheck;

import org.w3c.dom.Node;

public class WaTorFileErrorCheck extends FileErrorCheck {
	private final String sim_type = "WaTor";
	private final int DEFAULT_SHARK_TURNS_SPAWN = 6; 
	private final int DEFAULT_FISH_TURNS_SPAWN = 2;
	private final int DEFAULT_SHARK_INIT_ENERGY = 2; 
	private final int DEFAULT_FISH_ENERGY = 3; 
	private final String[] needed = {"sharkenergy", "fishspawn", "sharkspawn", "fishenergy"}; 

	public WaTorFileErrorCheck (FileReader fr) { 
		super(fr);
	}
	
	public void checkParams() { 
		for (int i=0;i<this.needed.length;i++) { 
			String val = ""; 
			try { 
				val = reader.readProperty(needed[i]); 
				validateParam(needed[i]);
			} catch (Exception e) { 
				System.out.println(String.format(myResources.getString("NoParams"), needed[i], sim_type));
				fillParam(val); 
			}
		}
	}
	
	public void fillParam(String property) { 
		if (Integer.parseInt(reader.readProperty(property)) <= 0) { 
			Node p = reader.retrievePropertyNode(property);
			p.getParentNode().removeChild(p); 
		}
		switch(property) { 
		case "sharkspawn": 
			reader.writeProperty("sharkspawn", DEFAULT_SHARK_TURNS_SPAWN + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_SHARK_TURNS_SPAWN + ""));
			break;
		case "fishspawn": 
			reader.writeProperty("fishspawn", DEFAULT_FISH_TURNS_SPAWN + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_FISH_TURNS_SPAWN + ""));
			break;
		case "sharkenergy": 
			reader.writeProperty("sharkenergy", DEFAULT_SHARK_INIT_ENERGY + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_SHARK_INIT_ENERGY + ""));
			break;
		case "fishenergy": 
			reader.writeProperty("fishenergy", DEFAULT_FISH_ENERGY + "");
			System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_FISH_ENERGY + ""));
			break;
		default: 
		}
	}

	
//	public Object fillParams(String need) { 
//		switch(need) { 
//		case "sharkspawn": 
//			// append sharkspawn 
//		case "fishspawn": 
//			//
//		case "sharkenergy": 
//			// 
//		case "fishenergy": 
//		
//		default: 
//			return 0;
//		}
//	}
}
