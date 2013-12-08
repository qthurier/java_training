

public class TableDeHachage {
	Liste[] tab;
	
	TableDeHachage(int n){
		this.tab = new Liste[n];
		for(int i=0; i<this.tab.length; i++){
			this.tab[i] = new Liste();
		}
	}
	public void ajoute(Objet o){
		int h = o.hash();
		int m = h % tab.length;
		if (m < 0) m += this.tab.length;
		tab[m].ajouteTete(o);
	}
	public boolean contient(Objet o){
		boolean contient = false;
		int i=0;
		while(i<this.tab.length & contient == false){
			contient = tab[i].contient(o);
			i++;
		}
		return contient;
	}
	public int[] remplissageMax(){
		int[] max = {0, 0};
		for(int i=0; i<this.tab.length; i++){
			if (this.tab[i].longueur() > max[1]){
				max[0] = i;
				max[1] = this.tab[i].longueur(); 
			}
		}
		return max;
	}
}
