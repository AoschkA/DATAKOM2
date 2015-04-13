package functionality;

public class LogDTO {
	
	private String dato;
	private String time;
	private int oprID;
	private int raavareID;
	private String afvejning;
	private String paa_lager;
	
	public LogDTO() {
		this.dato = "";
		this.time = "";
		this.oprID = 0;
		this.raavareID = 0;
		this.afvejning = "";
		this.paa_lager = "";
		
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getOprID() {
		return oprID;
	}

	public void setOprID(int oprID) {
		this.oprID = oprID;
	}

	public int getRaavareID() {
		return raavareID;
	}

	public void setRaavareID(int raavareID) {
		this.raavareID = raavareID;
	}

	public String getAfvejning() {
		return afvejning;
	}

	public void setAfvejning(String afvejning) {
		this.afvejning = afvejning;
	}

	public String getPaa_lager() {
		return paa_lager;
	}

	public void setPaa_lager(String paa_lager) {
		this.paa_lager = paa_lager;
	}
	

}
