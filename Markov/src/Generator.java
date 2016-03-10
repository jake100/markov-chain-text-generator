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
	int numWordsMin = 3;
	int numWordsMax = 12;
	Random rnd = new Random();
	FirstGenerator firstGen;
	public Hashtable<String, ArrayList<Word>> pairs = new Hashtable<String, ArrayList<Word>>();
	public Generator(String string)
	{
		firstGen = new FirstGenerator(string);
		createTable(string);
	}
	public String generate()
	{
		String generatedText = "";
		int numWords = randomizeNumWords();
		for (int i = 0; i < numWords; i++) {
			if(generatedText.equals(""))
			{
				
				curWord = firstGen.generate();
				generatedText += curWord;
				
			}
			else
			{
				ArrayList<Word> words = pairs.get(curWord);
				
				if(words != null)
				{
					generatedText += " ";
					double[] values = new double[words.size()];
					for (int j = 0; j < values.length; j++) {
						values[j] = words.get(j).num;
					}
					if(values.length != 0)curWord = words.get(Util.rouletteSelect(values)).s;
					else
					{
						String[] wordValues = (String[]) pairs.values().toArray();
						curWord = wordValues[rnd.nextInt(values.length)];
					}
					generatedText += curWord;
				}
			}
		}
		return generatedText;
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
