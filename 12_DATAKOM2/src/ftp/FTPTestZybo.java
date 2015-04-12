package ftp;
import java.io.FileOutputStream;
import java.io.IOException;  
import java.net.SocketException;  

import org.apache.commons.net.ftp.FTPClient;  

  
public class FTPTestZybo {  
 public static void main(String args[]) {  
  
  // get an ftpClient object  
  FTPClient ftpClient = new FTPClient();  
  FileOutputStream fos = null;  
  
  try {  
   // pass directory path on server to connect  
	  ftpClient.connect("192.168.0.18", 21);
  
   // pass username and password, returned true if authentication is  
   // successful  
   boolean login = ftpClient.login("modbus", "336699");  

   if (login) {  
    System.out.println("Connection established... \n \n");  
   
    //Set folder to watch
    boolean change = ftpClient.changeWorkingDirectory("upload");
    if (change){
    	System.out.println("Working directory sucesfully changed \n");
    }
    else
    {
    	System.out.println("Working directory did not change");
    }
    
    //Files in folder
    String[] names = ftpClient.listNames();
    if (names.length > 0){
    	System.out.println("Content: ");
    for (String name : names) {
      System.out.println(name);
    }
    }
    else{
    	System.out.println("No content");
    }
    
    // Download file
    fos = new FileOutputStream("C:/Users/Alexander/Documents/download.txt");  
    boolean download = ftpClient.retrieveFile("newfile.txt",  
      fos);  
    if (download) {  
     System.out.println("File downloaded successfully !");  
    } else {  
     System.out.println("Error in downloading file !");  
    }  
    
    // logout the user, returned true if logout successfully  
    boolean logout = ftpClient.logout();  
    if (logout) {  
     System.out.println("\nConnection closed...");  
    }  
   } else {  
    System.out.println("\nConnection fail...");  
   }  
   
   
  
  } catch (SocketException e) {  
   e.printStackTrace();  
  } catch (IOException e) {  
   e.printStackTrace();  
  } finally {  
   try {  
    ftpClient.disconnect();  
   } catch (IOException e) {  
    e.printStackTrace();  
   }  
  }  
 }  
}