


import java.util.LinkedList;

public class Position {
	final int x, y;
	final Exploration e;

	public Position(Exploration e, int x, int y){
		this.x = x;
		this.y = y;
		this.e = e;
	}
	
	private boolean estLegal(){
		boolean out = true;
		if (this.x > this.e.dim - 1 || this.y > this.e.dim - 1 || this.x < 0 || this.y < 0) out = false;
		else if(this.e.masque[x][y]) out = false; 
		return out;
	}
	
	public LinkedList<Position> deplacementsLegaux(){
		LinkedList<Position> deplacement = new LinkedList<Position>();
		for(int j=-1; j<2; j++){
			for(int k=-1; k<2; k++){
				Position p = new Position(this.e, x+j, y+k);
				if((j != 0 || k != 0) && p.estLegal()) deplacement.add(p);
			}
		}
		return deplacement;
	}
	
}
