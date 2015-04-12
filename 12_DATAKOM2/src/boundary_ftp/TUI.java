package boundary_ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
	

}
