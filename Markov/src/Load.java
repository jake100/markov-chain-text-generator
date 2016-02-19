import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Load {
	public static String load(String path)
	{
		Scanner scanner = null;
	try {
		scanner = new Scanner(new File(path));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String text = scanner.useDelimiter("\\A").next();
	scanner.close();
	return text;
	}
}
