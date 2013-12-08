

class Cellule{
	Objet cur;
	Cellule prev;
	
	Cellule(Objet a, Cellule b){
		this.cur=a;
		this.prev=b;
	}
}

class Liste {
	Cellule tete;
	
	Liste(){
		Cellule head = new Cellule(null, null);
		this.tete= head;
	}
	public void setTete(Cellule o){
		this.tete = o;
	}
	public Liste ajouteTete(Objet o){
		Cellule old = this.tete;
		this.tete = new Cellule(o, old);
		return this;
	}
	public Liste supprimeTete(){
		if (this.tete.cur == null) {
			throw new java.util.NoSuchElementException();
		} else {
			Cellule old = this.tete;
			this.setTete(old.prev);
		}
		return this;
	}
	public int longueur(){
		int longueur= 0;
		Cellule cursor = this.tete;
		while (cursor.cur != null) {
			longueur++;
			cursor = cursor.prev;
		}
		return longueur;
	}
	public boolean contient(Objet o){
		boolean contient=false;
		Cellule cursor = this.tete;
		while (cursor.cur != null) {
			if (cursor.cur.nom().equals(o.nom())) {
				contient = true;
				break;
			} else {
				cursor = cursor.prev;
			}
		}
		return contient;
	}

}
