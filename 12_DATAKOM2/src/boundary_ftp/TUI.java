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
	
	@Override
	public int getIntResponse() throws IOException{
		int n = -1;
		try{
		n = Integer.parseInt(inFromUser.readLine());
		}catch (NumberFormatException e){
			System.out.println("Invalid Input");
			return getIntResponse();
		}
		return n;
	}
	
	public void startMessage(){
		System.out.println("#########################");
		System.out.println("welcome to our ftp-client");
	}
	
	public void printTUIClient() {
		System.out.println("######################");
		System.out.println("Current options");
		System.out.println("1: Recive file from FTP server");
		System.out.println("2: Logout");
		System.out.println("3: Get list of files");
		System.out.println("4: Upload file");
	}
	
	public void connect_ip(){
		System.out.println("Please enter ip");
	}
	public void connect_port(){
		System.out.println("Please enter port to connect");
	}
	public void username(){
		System.out.println("Please enter Username");
	}
	public void password(){
		System.out.println("Please enter password");
	}
	public void failedConnected(){
		System.out.println("Failed to connect: wrong ip or port number");
	}
	public void failedToLogin() {
		System.out.println("Failed to Login: wrong username or password");
		
	}
}
