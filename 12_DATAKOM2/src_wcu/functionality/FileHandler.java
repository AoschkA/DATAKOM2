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
	String current_operatoer_File = "data.text/operatoer.txt";
	String current_Ingredient_File = "data.text/store.txt";
	String temp_Ingredient_File = "data.text/temptext";
	BufferedReader br = null;
	BufferedWriter bw = null;
	String linje = "";
	int IngredientNr = 0;
	int oprNr = 0;
	double vareaendring = 0.123;

	@Override
	public ArrayList<Database> readOperatoer() {
		//		boolean exixts = false;
		//		opr_id skal være userinput.
		//		int opr_id = 0;
		Database nyOBasePost;
		ArrayList<Database> nyOBase = new ArrayList<Database>();
		try {
			br = new BufferedReader(new FileReader(current_operatoer_File));
			while((linje = br.readLine()) != null){
				String[] tempDB = linje.split(csv_Character);
				nyOBasePost = new Database();
				nyOBasePost.setOprNr(Integer.parseInt(tempDB[0]));
				nyOBasePost.setOprName(tempDB[1]);
				nyOBase.add(nyOBasePost);

				//			if(nyOBasePost.getOprNr() == opr_id){
				//				exixts = false;
			}
		}catch(Exception e){
			System.out.println("Show me I work!");
		}
		return nyOBase;

	}

	@Override
	public ArrayList<Database> readIngredient() {
		Database nyIBasePost;
		ArrayList<Database> nyIBase = new ArrayList<Database>();
		try {
			br = new BufferedReader(new FileReader(current_Ingredient_File));
			while((linje = br.readLine()) != null){
				String[] tempDB = linje.split(csv_Character);
				nyIBasePost = new Database();
				nyIBasePost.setRaavareNr(Integer.parseInt(tempDB[0]));
				nyIBasePost.setRaavareNavn(tempDB[1]);
				nyIBasePost.setRaavareWeight(tempDB[2]);
				nyIBase.add(nyIBasePost);
			}	
		}catch(Exception e){
			System.out.println("Show me I work!");
		}
		return nyIBase;
	}

	@Override
	public void writeOperatoer() {
		ArrayList<Database> oprDB = new ArrayList<Database>();
		FileHandler fh = new FileHandler();
		oprDB = fh.readOperatoer();

		try {
			BufferedWriter writer = 
					new BufferedWriter (new FileWriter(temp_Ingredient_File));
			for(int i = 0;i <oprDB.size();i++){
				writer.write(oprDB.get(i).getOprNr() +", "+ oprDB.get(i).getOprName() +", "+ "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	public boolean checkIfOperatoerExixts() throws IOException {
		boolean exixts;
		int count = 0;
		int opr_id = 10230; 
		ArrayList<Database> oprDB = new ArrayList<Database>();
		FileHandler fh = new FileHandler();
		oprDB = fh.readOperatoer();

		for(int i = 0;i <oprDB.size();i++){
			if(oprDB.get(i).getOprNr() == opr_id){
				count ++;
			}
		}
		if (count == 1){
			exixts = true;
			System.out.println("Godkendt");
		}
		else {
			exixts = false;
			System.out.println("ikke Godkendt");
		}
		return exixts;
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




