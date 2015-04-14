package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import boundary_ftp.TUI;
import ftp.Exceptions.InvalidInputException;
import ftp.Exceptions.NoInputException;

public class newFTPClient implements IFTPClient{
	Socket socket;
	DataOutputStream output;
	InputStream input;
	BufferedReader reader;
	TUI tui = new TUI();
	IOControllerFTP ioC = new IOControllerFTP();

	

	@Override
	public boolean connect(String ip, int port) throws UnknownHostException, IOException, NoInputException {
		if (ip.isEmpty()){
			throw new NoInputException();
		}

		socket = new Socket(ip, port);
	    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    output = new DataOutputStream(socket.getOutputStream());
		return true;
		
	}
	@Override
	public boolean login(String username, String password) throws IOException {
		try{
		String response = readLine();
		System.out.println(response);
			writeLine("USER " + username);
			response = readLine();
			System.out.println(response);
			if(!response.startsWith("331 ")){
				throw new IOException("User error");
			}
			writeLine("PASS " + password);
			response = readLine();
			System.out.println(response);
			if(!response.startsWith("230 ")){
				throw new IOException("Password error");
			}
		return true;
		} catch (Exception e){
			return false;
		}
	}
	@Override
	public boolean retrieveFile(String filename, OutputStream output) throws IOException {
		
		writeLine("TYPE I");
		String response = readLine();
		if(response.startsWith("200 "))	{
		writeLine("PASV");
		response = readLine();
		if(!response.startsWith("227 ")){
			throw new IOException("couldn't enter passive mode" + response);
		}
		
		  String ip = null;
		    int port = -1;
		    int opening = response.indexOf('(');
		    int closing = response.indexOf(')', opening + 1);
		    if (closing > 0) {
		      String dataLink = response.substring(opening + 1, closing);
		      StringTokenizer tokenizer = new StringTokenizer(dataLink, ",");
		      try {
		        ip = tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
		            + tokenizer.nextToken() + "." + tokenizer.nextToken();
		        port = Integer.parseInt(tokenizer.nextToken()) * 256
		            + Integer.parseInt(tokenizer.nextToken());
		      } catch (Exception e) {
		        throw new IOException("couldn't connect probaly to:"
		            + response);
		      }
		    }
		 Socket socket = new Socket(ip, port);
		 writeLine("RETR " + filename);
		 
		 response= readLine();
		 System.out.println(response);
		 if(!response.startsWith("150 ")){
			 throw new IOException("coundn't retrieve file" + response);
		 }
		 DataInputStream input = new DataInputStream(socket.getInputStream());
		 
	
			 byte[] buffer = new byte[1024];
		        int bytesRead;
		        while((bytesRead=input.read(buffer))>0){
		        	output.write(buffer,0,bytesRead);
		        }
		        output.flush();
		        socket.close();
		        return true;
		}
		return false;
		
	}
	@Override
	public boolean logout() {
		try{
			writeLine("QUIT");
			String response = readLine();
			
			if(!response.startsWith("221 ")){
				throw new IOException("User not logged out");
			}else{
				System.out.println("User has been disconnected");
			}
		}catch (Exception e){
			System.out.println("couldn't log out");
		}
		return false;
	}

	
	public void writeLine(String line) throws IOException{
		if(socket == null){
			throw new IOException("ikke connected");
		}
		try{
			output.writeBytes(line + "\n");
			output.flush();
		}catch(IOException e){
			socket = null;
			throw e;
		}
	}
	
	public String readLine() throws IOException{
		String line = reader.readLine();
		return line;
	}

	public String getList() throws IOException, InterruptedException{
		try{
			writeLine("TYPE I");
			String response = readLine();
			if(response.startsWith("200 "))	{
			writeLine("PASV");
			 response = readLine();
			if(!response.startsWith("227 ")){
				throw new IOException("couldn't enter passive mode: " + response);
			}
			
				String ip = null;
			    int port = -1;
			    int opening = response.indexOf('(');
			    int closing = response.indexOf(')', opening + 1);
			    if (closing > 0) {
			      String dataLink = response.substring(opening + 1, closing);
			      StringTokenizer tokenizer = new StringTokenizer(dataLink, ",");
			      try {
			        ip = tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
			            + tokenizer.nextToken() + "." + tokenizer.nextToken();
			        port = Integer.parseInt(tokenizer.nextToken()) * 256
			            + Integer.parseInt(tokenizer.nextToken());
			      } catch (Exception e) {
			        throw new IOException("couldn't connect probaly to:"
			            + response);
			      }
			    }
			
			Socket socket = new Socket(ip, port); 
			writeLine("LIST -R");
			response = readLine();
			if(!response.startsWith("150 ")){
				throw new IOException("couldn't receive list");
			}
				
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n"  );
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			socket.close();
	    	return sb.toString();
			}}catch(IOException e){
				e.printStackTrace();
			}
		
		return "no list retrieve ";
	}
	public void connectAndLogin() throws IOException, NoInputException, InterruptedException{
		String ip = null;
		int port = 0;
		String user = null;
		String pass = null;
		boolean run = true;
		ip = ioC.getIP();
		port = ioC.getPort();
		if(port == -1){
			System.exit(0);
		}
		user = ioC.username();
		pass = ioC.password();
		if(connect(ip,port) == false){
			tui.failedConnected();
		}
		if(login(user, pass) == true){
			while(run){
				switch(ioC.runClient()){
					case 1:	retrieveChoosenFile();
					break;
					case 2: logout();
					run = false;
					break;
					case 3: ioC.getListOfFiles(getList());
					break;
					case 4: sendChoosenFile();
					break;
					}		
		}}
		else{
			tui.failedConnected();
		}
		
	}
	private void retrieveChoosenFile() throws IOException {
		boolean checkSucces;
		tui.printMessage("Please enter the name of the file you wish to download");
		tui.printMessage("Or type 'back' to go back to the main menu");
		String fileToDownload = ioC.getStringInput();
		if (!fileToDownload.equals("back")){
			tui.printMessage("Press '1': Choose the download location");
			tui.printMessage("Press '2': Download the file to your user folder");
			int n = ioC.getDestination();
		if (n==1) {
			tui.printMessage("Enter the desired download location");
			File downloadFile1 = new File(ioC.getStringInput() + "/" + fileToDownload);
	        OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
	        checkSucces = retrieveFile(fileToDownload, outputStream1);
			outputStream1.close();
			if(checkSucces == true){
				tui.printMessage("The download was succesfull");
			}else {
				tui.printMessage("The download failed");
			}
		}
		if (n==2){
			tui.printMessage("Enter the name of your user folder");
			File downloadFile1 = new File("C:/Users/" + ioC.getStringInput() + "/" + fileToDownload);
			OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
			checkSucces =retrieveFile(fileToDownload, outputStream1);
			outputStream1.close();
			if(checkSucces == true){
				tui.printMessage("The download was succesfull");
			}else {
				tui.printMessage("The download failed");
			}
		}
		} 
		
	}
	public void newFTPClient() throws IOException, NoInputException, InterruptedException {
		connectAndLogin();
	}
		
	public void sendChoosenFile() throws IOException{
		boolean checkSucces;
		tui.printMessage("Please enter the name of the file you wish to upload");
		tui.printMessage("Or type 'back' to go back to the main menu");
		String fileToUpload = "/" + ioC.getStringInput();
		if (!fileToUpload.equals("/back")){
			tui.printMessage("Press '1': Enter the location of the folder");
			tui.printMessage("Press '2': If the file is in user folder, enter name of the folder");
			int n = ioC.getDestination();
		if (n==1) {
			tui.printMessage("Enter the path of the file");
			File uploadFile1 = new File(ioC.getStringInput() + fileToUpload);
			checkSucces = send(uploadFile1);
			if(checkSucces == true){
				tui.printMessage("The upload was succesfull");
			}else {
				tui.printMessage("The upload failed");
			}
		}
		if (n==2){
			tui.printMessage("Enter the name of your user folder");
			File uploadFile1 = new File("C:/Users/" + ioC.getStringInput() + fileToUpload);
			checkSucces = send(uploadFile1);
			if(checkSucces == true){
				tui.printMessage("The upload was succesfull");
			}else {
				tui.printMessage("The upload failed");
			}
		}
		} 
	}
	public boolean send(File file) throws IOException {
	    String filename = file.getName();
	    tui.printMessage(filename);
	    return sendFile(new FileInputStream(file), filename);
	  }
	
	@Override
	public boolean sendFile(InputStream inputStream, String filename) throws IOException {
		  
			    BufferedInputStream input = new BufferedInputStream(inputStream);

			    writeLine("PASV");
			    String response = readLine();
			    if (!response.startsWith("227 ")) {
			      throw new IOException("could not enter passive mode: " + response);
			    }

			    String ip = null;
			    int port = -1;
			    int opening = response.indexOf('(');
			    int closing = response.indexOf(')', opening + 1);
			    if (closing > 0) {
			      String dataLink = response.substring(opening + 1, closing);
			      StringTokenizer tokenizer = new StringTokenizer(dataLink, ",");
			      try {
			        ip = tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
			            + tokenizer.nextToken() + "." + tokenizer.nextToken();
			        port = Integer.parseInt(tokenizer.nextToken()) * 256
			            + Integer.parseInt(tokenizer.nextToken());
			      } catch (Exception e) {
			        throw new IOException("wasn't able to connect: " + response);
			      }
			    }
			    Socket socket = new Socket(ip, port);
			    
			    tui.printMessage(filename);
			    writeLine("STOR " + filename);

			    
			    response = readLine();
			    if (!response.startsWith ("150 ")) {
			      throw new IOException("couldn't send the file: "+ response);
			    }

			    BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
			    byte[] buffer = new byte[4096];
			    int bytesRead = 0;
			    while ((bytesRead = input.read(buffer)) != -1) {
			    	output.write(buffer, 0, bytesRead);
			    }
			    output.flush();
			    output.close();
			    input.close();
			    socket.close();
			    response = readLine();
			    tui.printMessage(response);
			    return response.startsWith("226 ");
			  }
	
}

