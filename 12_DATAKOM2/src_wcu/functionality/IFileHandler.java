package functionality;

import java.io.IOException;
import java.util.ArrayList;

import data.Database;

public interface IFileHandler {

	ArrayList<Database> readOperatoer();
	
	ArrayList<Database> readIngredient();
	
	void writeOperatoer();
	
	void writeIngredient();
	
	boolean checkIfOperatoerExixts() throws IOException;
	
	void checkIfIngredientExixts();
	
	void updateOperatoer();
	
	ArrayList<Database>updateIngredient();

}
