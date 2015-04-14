import java.io.IOException;

import ftp.FTPClient;
import ftp.Exceptions.NoInputException;


public class Launch {
	static FTPClient ftp = new FTPClient();
	public static void main(String[] args) throws IOException, NoInputException, InterruptedException {
		ftp.run();

	}

}
