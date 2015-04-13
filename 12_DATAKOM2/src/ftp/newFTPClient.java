package ftp;

import java.io.BufferedReader;
import java.io.DataInputStream;
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
		try{
		writeLine("TYPE I");
		String response = readLine();
		if(response.startsWith("200 "))	{
		writeLine("PASV");
		response = readLine();
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
		 Socket socket = new Socket(ip, port);
		 writeLine("RETR " + filename);
		 
		 response= readLine();
		 System.out.println(response);
		 if(!response.startsWith("150 ")){
			 throw new IOException("coundn't send file");
		 }
		 DataInputStream input = new DataInputStream(socket.getInputStream());
		 System.out.println("ad");
		 
		 try{
			 byte[] buffer = new byte[1024];
		        int bytesRead;
		        while((bytesRead=input.read(buffer))>0){
		            output.write(buffer,0,bytesRead);
		            System.out.println("a");
		        }
		        input.close();
		 }catch(IOException e){
			 input.close();
			 System.out.println("fuck this shit");
			 }}
		 }
		 finally
		 {
			 
			
			socket.close();
		 }
		return false;
	}
	@Override
	public boolean logout() {
		try{
			writeLine("QUIT");
			String response = readLine();
			
			if(!response.startsWith("221")){
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
	    	return sb.toString();
			}}catch(IOException e){
				System.out.println("what the fuck");
			}
		return "shits gray";
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
				switch(ioC.runClient()){
					case 1:	System.out.println(getList());
				//	case 2: logout();
				//	case 3: doRandomShit2
					}		
		}
		else{
			tui.failedConnected();
		}
		
	}
	public void newFTPClient() throws IOException, NoInputException, InterruptedException {
		connectAndLogin();
	}

}
