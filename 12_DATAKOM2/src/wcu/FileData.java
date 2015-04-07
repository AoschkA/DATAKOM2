package wcu;

import java.io.IOException;

public class FileData {
	public static void main(String[] args) throws IOException {

		//		Her defineres stien der skal læses fra:		
		//		String textfile = "C:/Users/Andreas/workspace/Datakommunikation/ReadFile/src/store.txt";
		String textfile = "something";

		try{
			ReadFile file = new ReadFile(textfile);
			String[] arraytext = file.OpenFile();

			for (int i = 0; i < arraytext.length; i++){
				System.out.println(arraytext[i]);
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}

		try{
			WriteFile data = new WriteFile(textfile, true);

			//		Her skrives teksten der skal gemmes
			data.writeTofileFile("OST");

			//			Her defineres stien der skal skrive til:			
			//			System.out.println("C:/Users/Andreas/workspace/Datakommunikation/ReadFile/src/store.txt");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}

