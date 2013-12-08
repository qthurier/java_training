
import java.util.Arrays;

class Seg {

	  static double[] erreur (double[] xtab, double[] ytab, int d, int f) {
		  if (xtab.length != ytab.length) {
			  throw new IllegalArgumentException();
		  } else if (f < d) {
			  throw new IllegalArgumentException();
		  } else if (f >= xtab.length || d >= xtab.length ) {
			  throw new IllegalArgumentException();
		  } else {
			  double sum_x = 0, sum_x2 = 0, sum_y = 0, sum_xy = 0, n = f - d + 1;
			  for(int i=d; i<=f; i++){
				  sum_x += xtab[i];
				  sum_x2 += xtab[i] * xtab[i];
				  sum_y += ytab[i];
				  sum_xy += xtab[i] *  ytab[i];
			  }
			  double a = (n * sum_xy - sum_x * sum_y)/(n * sum_x2 - sum_x * sum_x);
			  double b = (sum_y - a * sum_x)/n;
			  double err = 0;
			  for(int i=d; i<=f; i++) err += (ytab[i] - a * xtab[i] - b)*(ytab[i] - a * xtab[i] - b);
			  return(new double[] { a, b, err });
		  }
	  }
	  
	  // points en commun
	  static int nbSegbis (double[] xtab, double[] ytab, double c) {
		  int[] best_n_list = new int[xtab.length - 1];
		  double[] best_err_list = new double[xtab.length - 1];
		  double min, erreur;
		  int best_n;
		  for(int i=1; i<xtab.length; i++){ // on parcourt les problèmes de 2 points à n points
			  min = c + Seg.erreur(xtab, ytab, 0, i)[2]; // une seule regression utilisant les points de 1 à i+1
			  best_n = 1; // un seul segment
			  for(int k=1; k<i; k++){ // on cherche s'il y a de meilleurs alternatives à cette solution
			  // programmation dynamique, la meilleure solution est la meilleure solution du problème restreint + un segment
			  // on peut restreindre le problème de 2 points à i-1 points	  
				  erreur = best_err_list[k-1] + c + Seg.erreur(xtab, ytab, k, i)[2]; 
				  if (erreur < min) {
					  min = erreur;
					  best_n = 1 + best_n_list[k-1];
				  }
			  }
			  best_err_list[i-1] = min; // meilleur erreur trouvée pour un problème à i + 1 points
			  best_n_list[i-1] = best_n; // nombre de segments dans la meilleur solution du problème à i + 1 points
		  }
		  return best_n_list[best_n_list.length-1];
	  }
	  
	  // pas de points en commun
	  static int nbSeg (double[] xtab, double[] ytab, double c) {
		  int[] best_n_list = new int[xtab.length - 1];
		  double[] best_err_list = new double[xtab.length - 1];
		  double min, erreur;
		  int best_n;
		  for(int i=1; i<xtab.length; i++){ // on parcourt les problèmes de 2 points à n points
			  min = c + Seg.erreur(xtab, ytab, 0, i)[2]; // une seule regression utilisant les points de 1 à i+1
			  best_n = 1; // un seul segment
			  for(int k=2; k<i; k++){ // on cherche s'il y a de meilleures alternatives à cette solution
			  // programmation dynamique, la meilleure solution est la meilleure solution du problème restreint + un segment
			  // on peut restreindre le problème de 2 points à i-1 points	
				  erreur = best_err_list[k-2] + c + Seg.erreur(xtab, ytab, k, i)[2]; 
				  if (erreur < min) {
					  //if(i>1 && i<=80) System.out.println("n=" + i + " cut="+ k + " " + erreur + " < " + min);
					  min = erreur;
					  best_n = 1 + best_n_list[k-2];
				  }
			  }
			  
			  best_err_list[i-1] = min; // meilleur erreur trouvée pour un problème à i + 1 points
			  best_n_list[i-1] = best_n; // nombre de segments dans la meilleur solution du problème à i + 1 points
			  //if(i>1 && i<=80) System.out.println("n=" + i + " err=" + best_err_list[i-1] + " bestp=" + best_n_list[i-1] + " best(p-1)=" + best_n_list[i-2] );
		  }
		  //for(int j=0; j<best_n_list.length; j++) System.out.println(best_n_list[j] + " " + best_err_list[j]);
		  return best_n_list[best_n_list.length-1];
	  }
	      

}


class Prng {
  static double seed = 0.4435431;
  static int m = 1;
  static double a = 68.45633;
  static double b = 0.724514 ;

  static double next() {
    seed = (double) (((seed) * a + b) % m);
    return seed;
  }
}
