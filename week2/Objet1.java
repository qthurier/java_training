

public class Objet1 extends Objet {
	String nom;
	
	Objet1(String nom){
		this.nom=nom;
	}
	public int hash(){
		String s=this.nom();
		int len=s.length(), h=0, p31=1;
		for(int j=0; j<len; j++){ 
			h += s.charAt(len-1-j)*p31; 
			p31 *= 31;
		}
		return h;
	}
	public String nom(){
		return this.nom;
	}
}
