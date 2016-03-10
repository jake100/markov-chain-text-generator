import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class FirstGenerator {
	Hashtable<String, Double> firsts = new Hashtable<String, Double>();
	public FirstGenerator(String string)
	{
		createTable(string);
	}
	public String generate()
	{
		Double[] nums = new Double[firsts.values().size()];
		String[] keys = new String[firsts.values().size()];
		Iterator<String> keyit = firsts.keySet().iterator();
		Iterator<Double> it = firsts.values().iterator();
		int i = 0;
		while (it.hasNext()) {
		  nums[i] = it.next();
		  i++;
		}
		i = 0;
		while (keyit.hasNext()) {
			  keys[i] = keyit.next();
			  i++;
		}
		
		return "" + keys[Util.rouletteSelect(nums)];
	}
	public void createTable(String string)
	{
		String[] lines = string.split("\n");
		for (int i = 0; i < lines.length; i++) {
			String[] words = lines[i].split(" ");
			String word = words[0];
			if(firsts.contains(word))
			{
				Double num = firsts.get(word);
				firsts.put(word, num + 1);
			}
			else
			{
				firsts.put(word, (double) 1);
			}
		}
		
	}
}
