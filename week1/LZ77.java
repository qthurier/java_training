

class Element {
	Occurrence o;
	int s;
	
	Element (Occurrence o, int s) {
		this.o=o;
		this.s=s;
	}
	
}

class Occurrence {
	int retour;
	int taille;
	
	Occurrence (int retour, int taille) {
		this.retour=retour;
		this.taille=taille;
	}
	
	public String toString(){
		return "(" + this.retour + "," + this.taille + ")"; 
	}
}

class LZ77 {
  public static Occurrence plusLongueOccurrence(
	      int[] t,
	      int positionCourante,
	      int tailleFenetre
	    ) {
	  if (t == null) {
			throw new IllegalArgumentException();
	  } else if (t.length == 0) {
			return new Occurrence(0, 0);
	  } else {
		  	// la taille de la fenetre doit etre corrigée au cas où il n'y a pas assez de valeurs avant la position courante
			tailleFenetre=Math.min(tailleFenetre, positionCourante); 
			// la taille maximum d'une occurence dépend de la taille de la fenêtre et du nombre de valeur après la position courante
			int tailleMaxOccurrence=Math.min(tailleFenetre, t.length-positionCourante);
			// initialisation par défaut
			int retour=positionCourante-tailleFenetre, comparaison=0, taille=tailleMaxOccurrence;
			for(int i=tailleMaxOccurrence; i>=1; i--) { // i = taille de l'occurrence recherchée
				//System.out.println("i "+i);
				for(int j=0; j<=tailleFenetre-i; j++){ // j = curseur initial relativement à la taille de la fenêtre
					retour=positionCourante-tailleFenetre+j; // valeur de retour (en absolu)
					//System.out.println("k "+retour);
					taille=i;
					comparaison=0;
					while(comparaison<i && t[retour+comparaison]==t[positionCourante+comparaison]){ // nb de comparaison ok fenetre vs occurence
						//System.out.println("compare "+ t[retour+comparaison] + " et " + t[positionCourante+comparaison]);
						comparaison++;
						//System.out.println("l "+comparaison);
					}
					if(comparaison==taille){
						//System.out.println("break");
						break;
					}
				}
				if(comparaison==taille){
					//System.out.println("break");
					break;
				}
			}
			if (comparaison==0) {
				return new Occurrence(0, 0);
			} else {
				return new Occurrence(positionCourante - retour, taille);
			}
	  }	
  }
  public static int LZ77Longueur(int[] t, int tailleFenetre){
	  if (t == null) {
			throw new IllegalArgumentException();
	  } else if (t.length == 0) {
			return 0;
	  } else {
		  int curseur=0, longueur=0;
		  while(curseur < t.length){	
			  curseur += plusLongueOccurrence(t, curseur,tailleFenetre).taille + 1;
			  //System.out.println(plusLongueOccurrence(t, curseur,tailleFenetre).taille);
			  longueur++;
		  }
		  return longueur;
	  }
  }
  public static Element[] LZ77(int[] tab, int tailleFenetre){
	  if (tab == null) {
			throw new IllegalArgumentException();
	  } else if (tab.length == 0) {
			return new Element[0];
	  } else {
		  int curseur=0;
		  Element[] elt= new Element[LZ77Longueur(tab, tailleFenetre)];
		  Occurrence o;
		  for(int i=0; i<elt.length-1; i++){
			  o = plusLongueOccurrence(tab, curseur, tailleFenetre);
			  curseur += o.taille + 1;
			  elt[i] = new Element(o, tab[curseur-1]); // si taille = 0 garde le même char sinon garde le char juste après la chaine qui a matché
		  }
		  o = plusLongueOccurrence(tab, curseur, tailleFenetre);
		  elt[elt.length-1] = new Element(o, tab[tab.length-1]);
		  return elt;
	  }
  }
  public static void afficheEncode(Element[] tab){
	  String output="";
	  for(int i=0; i<tab.length; i++){
		  //System.out.println(i);
		  output += tab[i].o.toString() + tab[i].s ;
	  }  
	  System.out.println(output);
  }
  public static int LZ77InverseLongueur(Element[] t){
	  int longueur = 0;
	  for(int i=0; i<t.length; i++){
		  longueur += t[i].o.taille + 1;
	  }
	  return longueur;
  }
  public static int[] LZ77Inverse(Element[] t){
	  int[] resultat = new int[LZ77InverseLongueur(t)];
	  int j=0;
	  for(int i=0; i<t.length; i++){
		  blit(resultat, resultat, j-t[i].o.retour, t[i].o.taille, j);
		  j += t[i].o.taille;
		  resultat[j] = t[i].s;
		  j++;
	  }
	  return resultat;
  }
  static void blit(int[] t1, int[] t2, int start1, int len, int start2){
	  for(int i=0; i<len; i++){
		  t2[start2+i] = t1[start1+i];
	  }
  }
  public static void afficheDecode(int[] t){
	  String resultat= new String("");
	  for(int i=0; i<t.length; i++){
		  resultat += t[i] + " ";
	  }
	  System.out.print(resultat += '\n');
  }
}
