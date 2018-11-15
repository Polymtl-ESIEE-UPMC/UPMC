package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import model.geometrie.Vecteur;

public class P2PObserver implements ObserverSWING {

	private ArrayList<Vecteur> listpoint;

	public P2PObserver(ArrayList<Vecteur> listpoint) {
		this.listpoint = listpoint;
	}
	
	public void print(Graphics g){
		if(listpoint!=null){
			synchronized(listpoint){
				g.setColor(Color.yellow);
				for(Vecteur vect:listpoint){
					g.drawLine((int)vect.y, (int)vect.x, (int)vect.y, (int)vect.x );
				}
			}
		}
	}

}
