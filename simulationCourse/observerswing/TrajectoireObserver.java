package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.geometrie.Vecteur;
import model.voiture.Voiture;

public class TrajectoireObserver implements ObserverSWING {
	
	private ArrayList<Vecteur> list;
	private Voiture v;
	
	public TrajectoireObserver(Voiture v){
		list = new ArrayList<Vecteur>();
		this.v=v;
	}
	public void print(Graphics g){
		list.add(v.getPosition());
		g.setColor(new Color((int)(v.getVitesse()*255), (int) (v.getVitesse()*255), 0));
		for(Vecteur vect:list)
			g.drawLine((int)vect.y, (int)vect.x, (int)vect.y, (int)vect.x );
	}
	
}
