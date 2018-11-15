package observeur;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.radar.Dijkstra;

public class DijkstraObserveur implements ObserveurImage {
	
	private Dijkstra dij;
	private double[][] dist;
	
	public DijkstraObserveur(Dijkstra dij){
		this.dij = dij;
	}
	
	public void print(BufferedImage im) {
		while(dij.isFinished() || dist == null){
			dist = dij.getDist();
		}
		for(int i = 0; i<dist.length; i++)
			for(int j = 0; j<dist[0].length; j++){
				if(dist[i][j]==Double.POSITIVE_INFINITY) im.setRGB(j, i, Color.CYAN.getRGB());
				else im.setRGB(j, i, (new Color((int)(((int) dist[i][j]/255)), 0 , 0)).getRGB());
			}
		im.setRGB((int)dij.c.getPointDepart().y, (int)dij.c.getPointDepart().x, Color.yellow.getRGB());
	}
	
}
