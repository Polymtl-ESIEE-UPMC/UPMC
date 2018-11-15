package model.circuit;

import java.util.LinkedList;
import model.geometrie.Vecteur;
import model.terrain.Terrain;

public interface Circuit {
	
	public Terrain getTerrain(int i, int j);
	public Terrain getTerrain(Vecteur v);
	public Vecteur getPointDepart();
	public Vecteur getDirectionDepart();
	public Vecteur getDirectionArrivee();
	public int getWidth();
	public int getHeight();
	public LinkedList<Vecteur> getArrivees();
	public Terrain[][] getMatrix();
}
