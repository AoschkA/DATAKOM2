import java.io.IOException;

import functionality.FileHandler;
import functionality.RaavareControl;
import functionality.RaavareDTO;


public class WCU_Launch {
	public static void main(String[] args) throws IOException {
		RaavareDTO r = new RaavareDTO();
		r.setRaavareID(2);
		r.setRaavareName("svovl");
		r.setRaavareWeight("0");
		RaavareControl rc = new RaavareControl();
		rc.updateRaavare(r);
	}
}

