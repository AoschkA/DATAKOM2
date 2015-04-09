package wcu;

import java.io.IOException;

public class FileHandler {

	//	String kun til test
	//	String textfile = "something";
	
	//	Her defineres stien der skal læses fra/ eller skrives til.		
			String textfile = "C:/Users/Andreas/git/DATAKOM2/12_DATAKOM2/src/wcu/store.txt";
	
			public FileHandler() {
	}

	public void showReadFile(){
		
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
	}
	public void executeWriteFile(){

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
