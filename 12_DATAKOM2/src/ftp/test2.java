package ftp;

import java.io.IOException;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public class test2 {
	static String response = "";
	static newFTPClient ftpC = new newFTPClient();
	
	public static void main(String args[]) throws UnknownHostException, IOException, NoInputException, InterruptedException{
		ftpC.connect("130.226.21.129",21);
		ftpC.login("modbus","336699");

		ftpC.writeLine("PASV");
		response = ftpC.readLine();
		System.out.println(response);
	}
	
}
