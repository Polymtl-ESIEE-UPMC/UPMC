package model.circuit;

import java.util.LinkedList;
import model.geometrie.Vecteur;
import model.terrain.Terrain;

public class CircuitImpl implements Circuit {
	
	private Terrain[][] track;
	private Vecteur depart, dirDepart, dirArrivee;
	
	public CircuitImpl(Terrain[][] track, Vecteur depart,
			Vecteur dirDepart, Vecteur dirArrivee){
		this.track = track;
		this.depart = depart;
		this.dirDepart = dirDepart;
		this.dirArrivee = dirArrivee;
	}
	
	public Terrain getTerrain(int i, int j){
		return track[i][j];
	}
	
	public Terrain getTerrain(Vecteur v){
		return track[(int)v.x][(int)v.y];
	}

	public Vecteur getPointDepart(){ 
		return depart;
	}
	
	public Vecteur getDirectionDepart(){
		return dirDepart;
	}
	
	public Vecteur getDirectionArrivee(){
		return dirArrivee;
	}
	
	public int getWidth(){
		return track[0].length;
	}
	
	public int getHeight(){
		return track.length;
	}
	
	public LinkedList<Vecteur> getArrivees(){
		LinkedList<Vecteur> list = new LinkedList<Vecteur>();
		for(int i=0; i< track.length; i++)
			for(int j=0 ; j<track[0].length;j++){
				if(track[i][j] == Terrain.EndLine)
					list.add(new Vecteur(i,j));
			}
		return list;
	}
	
	public Terrain[][] getMatrix(){
		return track;
	}
	
}
