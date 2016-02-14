package filereadcheck;

public class SlimeFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_PERCENT = 55; 

	public SlimeFileErrorCheck(FileReader fr) {
		super(fr);
	}
	
	public void fillParam(String property) { 
//		writer.writeProperty(property, DEFAULT_PERCENT + "");
//		System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_PERCENT + ""));
	}
}
