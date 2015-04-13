//package control;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//
//import data.Database;
//
//public class IOController {
//	String csv_Character = ",";
//	String current_Database_Fil = "data.text/store.txt";
//	BufferedReader br = null;
//	String linje = "";
//	
//	public IOController() {
//		// TODO Auto-generated constructor stub
//	}
//	public ArrayList<Database> readVareFil(){
//		
//		Database nyBasePost;
//		ArrayList<Database> nyBase = new ArrayList<Database>();
//		try {
//			br = new BufferedReader(new FileReader(current_Database_Fil));
//			while((linje = br.readLine()) != null){
//				String[] tempDB = linje.split(csv_Character);
//				nyBasePost = new Database();
//				nyBasePost.setRaavareNr(Integer.parseInt(tempDB[0]));
//				nyBasePost.setRaavareNavn(tempDB[1]);
//				nyBasePost.setRaavareWeight(tempDB[2]);
//				nyBase.add(nyBasePost);
//			}	
//		}catch(Exception e){
//			System.out.println("Show me I work!");
//		}
//		return nyBase;
//	}
//	public ArrayList<Database> readOperatoerFil(){
//		
//		Database nyBasePost;
//		ArrayList<Database> nyBase = new ArrayList<Database>();
//		try {
//			current_Database_Fil = "data.text/operatoer.txt";
//			br = new BufferedReader(new FileReader(current_Database_Fil));
//			while((linje = br.readLine()) != null){
//				String[] tempDB = linje.split(csv_Character);
//				nyBasePost = new Database();
//				nyBasePost.setOprNr(Integer.parseInt(tempDB[0]));
//				nyBasePost.setOprName(tempDB[1]);
//				nyBase.add(nyBasePost);
//			}	
//		}catch(Exception e){
//			System.out.println("Show me I work!");
//		}
//		return nyBase;
//	}
//}
