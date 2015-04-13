package functionality;

import java.util.ArrayList;

public interface LogDAO {
	
	public void registerLogOn(OperatoerDTO oprID);
	public void registerAfvejning(RaavareDTO raavareWeight);
	public ArrayList<LogDTO> getLogList();

}
