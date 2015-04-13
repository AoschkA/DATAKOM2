package functionality;

public class LogDTO {
	
	private String dato;
	private String time;
	OperatoerDTO oprID;
	RaavareDTO raavareID;
	private double afvejning;
	private double på_lager;
	
	public LogDTO() {
		this.dato = "";
		this.time = "";
		this.oprID = null;
		this.raavareID = null;
		this.afvejning = 0.0;
		this.på_lager = 0.0;
		
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

	public OperatoerDTO getOprID() {
		return oprID;
	}

	public void setOprID(OperatoerDTO oprID) {
		this.oprID = oprID;
	}

	public RaavareDTO getRaavareID() {
		return raavareID;
	}

	public void setRaavareID(RaavareDTO raavareID) {
		this.raavareID = raavareID;
	}

	public double getAfvejning() {
		return afvejning;
	}

	public void setAfvejning(double afvejning) {
		this.afvejning = afvejning;
	}

	public double getPå_lager() {
		return på_lager;
	}

	public void setPå_lager(double på_lager) {
		this.på_lager = på_lager;
	}

}
