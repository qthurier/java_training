import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Test {

  // pour charger les cartes
  static Carte carte;
  static final String chemin ="./"; // data file location
  Carte ens = new Carte(chemin+"mf.txt");

  final static int INEXPLORE = 0;
  final static int EXPLORE = 2;
  final static int TRAITEMENT = 1;

  static Ville[] villes;
  static LinkedList<Integer>[] voisins;

  static void construitGraphe(Collection<Ville> cv, double minDist) {
    // place dans le tableau villes, à qui on donnera la bonne dimension les
    // objets Ville de la collection cv
    int n = cv.size();
    villes = new Ville[n];

    // cette instruction cause un warning à la compilation
    // que vous pouvez ignorer
    voisins = new LinkedList[n];

    int k = 0;
    for (Ville v : cv) {
      villes[k] = v;
      k++;
    }
    
    int num1, num2;
    num1 = 0;
    LinkedList vliste;
    for (Ville v : cv) {
      vliste = new LinkedList();
      num2 = 0;
      for (Ville w : cv) {
          if(num1 != num2 && v.distance(w)<=minDist){
        	  vliste.addFirst(num2);
          }
          num2++;
      }
      voisins[num1] = vliste;
      num1++; 
    }
    // puis on place dans chaque voisins[i] la liste des numéros des voisins de
    // la ville villes[i] sont voisines d'une ville v, les villes qui sont à une
    // distance de moins de minDist. Il faut bien penser à initialiser voisins[j]
    // à newLinkedList() avant d'ajouter des éléments
  }



  static boolean relie(Ville[] villes, LinkedList<Integer>[] voisins, int v1, int v2) {
	  Map<Integer, Integer> etat = new HashMap<Integer, Integer>();
	  for(int k=0; k<villes.length; k++) etat.put(k, INEXPLORE);
	  return DFS(villes, voisins, etat, v1, v2);
  }



static boolean DFS (Ville[] villes, LinkedList<Integer>[] voisins, 
					Map<Integer, Integer> etat,
					int indexS, int indexSortie){
	 etat.put(indexS, TRAITEMENT);
	 if (villes[indexS] == villes[indexSortie]) return(true);
	 LinkedList<Integer> vliste = voisins[indexS];
	 for (int m:vliste) {
	      if (etat.get(m) == 0 && DFS(villes, voisins, etat, m, indexSortie)) return(true);
	 }
	 etat.put(indexS,EXPLORE);
	 return (false);
}


static boolean DFS2 (Ville[] villes, LinkedList<Integer>[] voisins, 
		Map<Integer, Integer> etat, int indexS){
	etat.put(indexS, TRAITEMENT);
	LinkedList<Integer> vliste = voisins[indexS];
	for (int m:vliste) {
	if (etat.get(m) == 0 && DFS2(villes, voisins, etat, m)) return(true);
	}
	etat.put(indexS,EXPLORE);
	return (false);
}

  static int compteCC(Ville[] villes, LinkedList<Integer>[] voisins) {
	  int compteur = 0;
	  Map<Integer, Integer> c = new HashMap<Integer, Integer>();
	  for(int k=0; k<villes.length; k++) c.put(k, INEXPLORE);
  	  for(int j=0; j<villes.length; j++){
	  	 	 if (c.get(j) == 0) {
	  	 	 	 c.put(j,TRAITEMENT);
	  	 	 	 compteur++;
	  	 	 	 DFS2(villes, voisins, c, j);
	  	 	 }
	  }
	  return(compteur);
  }



  static int premiereVille(String s) {
    for (int i = 0; i<villes.length; i++)
      if (s.equals(villes[i].getNom())) return (i);
    return(-1);
  }
  // retourne le numéro de la première ville du graphe dont le nom est s


  public static void initMayotte(double minDist){
    Carte ens = new Carte(chemin+"mf.txt");
    construitGraphe(ens.villes(), minDist);
  }

  public static void initFrance(double minDist){
    Carte ens = new Carte(chemin+"fr.txt");
    construitGraphe(ens.villes(), minDist);

  }


  public static void test1(double minDist) {
    System.out.println("Mayotte, pas de "+(int)minDist);
    initMayotte(minDist);

    int v1 = premiereVille("Accua") ;
    int v2 = premiereVille("Moutsamoudou");
    int v3 = premiereVille("Bandraboua");
    int v4 = premiereVille("Mambutzou");
    
    
    System.out.println("nb composantes : "+compteCC(villes, voisins));
    System.out.println(relie(villes, voisins, v1, v2));
    System.out.println(relie(villes, voisins, v1, v3));
    System.out.println(relie(villes, voisins, v2, v3));
    System.out.println(relie(villes, voisins, v2, v4));

  }

  public static void test2(double minDist) {

    System.out.println("France, pas de "+minDist);

    initFrance(minDist);
    System.out.println("composantes : "+compteCC(villes, voisins));
    
    int v1 = premiereVille("Paris");
    int v2 = premiereVille("Rouen");
  }

  public static void main (String[] args) {

    //test1(1850);
    //test1(2000);
    //test1(3000);
    //test1(3400);
    //test1(4000);

    // tests sur la carte de France
    // peuvent être longs voire demander d'augmenter la taille de la pile
     //test2(2000);
     test2(5000);

  }

}
