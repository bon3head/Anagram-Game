//import java.util.Scanner;
//import java.util.*;
import java.util.ArrayList;

public class Gram{

	private ArrayList<String> solutions;
	//private ArrayList<String> words;
	//private int randomIdx;
	private String scrambled;
	//private String[] scramble;

	public Gram(String scram, String sA, String sB, String sC ) // sets up for solutions for Easy
	{
		solutions = new ArrayList<String>();
		scrambled = scram;
		solutions.add(sA);
		solutions.add(sB);
		solutions.add(sC);
	}

	public Gram(String scram, String ans)	// sets up solutions for Normal, Mixed, Custom
	{
		solutions = new ArrayList<String>();
		scrambled = scram;
		if(ans.indexOf(",")>-1)
		{
			String[] newAns = ans.split("[,]"); 			// takes big String made in PlayGame/fillList and adds
			for(String s : newAns)							// each word to an array || inefficient but it works and im sleepy
			{
				solutions.add(s);
			}
		}
		else
			solutions.add(ans);
	}
	
	public Gram(String word)
	{
		solutions = new ArrayList<String>();
		scrambled=word;
	}

	//setters/getters
	public String getScram()
	{
			return scrambled;
	}

	public void setScram(String str)
	{
		scrambled = str;
	}
	///

	public boolean isSolution(String str)
	{
		boolean is = false;
		for(String sols : solutions)
			{
				if(str.equals(sols))
				is = true;
			}
		return is;
	}

}


