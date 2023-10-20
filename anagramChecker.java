//import java.util.Scanner;
//import java.util.*;
//import java.io.File;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.io.IOException;
//import java.io.FileWriter;
//import java.io.PrintWriter;

public class anagramChecker{

	//private ArrayList<Gram> list;
	private String wordX, wordY, reason, missLet;
	private String[] wXArr, wYArr;
	//private FileWriter fw;
	//private PrintWriter pw;
	//private String userAdd;
	private int count, fails;
	private boolean anaChk;


	public anagramChecker(String wX, String wY)
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
		{ missLet="";
			wXArr = arrOrg(wordX);
			wYArr = arrOrg(wordY);
			Arrays.sort(wXArr);
			Arrays.sort(wYArr);

			for(int i = 0;i<wXArr.length;i++)
			{
				if(wXArr[i].equals(wYArr[i]))
				{
					count++;
				}
				else if(wordX.indexOf(wYArr[i])==-1)
					missLet += wYArr[i];

			}
				////
			if(count==wXArr.length)
			{
				anaChk=true;
			}
			else if(missLet.length()>1)
			{
				reason = " the letters "+missLet+" are not present in " + wordX;
			}
			else
			{
				reason = " the letter "+missLet+" is not present in " + wordX;
			}



	 	}
	 return anaChk;
	}





	public String failReason()
	{
		if(fails>1)
		{
			reason = " multiple errors have occured";
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

	public String[] arrOrg (String str)
	{
		String[] aO = new String[str.length()];

		for(int i = 0;i<str.length();i++)
		{
			 aO[i] = str.substring(i,i+1);
		}
			return aO;
		}



	}






