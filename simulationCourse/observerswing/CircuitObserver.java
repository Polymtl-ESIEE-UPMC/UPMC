package observerswing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import model.circuit.Circuit;
import model.terrain.TerrainTools;


public class CircuitObserver implements ObserverSWING {
	
	private BufferedImage im;
	private Circuit c;
	public boolean go;

	public CircuitObserver(Circuit c) {
		this.c = c;
		im = TerrainTools.imageFromCircuit(c.getMatrix());
	}
	
	public void print(Graphics g){
		if(go){
			im = TerrainTools.imageFromCircuit(c.getMatrix());
			go = false;
		}
		g.drawImage(im,0,0,null);
	}

}
