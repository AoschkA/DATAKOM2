package ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
	public boolean retrieveFile(String filename, OutputStream output) {
		
		return false;
	}
	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
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
	

}
