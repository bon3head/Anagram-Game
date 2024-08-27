//import java.io.FileWriter;
//import java.io.PrintWriter;
import java.util.Scanner;
//import java.util.*;
//import java.io.IOException;



public class AnagramGame{
	public static void main(String[] args) throws Exception
	{




		try (Scanner enter = new Scanner(System.in)) {
			System.out.println("\t\t\t****************************************************");
			System.out.println("\t\t\t\tANAGRAM GAME\n\n\t\t\t\tWwhich Version?");
			System.out.println("\t\t\t\t[1] Easy - Shorter words, 3 solutions");
			System.out.println("\t\t\t\t[2] Normal - Long words, one solution");
			System.out.println("\t\t\t\t[3] Hard - Mixed list of 1 & 2");
			System.out.println("\t\t\t\t[4] Custom - Play with your custom wordlist");
			System.out.println("\t\t\t\t[5] Add Custom - add words to a custom wordlist");
			System.out.println("\t\t\t\t[6] What's an anagram?");
			System.out.println("\t\t\t****************************************************");

//
			int choice = enter.nextInt();
			PlayGame one;
			if(choice==1){										//easy
				one = new PlayGame(1);
				one.play();
			}
			else if(choice==2)									//medium
			{
				one = new PlayGame(2);
				one.play();
			}
			else if(choice==3)									//mixed
			{
				one = new PlayGame(3);
				one.play();
			}
			else if(choice==4)
			{
				one = new PlayGame(4);
				one.play();
			}
			else if(choice==5)									//custom adder
			{
				one = new PlayGame(5);
			}
			else if(choice==6)									//definition
			{
				one = new PlayGame(6);
			}
			else
				System.out.println("die");

		}
	}
}





