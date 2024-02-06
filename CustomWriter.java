import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CustomWriter
{
	private String scrambled, solutions;
	private PrintWriter pw;
	private FileWriter fw;


	public CustomWriter(String scram, String sols)
	{
		scrambled = scram;
		solutions = sols;


		File file1 = new File("CustomList.txt");
		 try {
			fw = new FileWriter(file1, true);
		} catch (IOException e) {

			e.printStackTrace();
		}
		 pw = new PrintWriter(fw);


	}

	public void writeGram()
	{
		pw.println(scrambled+""+solutions);
		pw.close();


	}

}