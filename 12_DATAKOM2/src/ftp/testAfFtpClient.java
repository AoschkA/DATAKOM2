package ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public class testAfFtpClient {
	static FTPClient ftpC = new FTPClient();
	
	public static void main(String args[]) throws UnknownHostException, IOException, NoInputException, InterruptedException{
		String ip = "130.226.21.129";
		int port = 21;
		String User = "modbus";
		String Password = "336699";
		
		//Connect and login
		System.out.println("------------------");
		ftpC.connect(ip,port);
		ftpC.login(User,Password);
		System.out.println("------------------");
		
		//Upload
		System.out.println("------------------");
		String fileToUpload = "/Mads.txt";
        File uploadFile = new File("C:/Users/Mads/" + fileToUpload);
        String name = uploadFile.getName();
        ftpC.sendFile(new FileInputStream(uploadFile), name);
        System.out.println("------------------");
        
        System.out.println("------------------");
        fileToUpload = "/Jonas.txt";
        uploadFile = new File("C:/Users/Mads/" + fileToUpload);
        name = uploadFile.getName();
        ftpC.sendFile(new FileInputStream(uploadFile), name);
        System.out.println("------------------");
        
        //Download
        System.out.println("------------------");
		String fileToDownload = "/charles.txt";
        File downloadFile = new File("C:/Users/Mads/charles.txt");
        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
		ftpC.retrieveFile(fileToDownload, outputStream1);
		outputStream1.close();
		System.out.println("------------------");
		
		System.out.println("------------------");
		fileToDownload = "/alexander.txt";
        downloadFile = new File("C:/Users/Mads/alexander.txt");
        outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
		ftpC.retrieveFile(fileToDownload, outputStream1);
		outputStream1.close();
		System.out.println("------------------");
		
		System.out.println("------------------");
		fileToDownload = "/strange.txt";
        downloadFile = new File("C:/Users/Mads/strange.txt");
        outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile));
		ftpC.retrieveFile(fileToDownload, outputStream1);
		outputStream1.close();
		System.out.println("------------------");
		
		//GetList
		System.out.println("------------------");
		ftpC.getList();
		System.out.println(ftpC.list);
		System.out.println("------------------");
	}
	
}
