package model.radar;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.terrain.Terrain;
import model.terrain.TerrainTools;

public class RadarDijkstra implements Radar {

	private double epsi;
	private Circuit c;
	private double[] thetas, cpt, dipix;
	private double[][] dist;
	private Dijkstra dij;
	private Vecteur pfan, d;
	private int lg;
	
	public RadarDijkstra(double e, Circuit c1, double[] t, Dijkstra dij){
		epsi = e;
		c = c1;
		thetas = t;
		this.dij = dij;
		cpt = new double[thetas.length];
		dipix = new double[thetas.length];
		dist = dij.getDist();
	}

	public double[] scores(Vecteur position, Vecteur direction){
		if(dij.isFinished()) dist = dij.getDist();
		for( int i = 0; i<thetas.length; i++){
			pfan = position;
			d = direction;
			d = d.rotation(thetas[i]);
			d = d.unitVec();
			double min = dist[(int) pfan.x][(int) pfan.y];
			cpt[i] = min;
			lg = -1;
			while( TerrainTools.isRunnable(c.getTerrain(pfan)) 
					&& pfan.x <= c.getHeight() 
					&& pfan.y <= c.getWidth() ){
				lg++;
				dipix[i]=lg*epsi;
				if(min>dist[(int) pfan.x][(int) pfan.y]){
					min=dist[(int) pfan.x][(int) pfan.y];
				}
				cpt[i]=min;
				if(c.getTerrain(pfan)==Terrain.EndLine)
					if(d.prodscal(c.getDirectionArrivee())<0.0){
						cpt[i]=Double.POSITIVE_INFINITY;
						break;
					}else if(d.prodscal(c.getDirectionArrivee())>0.0){
						break;
					}
				pfan = pfan.add(d.mult(epsi));
			}
		}
		return cpt;
	}

	public double[] distancesInPixels() {
		return dipix;
	}

	public int getBestIndex(Vecteur position, Vecteur direction) {
		cpt = scores(position, direction); //take the scores
		double min=cpt[0];
		int imin = 0;
		for(int j = 0; j<cpt.length; j++){
			if(min>cpt[j] ){
					min = cpt[j];
					imin = j;
			}else if( min == 0.0 && min == cpt[j]  && dipix[j]<dipix[imin]){
				min = cpt[j];
				imin = j;
			}
		}
		return imin;
	}

	public double[] thetas() {
		return thetas;
	}

	public String toString(){
		String a= "";
		for(int j=0; j<thetas.length; j++){
			a += "\n Score " +cpt[j];
		}
		return a;
	}
}
