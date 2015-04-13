package ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

import ftp.Exceptions.NoInputException;

public interface IFTPClient {
	
	boolean connect(String IP, int port) throws UnknownHostException, IOException, NoInputException;
	boolean login(String username, String password) throws IOException;
	boolean retrieveFile(String filename, OutputStream output) throws IOException;
	boolean logout();
	boolean sendFile(InputStream inputStream, String filename) throws IOException;

}
