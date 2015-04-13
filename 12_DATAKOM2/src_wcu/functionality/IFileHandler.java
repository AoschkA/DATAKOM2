package functionality;

import java.util.ArrayList;

public interface IFileHandler {

	ArrayList<OperatoerDTO> readOperatoerDB();
	ArrayList<RaavareDTO> readRaavareDB();
	ArrayList<LogDTO> readLogDB();
	void writeOperatoerDB(ArrayList<OperatoerDTO> oprDB);
	void writeRaavareDB(ArrayList<RaavareDTO> rDB);
	void writeLogDB(ArrayList<LogDTO> logDB);

}
