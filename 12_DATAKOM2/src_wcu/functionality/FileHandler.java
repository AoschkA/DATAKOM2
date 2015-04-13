package functionality;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.Database;


public class FileHandler implements IFileHandler {
	String csv_Character = ",";
	String current_Ingredient_File = "data.text/store.txt";
	String temp_Ingredient_File = "data.text/temptext";
	BufferedReader br = null;
	BufferedWriter bw = null;
	String linje = "";
	int IngredientNr = 0;
	int oprNr = 0;
	double vareaendring = 0.123;

	@Override
	public void readOperatoer() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Database> readIngredient() {
		Database nyBasePost;
		ArrayList<Database> nyBase = new ArrayList<Database>();
		try {
			br = new BufferedReader(new FileReader(current_Ingredient_File));
			while((linje = br.readLine()) != null){
				String[] tempDB = linje.split(csv_Character);
				nyBasePost = new Database();
				nyBasePost.setRaavareNr(Integer.parseInt(tempDB[0]));
				nyBasePost.setRaavareNavn(tempDB[1]);
				nyBasePost.setRaavareWeight(tempDB[2]);
				nyBase.add(nyBasePost);
			}	
		}catch(Exception e){
			System.out.println("Show me I work!");
		}
		return nyBase;
	}

	@Override
	public void writeOperatoer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeIngredient() {
		ArrayList<Database> vareDB = new ArrayList<Database>();
		FileHandler fh = new FileHandler();
		vareDB = fh.updateIngredient();

		try {
			BufferedWriter writer = 
					new BufferedWriter (new FileWriter(temp_Ingredient_File));
			for(int i = 0;i < vareDB.size();i++){
				writer.write(vareDB.get(i).getRaavareNr() +", "+ vareDB.get(i).getRaavareNavn() +", "+ vareDB.get(i).getRaavareWeight() +"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void checkIfOperatoerExixts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkIfIngredientExixts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOperatoer() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Database> updateIngredient() {
		ArrayList<Database> nyVareDB = new ArrayList<Database>();
		FileHandler fh = new FileHandler();
		nyVareDB = fh.readIngredient();
		
		for(int i = 0;i < nyVareDB.size();i++){
//			sc skal være userinput:
			int sc = 8;
			if(nyVareDB.get(i).getRaavareNr() == sc) {

				IngredientNr = i;

				Database nyAendring = new Database();
				
				nyAendring.setRaavareNr(nyVareDB.get(IngredientNr).getRaavareNr());
				nyAendring.setRaavareNavn(nyVareDB.get(IngredientNr).getRaavareNavn());
				nyAendring.setRaavareWeight(String.valueOf(Double.parseDouble(nyVareDB.get(IngredientNr).getRaavareWeight())-vareaendring));
								
				nyVareDB.set(IngredientNr, nyAendring);
				
			}
		
		}
		return nyVareDB;
	}
}




