package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import model.radar.RadarLight;

public class LightObserver implements ObserverSWING {

	private RadarLight light;

	public LightObserver(RadarLight light){
		this.light = light;
	}

	public void print(Graphics g){
		g.setColor(Color.RED);
		g.drawOval((int) light.getPo().y, (int) light.getPo().x, 10, 10);
	}

}
