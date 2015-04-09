package ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConnector {
	FTPClient FTPClient = new FTPClient();
	FileOutputStream fos = null;
	
	public void DownloadFile(){
		try{
		FTPClient.connect(""); 
		boolean login = FTPClient.login("username", "password");
		
		if (login) {
			System.out.println("Connection established");
			
			fos = new FileOutputStream("files/downloadedFile.txt");
			boolean download = FTPClient.retrieveFile("uploadedFile.txt", fos);
			
			if (download) {
				System.out.println("File downloaded succesfully");
			} else {
				System.out.println("Error in downloading file");
			}
			
			boolean logout = FTPClient.logout();
			if (logout) {
				System.out.println("Connection closed");
			}} 
		else {
				System.out.println("Connection failed");
		}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				FTPClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
