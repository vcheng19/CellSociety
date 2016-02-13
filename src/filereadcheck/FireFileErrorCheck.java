package filereadcheck;

public class FireFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_PERCENT = 55; 

	public FireFileErrorCheck(FileReader fr) {
		super(fr);
		String[] fireNeeded = {"percentage"};
		needed = fireNeeded;
	}
	
	public void fillParam(String property) { 
		writer.writeProperty(property, DEFAULT_PERCENT + "");
		System.out.println(String.format(myResources.getString("BadParam"), DEFAULT_PERCENT + ""));
	}
}
