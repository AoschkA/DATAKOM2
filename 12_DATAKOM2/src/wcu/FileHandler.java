package wcu;

import java.io.IOException;

public class FileHandler {


	//	Her defineres stien der skal læses fra/ eller skrives til.	

	String operatoerTextFile = "data.text/operatoer.txt";
	String storeTextFile = "data.text/store.txt";
	String testTextFile = "data.text/test";
	public FileHandler() {
	}

	public void readOperatoerFile(){

		try{
			ReadFile file = new ReadFile(operatoerTextFile);
			String[] arraytext = file.OpenFile();

			for (int i = 0; i < arraytext.length; i++){
				System.out.println(arraytext[i]);
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void readStoreFile(){

		try{
			ReadFile file = new ReadFile(storeTextFile);
			String[] arraytext = file.OpenFile();

			for (int i = 0; i < arraytext.length; i++){
				System.out.println(arraytext[i]);
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void writeToOperatoerFile(){

		try{
			WriteFile data = new WriteFile(operatoerTextFile, true);

			//		Her skrives teksten der skal gemmes
			data.writeTofileFile("OST");

		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void writeToStoreFile(){

		try{
			WriteFile data = new WriteFile(storeTextFile, true);

			//		Her skrives teksten der skal gemmes
			data.writeTofileFile("OST");

		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void writeToTestFile(){

		try{
			
//			Hvis argumentet er true skrives teksten efter den tekst der står der i forvejen,
//			hvis argumentet er false overskriver den hele teksten
			
			WriteFile data = new WriteFile(testTextFile, false);

//			Her skrives teksten der skal gemmes
			data.writeTofileFile("OST");

		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	public void checkIfOperatorExists(){

		boolean Oexists = false;
		try{
			ReadFile file = new ReadFile(operatoerTextFile);
			String[] arraytext = file.OpenFile();
			int count = 0;
			
//			Brugeren skal indtaste sc selv.
			int sc = 10231;
			for (int i = 0; i < arraytext.length; i++){
				String word = arraytext[i];
				String [] wordsinline = word.split(",");

				if (sc == Integer.parseInt(wordsinline[0])) {
					count ++;
				}
			}
			if(count==1){
				Oexists = true;
				System.out.println("Operatoer Godkendt");
			}
			else {
				System.out.println("Operatoer findes ikke");
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}	
	public void checkIfIngredientExists(){

		boolean Iexists = false;
		try{
			ReadFile file = new ReadFile(storeTextFile);
			String[] arraytext = file.OpenFile();
			int count = 0;
			
//			Brugeren skal indtaste varenummer selv.
			int varenummer = 16;
			for (int i = 0; i < arraytext.length; i++){
				String word = arraytext[i];
				String [] wordsinline = word.split(",");

				if (varenummer == Integer.parseInt(wordsinline[0])) {
					count ++;
				}
			}
			if(count==1){
				Iexists = true;
				System.out.println("Varen Findes");
			}
			else {
				System.out.println("Varen Findes Ikke");
			}
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}	
}

