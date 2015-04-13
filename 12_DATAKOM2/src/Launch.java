
////import wcu.FileHandler;
//import java.util.ArrayList;
//
//import control.IOController;
//import data.Database;
//
//
//public class Launch {
//	public static void main(String[] args) {
//		//FileHandler fh = new FileHandler();
//		//fh.checkIfIngredientExists();
//		ArrayList<Database> vareDB = new ArrayList<Database>();
//		ArrayList<Database> oprDB = new ArrayList<Database>();
//		IOController io = new IOController();
//		vareDB = io.readVareFil();
//		oprDB = io.readOperatoerFil();
//		int rigtigvare = 0;
//		int rigtigoperatoer = 0;
//		double vareaendring = 0.12;
//		for(int i = 0;i < vareDB.size();i++){
//			if(vareDB.get(i).getRaavareNr() == 8) {
//				System.out.println(vareDB.get(i).getRaavareNr() +" "+ vareDB.get(i).getRaavareNavn() +" "+ vareDB.get(i).getRaavareWeight());
//				rigtigvare = i;
//			}
//		}
//		for(int i = 0;i < oprDB.size();i++){
//			if(oprDB.get(i).getOprNr() == 42352) {
//				System.out.println(oprDB.get(i).getOprNr() +" "+ oprDB.get(i).getOprName());
//				rigtigoperatoer = i;
//			}
//		}
//		System.out.println("Vare fundet p� plads " + rigtigvare);
//		System.out.println("Operat�r fundet p� plads " + rigtigoperatoer);
//		
//		System.out.println("S� lad os �ndre v�gten p� den vare: ");
//		Database nyAendring = new Database();
//		nyAendring.setRaavareWeight(String.valueOf(Double.parseDouble(vareDB.get(rigtigvare).getRaavareWeight())-vareaendring));
//		vareDB.set(rigtigvare, nyAendring);
//		System.out.println(vareDB.get(rigtigvare).getRaavareWeight());
//	}
//	
//}
