

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Exploration {
	final char[][] grille;
	final int dim;
	final Dictionnaire d;
	// Variables représentant l'état de l'exploration, modifiées par les fonctions
	// "explore" et "exploreTout". Elles seront initialisées par la fonction
	// "exploreTout`"
	boolean[][] masque;
	LinkedList<Character> prefix;
	LinkedList<String> motsTrouves;

	Exploration (char[][] grille, int dim, Dictionnaire d){
		this.grille = grille;
		this.dim = dim;
		this.d = d;
	}
	
	public void explore(Position p, Noeud n){
		char c = this.grille[p.x][p.y]; // quel caractère sur cette position
		Noeud suivant = n.trouveFils(c); // peut on former un nouveau préfixe avec
		LinkedList<Character> temp = new LinkedList<Character>(this.prefix);
		boolean[][] tempbis = new boolean[this.dim][this.dim];
		for(int j=0; j<this.dim; j++) for(int k=0; k<this.dim; k++) tempbis[j][k] = this.masque[j][k];
		if(suivant != null){ // oui on peut
			this.prefix.add(c);// mise à jour du préfixe
			n = suivant; // on déplace le pointeur vers le nouveau noeud
			this.masque[p.x][p.y] = true; // mise à jour du masque
			if(n.estMot()) motsTrouves.add(versChaine(this.prefix)); // si on trouve un mot on l'ajoute
			for(Position q : p.deplacementsLegaux()) explore(q, n); // appel récursif de la fontion pour toutes les positions légales
		}
		this.prefix = temp; // problème de backtracking
		this.masque = tempbis; // problème de backtracking
	}
	
	public static String versChaine(LinkedList<Character> l) {
	    StringBuilder sb = new StringBuilder();
	    for (Character c: l)
	      sb.append(c);
	    return sb.toString();
	}
	
	public LinkedList<String> exploreTout(){
		this.prefix = new LinkedList<Character>();
		this.motsTrouves = new LinkedList<String>();
		this.masque = new boolean[this.dim][this.dim];
		for(int j=0; j<this.dim; j++){
			for(int k=0; k<this.dim; k++){
				this.masque[j][k] = false;
			}
		}
		this.motsTrouves = new LinkedList<String>();
		this.prefix = new LinkedList<Character>();
		for(int j=0; j<this.dim; j++){
			for(int k=0; k<this.dim; k++){
				Position where = new Position(this, j, k);
				this.explore(where, this.d.racine);
			}
		}

		Collections.sort(this.motsTrouves,
				new Comparator<String>(){ 
					public int compare(String s1,String s2){
						if (s1.length() == s2.length()) return s1.compareTo(s2);
						else return s1.length()-s2.length();
					}
				}
						);
		
		return this.motsTrouves;	
	}
}


