package ftp;

import java.io.IOException;

import boundary_ftp.TUI;

public class IOControllerFTP {
	 TUI TUI = new TUI();


	
	public String getStringInput() {
		String input = "";
		try{
			input = TUI.getResponse();
		} catch (IOException e) {
			TUI.printMessage("No input detected");
			return getStringInput();
		}
		return input;
	}
	
	public int getIntInput() {
		int output=0;
		String input = getStringInput();
		try{
			output = Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			TUI.printMessage("Couldn't recognize the input");
			return getIntInput();
		}
		
		return output;	
	}
	
	
	
	public int runClient(){
		boolean run=true;
		int cunt = 0;
		TUI.startMessage();
		while(run){
		TUI.printTUIClient();
		switch(getMainMenu()) {
		case 1: cunt = 1;
				run =false;
				break;
		case 2: cunt = 2;
				run = false;
				break;
		case 3: cunt = 3;
				run=false;
				break;
		case 4: cunt = 4;
				run = false;
				break;
		case 5: System.out.println("no idea what to do");
				break;
		case 6: System.out.println("no idea what to do");
				break;
		}}
		
		return cunt;
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
		case "5": output = 5;
		break;
		case "6": output = 6;
		break;
		default: throw new IOException();
		} } catch (IOException e) {
			TUI.printMessage("Couldn't recognize the input");
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

	public String password() throws IOException {
		TUI.password();
		return TUI.getResponse();
		
	}

	public void getListOfFiles(String fileList) {
		System.out.println(fileList);
	}

}
