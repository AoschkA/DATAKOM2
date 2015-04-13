package ftp;

import java.io.IOException;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public class test2 {
	static newFTPClient ftpC = new newFTPClient();
	
	public static void main(String args[]) throws UnknownHostException, IOException, NoInputException, InterruptedException{
		ftpC.connect("130.226.21.129",21);
		ftpC.login("mondus","336699");
		ftpC.writeLine("TYPE I");
		String response = ftpC.readLine();
		System.out.println(response);
		ftpC.writeLine("PASV");
		response = ftpC.readLine();
		System.out.println(response);
		
	}
	
}
