package Yashi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


// Class that drives everything.
public class Main {
	public static void main(String[] args) throws IOException {
		String file = "src/Yashi/Input.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		InputParser input=new InputParser();
		String curLine;
		while ((curLine = bufferedReader.readLine()) != null){
			input.parseTextInput(curLine);

			//	System.out.println(curLine);
		}
		bufferedReader.close();

	}
}