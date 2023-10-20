//import java.util.Scanner;
//import java.util.*;
//import java.io.File;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class anagramCheckerLEGACY{

	private ArrayList<Gram> list;
	private String wordX, wordY, reason;
	private String[] letterA, oneLetter;
	private FileWriter fw;
	private PrintWriter pw;
	private String userAdd;
	private int count, fails;


	public anagramCheckerLEGACY(String wX, String wY)
	{

		/*try{
			dict = new Scanner(new File("words.txt"));
		}
		catch (Exception ex){}*/


		wordX = wX;
		wordY = wY;

		//fillList;
	}

	public boolean isAnagram()
	{


		letterA = new String[wordX.length()];  			//array created size of wordX
		oneLetter = new String[wordY.length()];			//array created size of wordY
		boolean anaChk =false;
		if(wordX.equals(wordY))								// scans through wordX for wordY; if found
		{
			anaChk = false;
			reason = " these words are the exact same";
			fails++;
		}
		else if(wordX.length()!=wordY.length())
		{
			anaChk = false;
			reason = " the words are different lengths";					// 31 chars
			fails++;
		}
		else
		{
			reason = "";
			for(int i =0;i<wordX.length();i++)
			{

				oneLetter[i]=wordY.substring(i, i+1);

				if(wordX.indexOf(oneLetter[i])==-1)
				{
					anaChk = false;
					reason +=" "+oneLetter[i] +" is not present in the original word";
				}
				if(wordX.indexOf(oneLetter[i])>-1)
				{
					count++;																	//if the letter at index 'i' in wordY exists anywhere in WordX +1 is added to count

				}

			 if(count == wordX.length())														//if the number of concurrent letters that exist in both words is equal to the length of both words, anagram found!
			 {
				 anaChk = true;

			 }


			}
		}
		return anaChk;

	}
/*
	public boolean isInDict(String ana)
	{	boolean h = false;
		for(int i=0;i<list.size();i++)
		{
			if((list.get(i).equals(ana))
			{
				h = true;
			}
		}
		return h;
	}
*/



	public String failReason()
	{
		if(fails>1)
		{
			reason = " multiple errors have occured";
		}
		else if(reason.length()>39)
		{
			reason = " one or more letters are not present in " + wordX;
		}
		return reason;
	}

	/*public void fillList()
	{
		while(dict.hasNext())
		{
			list.add(new Gram(insaneSC.nextLine()));
		}
	}*/

	public int multChk(String wX, String wY)
	{
		//useless
		return 1;

	}











}