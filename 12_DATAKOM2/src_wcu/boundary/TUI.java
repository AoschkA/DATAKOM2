package boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import boundary_ftp.IUI;

public class TUI implements IUI {
	private BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public void printMessage(String message) {
		System.out.println(message);	
	}

	@Override
	public String getResponse() throws IOException {
		return inFromUser.readLine();
	}
	
	@Override
	public int getIntResponse() throws IOException{
		int port = -1;
		try{
		port = Integer.parseInt(inFromUser.readLine());
		}catch (NumberFormatException e){
			System.out.println("Invalid Input");
			return getIntResponse();
		}
		return port;
	}
	
	
}
