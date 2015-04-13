package functionality;

import java.util.ArrayList;

public interface OperatoerDAO {
	public OperatoerDTO getOperatoer(int oprID);
	public void updateOperatoer(int oprID);
	public void createOperatoer(ArrayList<OperatoerDTO> oprDB, OperatoerDTO opr);
	public void deleteOperatoer(int oprID);
	public ArrayList<OperatoerDTO> getOperatoerList();
}
