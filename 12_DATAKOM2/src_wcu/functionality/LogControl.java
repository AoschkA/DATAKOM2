package functionality;

import java.util.ArrayList;

public class LogControl implements LogDAO{
	FileHandler fh = new FileHandler();
	LogDTO logEntry = new LogDTO();
	ArrayList<LogDTO> logList = new ArrayList<LogDTO>();
	
	@Override
	public void registerLogEntry(OperatoerDTO oprID,RaavareDTO raavare, String afvejning) {
		logEntry.setAfvejning(afvejning);
		logEntry.getDato();
		logEntry.setOprID(oprID.getOprID());
		logEntry.setPaa_lager(raavare.getRaavareWeight());
		logEntry.setRaavareID(raavare.getRaavareID());
		fh.writeLogDB(logEntry);
		
	}
	@Override
	public ArrayList<LogDTO> getLogList() {
		logList = fh.readLogDB();
		return logList;
	}
}
