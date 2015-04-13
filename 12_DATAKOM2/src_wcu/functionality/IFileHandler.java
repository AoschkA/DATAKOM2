package functionality;

import java.util.ArrayList;

public interface IFileHandler {

	ArrayList<OperatoerDTO> readOperatoerDB();
	ArrayList<RaavareDTO> readRaavareDB();
	void writeOperatoerDB(ArrayList<OperatoerDTO> oprDB);
	void writeRaavareDB(ArrayList<RaavareDTO> rDB);

}
