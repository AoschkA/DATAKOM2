package functionality;

import java.util.ArrayList;

import data.Database;

public interface IFileHandler {
void readOperatoer();
	
	ArrayList<Database> readIngredient();
	
	void writeOperatoer();
	
	void writeIngredient();
	
	void checkIfOperatoerExixts();
	
	void checkIfIngredientExixts();
	
	void updateOperatoer();
	
	ArrayList<Database>updateIngredient();

}
