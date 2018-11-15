package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import model.geometrie.Vecteur;
import model.radar.RadarSurf;

public class SurfObserver implements ObserverSWING {

	private RadarSurf surf;
	private LinkedList<Vecteur> list;
	
	public SurfObserver(RadarSurf surf){
		this.surf = surf;
	}

	public void print(Graphics g){
		list = surf.getList();
		if(list!=null){
			synchronized(list){
				Vecteur po = surf.getPo();
				g.setColor(Color.yellow);
				for(Vecteur vect:list){
					g.drawLine((int)vect.y, (int)vect.x, (int)po.y, (int)po.x );
				}
				g.setColor(Color.red);
				g.draw3DRect((int)po.y,(int) po.x, 10, 10, true);
			}
		}
		g.setColor(Color.red);
		double a = surf.getSurf();
		int nbob = surf.getNbob();
		g.drawString(String.format("surface %f", a),
				180, 10);
		g.drawString(String.format("nbob %d", nbob),
				330, 10);
	}

}
