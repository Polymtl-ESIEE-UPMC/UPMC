package model.circuit;

import model.geometrie.Vecteur;
import model.terrain.Terrain;
import model.terrain.TerrainTools;

public class CircuitFactoryFromFile {
	
	public static final Vecteur dirDepart=new Vecteur(0,1);
	public static final Vecteur dirArrivee=new Vecteur(0,1);

	public static Circuit build(String name) { 
		
		Terrain[][]  t = TerrainTools.readFromFile(name);
		Vecteur depart =null;
		for(int i=0; i<t.length; i++){
			for(int j=0; j<t[0].length; j++){
				if(t[i][j] == Terrain.StartPoint){
					depart = new Vecteur(i,j);
					break;
				}
			}
		}
		return new CircuitImpl(t, depart, dirDepart, dirArrivee);
	}

}
