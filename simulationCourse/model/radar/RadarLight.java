package model.radar;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.terrain.TerrainTools;
import model.voiture.Voiture;

public class RadarLight {

	private Circuit c;
	private Vecteur po, direction, position;
	private int cpt;

	public RadarLight(Circuit c){
		this.c = c;
		po = c.getPointDepart().add(c.getDirectionDepart().mult(0.9));
	}

	public void score(Voiture v, double angle){
		direction = v.getDirection();
		direction = direction.rotation(angle);
		direction = direction.unitVec();
		cpt = -1;
		position= v.getPosition();
		while(TerrainTools.isRunnable(c.getTerrain(position.add(direction.mult(1)))) == true
				&& position.x < c.getHeight() 
				&& position.y < c.getWidth()){
			po = position;
			position = position.add( direction.mult(1));
			cpt++;
			if(cpt == 50) break;
		}
		if(cpt<50) po = position.add(direction.mult(-cpt/1.3));
	}

	public Vecteur getPo(){
		return po;
	}

}
