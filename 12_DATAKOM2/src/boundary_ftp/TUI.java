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
		int port = Integer.parseInt(inFromUser.readLine());
		return port;
	}
	
	public void startMessage(){
		System.out.println("#########################");
		System.out.println("welcome to out ftp-client");
		System.out.println("#########################");
	}
	
	public void printTUIClient() {
		System.out.println("######################");
		System.out.println("Current options");
		System.out.println("1: recive file from FTP server");
		System.out.println("2 send command to zybo-board");
		System.out.println("3 send command to zybo-board");
		System.out.println("4 send command to zybo-board");
		System.out.println("5 send command to zybo-board");
		System.out.println("6 send command to zybo-board");
	}
	
	public void connect_ip(){
		System.out.println("please enter ip");
	}
	public void connect_port(){
		System.out.println("please enter port to connect");
	}
	public void username(){
		System.out.println("please enter Username");
	}
	public void password(){
		System.out.println("please enter password");
	}
	public void failedConnected(){
		System.out.println("failed");
	}
}
