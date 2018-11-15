package observerswing;

import java.awt.Color;
import java.awt.Graphics;

import model.geometrie.Vecteur;
import model.radar.Radar;
import model.radar.RadarLight;
import model.voiture.Voiture;

public class Radar3Observer implements ObserverSWING {
	private Radar r;
	private RadarLight r2;
	private Color c;
	private Voiture v;
	private Vecteur[] bouts;

	public Radar3Observer(Radar r, RadarLight r2, Voiture v) {
		this.r = r;
		this.r2 = r2;
		this.v = v;
		this.c = Color.BLACK;
	}

	public Vecteur[] getFaisceaux(){
		double[] thetas = r.thetas();
		double[] distances = r.distancesInPixels();
		bouts = new Vecteur[thetas.length];
		Vecteur pos = r2.getPo();

		for(int i=0;i<thetas.length;i++){
			Vecteur dir = v.getDirection();
			dir = dir.rotation(thetas[i]);
			dir = dir.unitVec();
			bouts[i] = pos.add(dir.mult(distances[i]));
		}
		return bouts;
	}

	public void print(Graphics g){
		getFaisceaux();
		g.setColor(c);
		for(int i=0;i<r.thetas().length;i++){
			g.drawLine((int) r2.getPo().y, 
					(int) r2.getPo().x, 
					(int) bouts[i].y, 
					(int) bouts[i].x);	
		}
	}
	
}
