package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ftp.Exceptions.NoInputException;

public class newFTPClient implements IFTPClient{
	Socket socket;
	DataOutputStream output;
	BufferedReader input;
	BufferedWriter writer;
	@Override
	public void connect(String IP, int port) throws UnknownHostException, IOException, NoInputException {
		if (IP.isEmpty())
			throw new NoInputException();
		
		socket = new Socket(IP, port);
		output = new DataOutputStream(socket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
	}
	@Override
	public boolean login(String username, String password) throws IOException {
		try{
		sendLine("USER " + username);
		
		String response =  readLine();
		if(!response.startsWith("331 ")){
			throw new IOException("User error");
		}
		sendLine("PASS " + password);
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
		sendLine("PASV");
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
		    
		 sendLine("RETR " + filename);
		 Socket socket = new Socket(ip, port);
		 response= readLine();
		 if(!response.startsWith("125 ")){
			 throw new IOException("coundn't send file");
		 }
		 BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
		 try{
			copyStream(input, output);
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
			sendLine("QUIT");
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
	
	public void sendLine(String line) throws IOException{
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
	
	public void copyStream(InputStream input, OutputStream output){
		try{
			byte[] buffer = new byte[1024];
	        int bytesRead;
	        while((bytesRead=input.read(buffer))>0){
	            output.write(buffer,0,bytesRead);
	        }
	        output.close();
	        input.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void getList(InputStream input) throws IOException{
		sendLine("PASV");
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
		
		    
		sendLine("NLST");
		response = readLine();
		if(!response.startsWith("150 ")){
			throw new IOException("couldn't receive list");
		}
			
			ArrayList<String> list = new ArrayList();
	
			byte[] buffer = new byte[1024];
	        int bytesRead;
	        while((bytesRead=input.read(buffer))>0){
	            output.write(buffer,0,bytesRead);
	        }
	        output.close();
	        input.close();

	}
}
