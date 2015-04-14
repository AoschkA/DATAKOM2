package ftp;

import java.io.IOException;

import ftp.Exceptions.InvalidInputException;
import boundary_ftp.TUI;

public class IOController {
	 TUI TUI = new TUI();
	
	public String getStringInput() {
		String n = "";
		try {
		n = TUI.getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int getIntInput(){
		int n = 0;
		try {
		n = TUI.getIntResponse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}
		
	public int runClient(){
		int input = 0;
		TUI.startMessage();
		TUI.printTUIClient();
		try {
		switch(getMainMenu()) {
		case 1: input = 1;
				break;
		case 2: input = 2;
				break;
		case 3: input = 3;
				break;
		case 4: input = 4;
				break;
		default: throw new InvalidInputException();
		}} catch (InvalidInputException e) {
			TUI.printMessage("Invalid input");
			return getMainMenu();
		}
		
		return input;
	}
	
	public int getMainMenu() {
		String input = getStringInput();
		int output = 0;
		try {
		switch(input) {
		case "1": output = 1;
		break;
		case "2": output = 2;
		break;
		case "3": output = 3;
		break;
		case "4": output = 4;
		break;
		default: throw new InvalidInputException();
		} } catch (InvalidInputException e) {
			TUI.printMessage("Invalid input");
			return getMainMenu();
		} 
		return output;
	}
	public String getIP() throws IOException{
		TUI.connect_ip();
		return TUI.getResponse();
	}


	public int getPort() throws IOException {
		TUI.connect_port();
		return TUI.getIntResponse();
		
	}

	public String username() throws IOException {
		TUI.username();
		return TUI.getResponse();
		
	}
	
	public int getDestination() throws IOException {
		int n = TUI.getIntResponse();
		try {
		switch (n) {
		case 1: return 1;
		case 2: return 2;
		default: throw new InvalidInputException();
		}} catch (InvalidInputException e) {
			return getDestination();
		}
	}

	public String password() throws IOException {
		TUI.password();
		return TUI.getResponse();
		
	}

	public void getListOfFiles(String fileList) {
		System.out.println(fileList);
	}

}
