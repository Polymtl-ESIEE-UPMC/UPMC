package model.radar;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.terrain.Terrain;
import model.terrain.TerrainTools;

public class RadarClassique implements Radar {
	
	private double epsi;
	private Circuit c;
	private double [] thetas, cpt, dipix;
	private Vecteur pfan, d, arr;
	

	public RadarClassique(double e, Circuit c1, double[] t){
		epsi = e;
		c = c1;
		thetas = t;
		cpt = new double[thetas.length];
		dipix = new double[thetas.length];
	}

	public double[] scores(Vecteur position, Vecteur direction) {
		for( int i = 0; i<thetas.length; i++){
			pfan = position;
			d = direction;
			arr = c.getDirectionArrivee();
			d = d.rotation(thetas[i]);
			d = d.unitVec();
			cpt[i]=0.0;
			while( TerrainTools.isRunnable(c.getTerrain(pfan))==true 
					&& pfan.x < c.getHeight() 
					&& pfan.y < c.getWidth() ){
				cpt[i]++;
				dipix[i] = cpt[i]*epsi;
				if(c.getTerrain(pfan)==Terrain.EndLine)
					if(d.prodscal(arr)<0.0){
						cpt[i]=0.0;
						break;
					}/*else if(d.prodscal(c.getDirectionArrivee())>0.0){
						cpt[i]=cpt[i]*10+1000;
						System.out.println(cpt[i]);
						break; 
					}*/ 
				pfan = pfan.add(d.mult(epsi));
			}
		}
		return cpt;
	}

	public String toString(){
		String a= "";
		for(int j=0; j<thetas.length; j++){
			a += " Score " +cpt[j];
		}
		return a;
	}

	public double[] distancesInPixels() {
		return dipix;
	}
	
	public int getBestIndex(Vecteur position, Vecteur direction) {
		scores(position, direction);
		double max = 0.0;
		int imax = 0;
		for(int i = 0; i<cpt.length; i++){
			if(cpt[i]>max){
				max = cpt[i];
				imax = i;
			}
		}
		return imax;
	}
	
	public double[] thetas() {
		return thetas;
	}
}

