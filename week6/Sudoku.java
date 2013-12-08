class Sudoku {
  int[][] grille;
  
  Sudoku(int[][] t) {
    for(int i=0; i<t.)
  }
  
  public boolean verifieLigne(int i) {
    // à compléter
    return false;
  }

  public boolean verifieColonne(int i) {
    // à compléter
    return false;
  }

  public boolean verifieCarre(int i,int j) {
    // à compléter
    return false;
  }
  
  public boolean verifiePossible(int i,int j, int val) {
    // à compléter
    return false;
  }
  
  public boolean resousGrille() {
    // à compléter
    return false;
  }

  public int solutionUnique() {
    // à compléter
    return 0;
  }
  
  public void afficheGrille() {
    if (this.grille == null)
      return;

    for(int i = 0 ;i < 9; i++) {
      for(int j = 0; j < 9; j++) {
        System.out.print(this.grille[i][j]+" ");
      }
      System.out.println();
    }
  }
}
