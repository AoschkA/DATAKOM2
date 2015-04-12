package ftp;

import java.io.IOException;

import boundary_ftp.TUI;

public class IOControllerFTP {
	private TUI TUI = new TUI();
	
	public IOControllerFTP(){
		runClient();
	}
	
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
	
	
	public void startMessage(){
		TUI.printMessage("#########################");
		TUI.printMessage("welcome to out ftp-client");
		TUI.printMessage("#########################");
	}
	
	private void printTUIClient() {
		TUI.printMessage("######################");
		TUI.printMessage("Current options");
		TUI.printMessage("1: recive file from FTP server");
		TUI.printMessage("2 send command to zybo-board");
		TUI.printMessage("3 send command to zybo-board");
		TUI.printMessage("4 send command to zybo-board");
		TUI.printMessage("5 send command to zybo-board");
		TUI.printMessage("6 send command to zybo-board");
		
	}
	
	public void runClient(){
		boolean run=true;
		startMessage();
		while(run){
		printTUIClient();
		switch(getMainMenu()) {
		case 1: System.out.println("recive file");
				break;
		case 2: System.out.println("no idea what to do");
				break;
		case 3: System.out.println("no idea what to do");
				break;
		case 4: System.out.println("no idea what to do");
				break;
		case 5: System.out.println("no idea what to do");
				break;
		case 6: System.out.println("no idea what to do");
				break;
		case 7: run=false;
				break;
		}}
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
		case "7": output = 7;
		break;
		default: throw new IOException();
		} } catch (IOException e) {
			TUI.printMessage("Couldn't recognize the input");
			return getMainMenu();
		} 
		return output;
	}
	
	public void printListOfFiles(){
		
	}
	public void IOControllerFTP() {
		// TODO Auto-generated method stub
		
	}
}
