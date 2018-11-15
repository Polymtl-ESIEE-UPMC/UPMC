package model.filtre;

import model.terrain.Terrain;
import model.circuit.Circuit;

public class Filtre {

	private Circuit c;

	public Filtre(Circuit c, Circuit c1){
		this.c = c;
	}
	
	public void filtrer() {
		// TODO Auto-generated method stub
		Terrain[][] mat = c.getMatrix();
		for(int i=0; i<300; i++){
			for(int j=0; j<300; j++){  
				if(mat[i][j]==Terrain.EndLine)
					mat[i][j]=Terrain.Herbe;
			}
		}
	}

}