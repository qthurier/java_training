

public class Objet2 extends Objet {
	String nom;
	
	Objet2(String nom){
		this.nom=nom;
	}
	public int hash(){
		String s=this.nom();
		int len=s.length(), h=5381;
		for(int j=0; j<len; j++){ 
			h = h*33^s.charAt(j); 
		}
		return h;
	}
	public String nom(){
		return this.nom;
	}
}
