package functionality;

import java.util.ArrayList;

public class OprControl implements OperatoerDAO{

	FileHandler operatoerFil;
	ArrayList<OperatoerDTO> oDB = new ArrayList<OperatoerDTO>();

	public OprControl() {

		operatoerFil = new FileHandler();
		oDB = operatoerFil.readOperatoerDB();

	}

	@Override
	public OperatoerDTO getOperatoer(int oprID) {
		OperatoerDTO hentOperatoer = new OperatoerDTO();
		for (int i = 0; i < oDB.size(); i ++){
			if (oprID == oDB.get(i).getOprID()){
				return oDB.get(i);
			}
		}
		hentOperatoer.setOprID(999999);
		return hentOperatoer;
	}

	@Override
	public void updateOperatoer(OperatoerDTO oprID) {
		for(int i = 0; i < oDB.size(); i++){
			if(oprID.getOprID() == oDB.get(i).getOprID()){
				oDB.set(i, oprID);
			}
		}
		operatoerFil.writeOperatoerDB(oDB);
	}

	@Override
	public ArrayList<OperatoerDTO> getOperatoerList() {
		return oDB;
	}
	@Override
	public void createOperatoer(ArrayList<OperatoerDTO> oprDB, OperatoerDTO opr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOperatoer(int oprID) {
		// TODO Auto-generated method stub

	}

}


