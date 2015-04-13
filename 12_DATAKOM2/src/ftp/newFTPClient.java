package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import boundary_ftp.TUI;
import ftp.Exceptions.NoInputException;

public class newFTPClient implements IFTPClient{
	Socket socket;
	DataOutputStream output;
	BufferedReader input;
	BufferedWriter writer;
	InputStream input2;
	TUI tui;
	IOControllerFTP ioC = new IOControllerFTP();
	

	@Override
	public boolean connect(String IP, int port) throws UnknownHostException, IOException, NoInputException {
		if (IP.isEmpty()){
			throw new NoInputException();
		}
		socket = new Socket(IP, port);
		output = new DataOutputStream(socket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		return true;
		
	}
	@Override
	public boolean login(String username, String password) throws IOException {
		try{
		writeLine("USER " + username);
		
		String response =  readLine();
		if(!response.startsWith("331 ")){
			throw new IOException("User error");
		}
		writeLine("PASS " + password);
		response = readLine();
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
		writeLine("PASV");
		String response = readLine();
		if(!response.startsWith("227 ")){
			throw new IOException("couldn't enter passive mode");
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
		    
		 writeLine("RETR " + filename);
		 Socket socket = new Socket(ip, port);
		 response= readLine();
		 if(!response.startsWith("125 ")){
			 throw new IOException("coundn't send file");
		 }
		 BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
		 try{
			 byte[] buffer = new byte[1024];
		        int bytesRead;
		        while((bytesRead=input.read(buffer))>0){
		            output.write(buffer,0,bytesRead);
		        }
		 }
		 finally
		 {
			input.close();
			socket.close();
		 }
		return false;
	}
	@Override
	public boolean logout() {
		try{
			writeLine("QUIT");
			String response = readLine();
			
			if(!response.startsWith("231")){
				throw new IOException("User not logged out");
			}else{
				System.out.println("User has been disconnected");
			}
		}catch (Exception e){
			System.out.println("couldn't log out");
		}
		return false;
	}
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	public void writeLine(String line) throws IOException{
		if(socket == null){
			throw new IOException("ikke connected");
		}
		try{
			writer.write(line + "\r\n");
			writer.flush();
		}catch(IOException e){
			socket = null;
			throw e;
		}
	}
	
	public String readLine() throws IOException{
		String line = input.readLine();
		return line;
	}
	
	public String getList(InputStream input2) throws IOException{
		writeLine("PASV");
		String response = readLine();
		if(!response.startsWith("227 ")){
			throw new IOException("couldn't enter passive mode");
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
		
		    
		writeLine("NLST");
		response = readLine();
		if(!response.startsWith("150 ")){
			throw new IOException("couldn't receive list");
		}
			
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(input2));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println("shits gone gray");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        input.close();  
    	return sb.toString();
	}
	public void connectAndLogin() throws IOException, NoInputException{
		String ip = null;
		int port = 0;
		String user = null;
		String pass = null;
		boolean run = true;
		ioC.getIP();
		if(ioC.getPort() == -1){
			System.exit(0);
		}
		ioC.username();
		ioC.password();
		if(connect(ip, port) == false){
			tui.failedConnected();
		}
		if(login(user, pass) == true){
				switch(ioC.runClient()){
				case 1:	ioC.getListOfFiles(getList(input2));
//				case 2: doRandomShit
//				case 3: doRandomShit2
				}		
		}
		else{
			tui.failedConnected();
		}
		
	}
	public void newFTPClient() throws IOException, NoInputException {
		connectAndLogin();
	}
}
