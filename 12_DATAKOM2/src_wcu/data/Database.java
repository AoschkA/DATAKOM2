package data;

public class Database {

	int opr_nr;
	String opr_name;
	int raavare_nr;
	String raavare_navn;
	String raavare_weight;
	
	public Database(){
		opr_nr = 0;
		opr_name = "";
		raavare_nr = 0;
		raavare_navn = "";
		raavare_weight = "";
	}
	
	public int getOprNr(){
		return this.opr_nr;
	}
	public String getOprName(){
		return this.opr_name;
	}
	public int getRaavareNr(){
		return this.raavare_nr;
	}
	public String getRaavareNavn(){
		return this.raavare_navn;
	}
	public String getRaavareWeight(){
		return this.raavare_weight;
	}
	public void setOprNr(int opr_nr){
		this.opr_nr = opr_nr;
	}
	public void setOprName(String opr_name){
		this.opr_name = opr_name;
	}
	public void setRaavareNr(int raavare_nr){
		this.raavare_nr = raavare_nr;
	}
	public void setRaavareNavn(String raavare_navn){
		this.raavare_navn = raavare_navn;
	}
	public void setRaavareWeight(String raavare_weight){
		this.raavare_weight = raavare_weight;
	}
	
}
