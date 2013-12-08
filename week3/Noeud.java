
import java.util.LinkedList;

public class Noeud {
	char lettre;
	LinkedList<Noeud> fils;
	
	Noeud(char c){
		this.lettre = c;
		this.fils = new LinkedList<Noeud>();
	}
	
	Noeud(Noeud n){
		this.lettre = n.lettre;
		this.fils = new LinkedList<Noeud>(n.fils);
	}
	
	public void ajouteFils(Noeud a){
		this.fils.add(a);
	}
	
	public String toString(){
		 StringBuilder sb  = new StringBuilder();
	 	 sb.append(this.lettre);
	 	 if(! this.fils.isEmpty()){
	 		sb.append("(");
		 	 for (Noeud n : fils) {
		 	 	 sb.append(n.toString() + ",");
		 	 }
		 	sb.deleteCharAt(sb.length()-1);
		 	sb.append(")");
	 	 }  	 	
	 	 return sb.toString();
	}
	
	public boolean existeMotRecursif(String s, int pos){
		boolean existe = false;
		for (Noeud f: this.fils) {
		  if(f.lettre == s.charAt(pos)){
			  existe = true;
			  break;
		  }
		}
		return existe;
	}
	
	public int position(String s, int pos){
		int position = 0;
		for (Noeud f: this.fils) {
		  if(f.lettre == s.charAt(pos)) break;
		  position++;
		}
		return position;
	}
	
	public int where(char c){
		int position = 0;
		for (Noeud f: this.fils) {
		  System.out.println(f.lettre > c);
		  if(f.lettre > c) break;
		  position++;
		}
		return position;
	}
	
	public void listeMotsAlphabetique(LinkedList<Character> prefix) {
		if(this.lettre == '*'){
			StringBuilder sb  = new StringBuilder();
			for(Character c : prefix) {
				sb.append(c);
			}
			System.out.println(sb.deleteCharAt(0));
		}
		else{
			LinkedList<Character> temp = new LinkedList<Character>(prefix);
			temp.add(this.lettre);
		 	for (Noeud n : fils) {
		 	 	 n.listeMotsAlphabetique(temp);
		 	}
		}
	}

}
