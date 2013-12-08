

import java.util.LinkedList;

public class Dictionnaire {
	Noeud racine;
	
	Dictionnaire(){
		this.racine = new Noeud('_');
	}
	
	public boolean existeMot(String s){
		boolean existe = true;
		int i = 0;
		Noeud cur = new Noeud(this.racine);
		while(i<s.length()){
			if(cur.existeMotRecursif(s, i)){
				cur = cur.fils.get(cur.position(s, i));
				i++;
			}
			else{
				existe = false;
				break;
			}
		}
		if(existe && cur.fils.get(0).lettre != '*') existe = false;
		return existe;
	}
	
	public boolean ajouteMot(String s){
		boolean deja_la = this.existeMot(s);
		if(!deja_la){
			int i = 0;
			Noeud cur = this.racine;
			while(i<s.length()){
				if(cur.existeMotRecursif(s, i)){
					cur = cur.fils.get(cur.position(s, i));
					i++;
				}
				else break;
			}
			int index;
			for(int j=i; j<s.length(); j++){
				index = cur.where(s.charAt(j));
				//System.out.println(index);
				cur.fils.add(index, new Noeud(s.charAt(j)));
				cur = cur.fils.get(index);
			}
			if(this.estPrefixe(s)){
				cur.fils.add(0, new Noeud('*'));
			}
			else{
				cur.fils.add(new Noeud('*'));
			}
		}
		return !deja_la;
	}
	
	boolean estPrefixe(String s){
		boolean prefixe = true;
		int i = 0;
		Noeud cur = new Noeud(this.racine);
		while(i<s.length()){
			if(cur.existeMotRecursif(s, i)){
				cur = cur.fils.get(cur.position(s, i));
				i++;
			}
			else{
				prefixe = false;
				break;
			}
		}
		return prefixe;	
	}
	
	public void listeMotsAlphabetique(){
		this.racine.listeMotsAlphabetique(new LinkedList<Character>());
	}

}
