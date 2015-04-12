//import wcu.FileHandler;
import java.util.ArrayList;

import wcu.Database;
import wcu.IOController;


public class Launch {
	public static void main(String[] args) {
		//FileHandler fh = new FileHandler();
		//fh.checkIfIngredientExists();
		ArrayList<Database> vareDB = new ArrayList<Database>();
		ArrayList<Database> oprDB = new ArrayList<Database>();
		IOController io = new IOController();
		vareDB = io.readVareFil();
		oprDB = io.readOperatoerFil();
		for(int i = 0;i < vareDB.size();i++){
			if(vareDB.get(i).getRaavareNr() == 8) {
				System.out.println(vareDB.get(i).getRaavareNr() +" "+ vareDB.get(i).getRaavareNavn() +" "+ vareDB.get(i).getRaavareWeight());
			}
		}
		for(int i = 0;i < oprDB.size();i++){
			if(oprDB.get(i).getOprNr() == 42352) {
				System.out.println(oprDB.get(i).getOprNr() +" "+ oprDB.get(i).getOprName());
			}
		}
		
	}
	
}
