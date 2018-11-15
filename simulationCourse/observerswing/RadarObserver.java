package observerswing;

import model.geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import model.radar.Radar;
import model.voiture.Voiture;

public class RadarObserver implements ObserverSWING {

	private Voiture v;
	private Radar r;
	private Color c;
	private Vecteur[] bouts;

	public RadarObserver(Voiture v, Radar r) {
		this.v = v;
		this.r = r;
		this.c = Color.white;
	}

	public Vecteur[] getFaisceaux(Voiture v){
		double[] thetas = r.thetas();
		double[] distances = r.distancesInPixels();
		bouts = new Vecteur[thetas.length];
		Vecteur pos = v.getPosition();

		for(int i=0;i<thetas.length;i++){
			Vecteur dir = v.getDirection();
			dir = dir.rotation(thetas[i]);
			bouts[i] = pos.add(dir.mult(distances[i]));
		}
		return bouts;
	}

	public void print(Graphics g){
		getFaisceaux(v);
		g.setColor(c);
		for(int i=0;i<r.thetas().length;i++){
			g.drawLine((int) v.getPosition().y, 
					(int) v.getPosition().x, 
					(int) bouts[i].y, 
					(int) bouts[i].x);	
		}
	}

}
