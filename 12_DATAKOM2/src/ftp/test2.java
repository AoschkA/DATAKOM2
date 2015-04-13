package ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public class test2 {
	static String response = "";
	static newFTPClient ftpC = new newFTPClient();
	
	public static void main(String args[]) throws UnknownHostException, IOException, NoInputException, InterruptedException{
		ftpC.connect("kaare8p2.noip.me",21);
		ftpC.login("toby","test");
		String remoteFile1 = "/PENIS2.txt";
        File downloadFile1 = new File("C:/Users/Juste/PENIS2.txt");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
		ftpC.retrieveFile(remoteFile1, outputStream1);
		outputStream1.close();
	}
	
}
