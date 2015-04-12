//import wcu.FileHandler;
import java.util.ArrayList;

import wcu.Database;
import wcu.IOController;


public class Launch {
	public static void main(String[] args) {
		//FileHandler fh = new FileHandler();
		//fh.checkIfIngredientExists();
		ArrayList<Database> vareDB = new ArrayList<Database>();
		IOController io = new IOController();
		vareDB = io.readVareFil();
		for(int i = 0;i < vareDB.size();i++){
			System.out.println(vareDB.get(i).getRaavareNr() +" "+ vareDB.get(i).getRaavareNavn() +" "+ vareDB.get(i).getRaavareWeight());
		}
		
	}
	
}
