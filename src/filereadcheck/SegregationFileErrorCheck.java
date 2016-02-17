package filereadcheck;


public class SegregationFileErrorCheck extends FileErrorCheck{
	private final int DEFAULT_SEG_PERCENT = 50; 

	public SegregationFileErrorCheck(FileReader fr) {
		super(fr);
		String[] segNeeded = {"percentage"};
		needed = segNeeded;
	}

	public void fillParam(String property) {
		writer.editProperty(property, DEFAULT_SEG_PERCENT + "");
	}
}
