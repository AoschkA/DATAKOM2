package ftp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public class newFTPClient implements IFTPClient{
	Socket socket;
	DataOutputStream output;
	BufferedReader input;
	@Override
	public void connect(String IP, int port) throws UnknownHostException, IOException, NoInputException {
		if (IP.isEmpty())
			throw new NoInputException();
		
		socket = new Socket(IP, port);
		output = new DataOutputStream(socket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
	}
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retrieveFile(String filename) {
		// TODO Auto-generated method stub
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
	
	

}
