import java.util.Scanner;
//import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.awt.*;
import javax.swing.JPanel;


public class PlayGame extends JPanel
{

	ArrayList<Gram> list;
	ArrayList<String> mixLen, mixLenS;
	Scanner easySC, medSC, mixSC, insaneSC, customSC;
	String[] wordList;
	String scanLine;
	int count, streak = 0;

	boolean win;

	public PlayGame(int diff) throws IOException
	{
		try{
			customSC = new Scanner(new File("CustomList.txt"));
		 	easySC = new Scanner(new File("WordLibrary.txt"));
	     	medSC = new Scanner(new File("OneWordLibrary.txt"));
	      	mixSC = new Scanner(new File("MixLibrary.txt"));
	      	insaneSC = new Scanner(new File("wordlist.txt"));

        }
        catch (Exception ex){}

		list = new ArrayList<Gram>();
		fillList(diff);
	}

	public void fillList(int diff)
	{


		if(diff==1)//easy
		{
			while(easySC.hasNext())
			{
			scanLine = easySC.nextLine();
			wordList = scanLine.split(" ");

			list.add(new Gram(wordList[0], wordList[1], wordList[2], wordList[3]));

			count++;
			}
		}

		if(diff==2)//normal
		{
			while(medSC.hasNext())
			{
				scanLine = medSC.nextLine();
				wordList = scanLine.split(" ");

				list.add(new Gram(wordList[0], wordList[1]));

				count++;
			}
		}


		if(diff==3)//mixed
		{
			mixLenS = new ArrayList<String>();
			mixLen = new ArrayList<String>();
			String newWord="";

			while(medSC.hasNext())
			{
				scanLine = mixSC.nextLine();
				wordList = scanLine.split(" ");

				for(int i=1;i<wordList.length;i++)			// takes list of solutions of range {wordList[1],wordList[n]} and
				{											// arranges them into one String seperated by commas (Ex: rat,tar,art,)
					newWord+=","+wordList[i];
				}

				list.add(new Gram(wordList[0], newWord));

				count++;
			}
		}
		if(diff==4)//custom play
		{
			mixLenS = new ArrayList<String>();
			mixLen = new ArrayList<String>();
			String newWord="";

			while(customSC.hasNext())
			{
				scanLine = customSC.nextLine();
				wordList = scanLine.split(" ");

				for(int i=1;i<wordList.length;i++)			// takes list of solutions of range {wordList[1],wordList[n]} and
				{											// arranges them into one String seperated by commas (Ex: rat,tar,art,)
					newWord+=","+wordList[i];
				}

				list.add(new Gram(wordList[0], newWord));

				count++;
			}

		}
		if(diff==5)//custom add
		{
			try {
				addCustom();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}



		if(diff==6)
		{
			gameExplain();
		}


	}

	public void play()
	{

		int randomList = (int)(Math.random()*(count));
		Gram gameS = list.get(randomList);
		System.out.println("\n"+gameS.getScram());

		try (Scanner scanGuess = new Scanner(System.in)) {
			System.out.println("Enter a possbile anagram of the scrambled word:\nCurrent Streak: " + streak);
			String userGuess = scanGuess.nextLine();

			anagramChecker aC = new anagramChecker(gameS.getScram(), userGuess);

			if(userGuess.equals("DB"))						//Debug
			{
				System.out.println("listSize: " + list.size());
				System.out.println("count: " + count);
				System.out.println("randomList " + randomList);
				System.out.println("streak" + streak);
				play();

			}

			else if(aC.isAnagram())
			{
				list.remove(gameS);
				count--;
				if(count>=0)
				{ streak++; }

				System.out.println("\nCorrect! The word "+ userGuess + " is a valid solution.");

				System.out.println("***************************\nPlay Again?:\tY\tN\n***************************");
					try (Scanner playAgain = new Scanner(System.in)) {
						String stat = playAgain.nextLine(); //

						if(stat.indexOf("Y")>-1||stat.indexOf("y")>-1)
						{

							if(list.size()==0)															// size==0 means no more options
								System.out.println("All anagrams solved!");
							else
								play();
						}
						else if(stat.indexOf("N")>-1||stat.indexOf("n")>-1)
						{
							try {
								AnagramGame.main(wordList);
							} catch (Exception e) {

								e.printStackTrace();
							}

						}
					}

			}

			/*else if( gameS.isSolution(userGuess))
			{

				list.remove(gameS);																						// removes option
				count--;																								// prevents OOB in randomList
				if(list.size()>=0)
				{	streak++;}

				System.out.println("\nCorrect! The word "+ userGuess + " is a valid solution.");

				System.out.println("***************************\nPlay Again?:\tY\tN\n***************************");
				Scanner playAgain = new Scanner(System.in);
				String stat = playAgain.nextLine(); //

				if(stat.indexOf("Y")>-1||stat.indexOf("y")>-1)
				{

					if(list.size()==0)																					// true = no more options
						System.out.println("All anagrams solved!");
					else
						play();

				}
			}*/


			else
			{
				streak=0;

				System.out.println("You lose! " + "\""+userGuess+"\"" + " does not match a valid solution \n***************************\nPlay Again:\tY\tN\n***************************\n");

				try (Scanner playAgain = new Scanner(System.in)) {
					String stat = playAgain.nextLine();
				if(stat.indexOf("Y")>-1||stat.indexOf("y")>-1)
					{play();}
				else if(stat.indexOf("N")>-1||stat.indexOf("n")>-1)
				{
					try {
						AnagramGame.main(wordList);
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

				}


			}
		}
	}


	public void gameExplain()
	{
		System.out.println("\nAn"+"\u00B7"+"a"+"\u00B7"+"gram - a word, phrase, or name formed by rearranging the letters of another, such as cinema, formed from iceman.");
		System.out.println("\nGive it a try! Enter a word and then try to make an anagram of it.");
		try (Scanner scan1 = new Scanner(System.in)) {
		try (Scanner scan2 = new Scanner(System.in)) {

			System.out.println("Enter a word:");
				String wordX = scan1.nextLine();
				System.out.println("\nEnter a case-sensitive anagram of that word:");
				String wordY = scan2.nextLine();

				anagramChecker aC=new anagramChecker(wordX, wordY);							// Checks if WordY is a valid anagram of WordX
				if(aC.isAnagram()){
					System.out.println("\nCorrect! " + wordY + " is an angagram of " + wordX+"!");

					System.out.println("***************************\nPlay Again?:\tY\tN\n***************************");
					try (Scanner playAgain = new Scanner(System.in)) {
						String stat = playAgain.nextLine();

						if(stat.indexOf("Y")>-1||stat.indexOf("y")>-1)
						{
							gameExplain();											// runs method again if user agrees to play again
						}
						else if(stat.indexOf("N")>-1||stat.indexOf("n")>-1)
						{
								
							try {
								AnagramGame.main(wordList);
							} catch (Exception e) {
							
								e.printStackTrace();
							}
							
							
						}
					}
				}
				else
				{
					System.out.println("\nIncorrrect! " + wordY + " is not an anagram of " + wordX + " because" + aC.failReason()+"!");

					System.out.println("***************************\nPlay Again?:\tY\tN\n***************************");
					try (Scanner playAgain = new Scanner(System.in)) {
						String stat = playAgain.nextLine();

						if(stat.indexOf("Y")>-1||stat.indexOf("y")>-1)
						{
							gameExplain();
						}
						else if(stat.indexOf("N")>-1||stat.indexOf("n")>-1)
						{
								
							try {
								AnagramGame.main(wordList);
							} catch (Exception e) {
							
								e.printStackTrace();
							}
							
							
						}
					}
			}
		}
	}

	}

	public void addCustom() throws IOException
	{
		listPrint(new File("CustomList.txt"));


		System.out.println("Want to add an anagram? First, enter a word (scrambled or otherwise).\nNext, enter the case-sensitve solutions that will be guessed by the user.");
		Scanner cWordScan = new Scanner(System.in);
		Scanner cSolScan = new Scanner(System.in);
		Scanner doneScan = new Scanner(System.in);
				String cWord="";
				String lastWord = "";
			//	String cSol ="";
				int solAmt = 0;
				int i = 0;
				boolean valid = false;

				System.out.println("Enter word to be Anagrammed:");
					 cWord = cWordScan.nextLine();
				System.out.println("Now enter amount of solutions:");
					solAmt = doneScan.nextInt();


				System.out.println("Now enter valid anagrams of that word.");
				while(i<solAmt)
				{

					String oneSol = cSolScan.nextLine();

					anagramChecker customChk = new anagramChecker(cWord, oneSol);
					if(customChk.isAnagram())
					{
						lastWord+=" "+oneSol;
						i++;
						valid = true;
					}
					else if(!(customChk.isAnagram()))
					{
						valid = false;
						System.out.println("Selected word is not an anagram. Please enter a different one or check the spelling.");

					}

				}
				if(i==solAmt && valid )
				{
					CustomWriter cw = new CustomWriter(cWord, lastWord);
					cw.writeGram();
					System.out.println("Custom Anagram Created");
				}
				




	}

	public void listPrint(File file)
	{
		mixLen = new ArrayList<String>();


			try (Scanner fileSC = new Scanner(file)) {
				System.out.println("Custom List\nAnagram\t\tSolutions\n*******\t\t*********");

				while(fileSC.hasNext())
				{
					String newWord = "";
					scanLine = fileSC.nextLine();
					wordList = scanLine.split(" ");


					System.out.print(wordList[0]+":\t\t");

					for(int i=1;i<wordList.length;i++)			// takes list of solutions of range {wordList[1],wordList[n]} and
					{											// arranges them into one String seperated by commas (Ex: rat,tar,art,)
						newWord+=wordList[i]+"|";
					}

					System.out.println(newWord);

				}
				System.out.println();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

	}
}