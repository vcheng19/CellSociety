package filereadcheck;


public class SegregationFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_SEG_PERCENT = 50; 
	String[] needed = {"percentage"}; 

	public SegregationFileErrorCheck(FileReader fr) {
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
		reader.writeProperty(property, DEFAULT_SEG_PERCENT + "");
		System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_SEG_PERCENT + ""));
		
	}

}
