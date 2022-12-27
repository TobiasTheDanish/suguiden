import java.io.File;

public class FileIO {
	public File createFile(String fileName) {
		try {
			File file = new File(fileName);
			file.createNewFile();
			return file;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
