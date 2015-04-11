package ftp;

import java.io.IOException;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public interface IFTPClient {
	
	void connect(String IP, int port) throws UnknownHostException, IOException, NoInputException;
	boolean login(String username, String password);
	boolean retrieveFile(String filename);
	boolean logout();
	void disconnect();

}
