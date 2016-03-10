import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Generator {
	String previous = "";
	String curWord = "";
	int numWordsMin = 2;
	int numWordsMax = 8;
	Random rnd = new Random();
	public Hashtable<String, ArrayList<Word>> pairs = new Hashtable<String, ArrayList<Word>>();
	public Generator()
	{
		
	}
	public String generate(String string)
	{
		String generatedText = "";
		createTable(string);
		int numWords = randomizeNumWords();
		for (int i = 0; i < numWords; i++) {
			if(generatedText.equals(""))
			{
				ArrayList<String> keys = new ArrayList<String>(pairs.keySet());
				
				curWord = keys.get(rnd.nextInt(keys.size()));
				generatedText += curWord;
				
			}
			else
			{
				generatedText += " ";
				ArrayList<Word> words = pairs.get(curWord);
				double[] values = new double[words.size()];
				for (int j = 0; j < values.length; j++) {
					values[j] = words.get(j).num;
				}
				if(values.length != 0)curWord = words.get(rouletteSelect(values)).s;
				else
				{
					String[] wordValues = (String[]) pairs.values().toArray();
					curWord = wordValues[rnd.nextInt(values.length)];
				}
				generatedText += curWord;
			}
		}
		return generatedText;
	}
	public static void main(String[] args)
	{
		new Generator();
	}
	public int rouletteSelect(double[] weight) {
		double weight_sum = 0;
		for(int i = 0; i < weight.length; i++) {
			weight_sum += weight[i];
		}
		double value = rnd.nextDouble() * weight_sum;	
		for(int i = 0; i < weight.length; i++) {		
			value -= weight[i];		
			if(value <= 0) return i;
		}
		return weight.length - 1;
	}
	public int randomizeNumWords()
	{
		return rnd.nextInt((numWordsMax - numWordsMin) + 1) + numWordsMin;
	}
	public void createTable(String string)
	{
		String[] words = string.split(" ");
		for (int i = 0; i < words.length; i++) {
			if(!pairs.containsKey(words[i]))
			{
				pairs.put(words[i], new ArrayList<Word>());
			}
			ArrayList<Word> w = new ArrayList<Word>();
			if(pairs.containsKey(previous))
			{
				w = pairs.get(previous);
				boolean found = false;
				for (int j = 0; j < w.size(); j++) {
					if(w.get(j).s.equals(words[i])){
						w.get(j).num++;
						found = true;
					}
				}
				if(!found)w.add(new Word(words[i]));
			}
			pairs.remove(previous);
			pairs.put(previous, w);
			previous = words[i];
		}
	}
}
