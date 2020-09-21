
public class Pessoa {
	private String id;
	private String k_mestre;	
	public Pessoa(String id, String k_mestre){
		this.id = id;
		this.k_mestre = k_mestre;
	}	
	public String getID(){
		return this.id;
	}	
	public String getChaveMestre(){
		return this.k_mestre;
	}
}
