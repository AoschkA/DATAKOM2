import java.io.IOException;

import control.WeightCommunicator;
import functionality.OprControl;
import functionality.UserHandler;


public class WCU_Launch {
	public static boolean run = true;
	public static void main(String[] args) throws IOException {
		WeightCommunicator wc = new WeightCommunicator("10.16.169.246", 4567);
		wc.connectToServer();
		int i = 0;
		UserHandler uh = new UserHandler();
		uh.doSomething(11);
		uh.doSomething(12);
		uh.doSomething(13);
		uh.doSomething(14);
		while(run){
			wc.writeSocket("D hej klaphat \r\n");
			System.out.println(wc.readSocket());
			i++;
			if(i == 20){run = false;}
		}
	}
}

