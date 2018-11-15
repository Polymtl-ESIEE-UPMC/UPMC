package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;

public class ZoneDangerObserver implements ObserverSWING {
	private ArrayList<ZoneDanger> listzone;
	private Circuit c;
	private Vecteur vect, zone;

	public ZoneDangerObserver(ArrayList<ZoneDanger> listzone, Circuit c) {
		this.listzone = listzone;
		this.c = c;
	}

	public void print(Graphics g){
		if(listzone!=null){
			synchronized(listzone){
				g.setColor(Color.yellow);
				for(int i=0; i<c.getHeight(); i++){
					for(int j=0; j<c.getWidth(); j++){
						vect = new Vecteur(i, j);
						for(ZoneDanger zo:listzone){
							zone = new Vecteur(zo.pdanger, vect);
							if(zone.norme() < zo.rayon+0.5 &&
									zone.norme() > zo.rayon-0.5) g.drawLine((int)vect.y, (int)vect.x, (int)vect.y, (int)vect.x );
						}
					}
				}
			}
		}
	}
}
