package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class WeightCommunicator {
	// opret forbindelse til vægt
	private int portdst = 8000;
	private Socket sock ;
	private BufferedReader instream ; 
	private DataOutputStream outstream ; 
	private int opr_nr , raavare_nr, raavare_weight; 
	private String raavare_navn, opr_name,
	filenameStore = "store.txt",
	logfile = "log.txt";

	public void run ( int port ) {
		
	}
	
	public WeightCommunicator() {
		// TODO Auto-generated constructor stub
	}

}
