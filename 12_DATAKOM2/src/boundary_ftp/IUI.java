package boundary_ftp;

import java.io.IOException;

public interface IUI {
	void printMessage(String message);
	String getResponse() throws IOException;
}
