package filereadcheck;

public class FireFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_PERCENT = 55; 
	private final String[] needed = {"percentage"};

	public FireFileErrorCheck(FileReader fr) {
		super(fr);
	}
	
	public void checkParams() { 
		for (int i=0;i<this.needed.length;i++) { 
			String val = ""; 
			try { 
				val = reader.readProperty(needed[i]); 
				validateParam(needed[i]);
			} catch (Exception e) { 
				System.out.println(String.format(myResources.getString("NoParams"), needed[i]));
				fillParam(val); 
			}
		}
	}
	
	public void fillParam(String property) { 
		reader.writeProperty(property, DEFAULT_PERCENT + "");
		System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_PERCENT + ""));
	}

}
