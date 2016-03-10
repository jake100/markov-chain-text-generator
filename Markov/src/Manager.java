import java.util.ArrayList;

public class Manager {
	int numSentances = 10;
	Generator generator = new Generator();
	String[] strings = new String[numSentances];
	public Manager()
	{
		String text = Load.load("res/text.txt");
		for (int i = 0; i < numSentances; i++) {
			strings[i] = generator.generate(text);
		}
		manage();
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}
	public void manage()
	{
		for (int i = 0; i < strings.length; i++) {
			String string = strings[i];
			if(!(string.endsWith("the") || string.endsWith("and")
					|| string.endsWith("take") || string.endsWith("your")
					|| string.endsWith("*is*")|| string.endsWith("each")
					|| string.endsWith("they")|| string.endsWith("like")
					|| string.endsWith("in")|| string.endsWith("my")
					|| string.endsWith("his")|| string.endsWith("our")
					|| string.endsWith("a")|| string.endsWith("she's")
					|| string.endsWith("he")|| string.endsWith(" ")
					|| string.endsWith("she")|| string.endsWith("it")
					|| string.endsWith("but")|| string.endsWith("you're")
					|| string.endsWith("on")|| string.endsWith("or")
					|| string.endsWith("introduce")|| string.endsWith("you")
					|| string.endsWith("that's")|| string.endsWith("their")
					|| string.endsWith("ain't")|| string.endsWith("start")
					|| string.endsWith("a Go")|| string.endsWith("a go")
					|| string.endsWith("of")|| string.endsWith("just")
					|| string.endsWith("not")|| string.endsWith("if")
					|| string.endsWith("than")|| string.endsWith("are")
					|| string.endsWith("at")|| string.endsWith("to")
					|| string.endsWith("is")|| string.endsWith(",")
					|| string.endsWith(":")|| string.endsWith(";")
					|| string.endsWith("I'll get")|| string.endsWith("You'll get")
					|| string.endsWith("Don't be")|| string.endsWith("You're totally")
					|| string.matches("^\\w*[\\.\\?\\!]")) || string.matches("[A-Z]+[a-z']+[\\.\\?\\!]*$"))
			{
				if(!string.matches("[\\.\\?\\!\\,]$"))
				{
					string = string + ".";
				}
				if(string.startsWith(" "))string = string.substring(1);
				if(string.startsWith(" "))string = string.substring(1);
				if(string.startsWith(" "))string = string.substring(1);
				strings[i] = string.substring(0, 1).toUpperCase() + string.substring(1);
			}
			else
			{
				strings[i] = "\"" + string +"\"";
			}
		}
	}
	public static void main(String[] args)
	{
		new Manager();
	}
}
