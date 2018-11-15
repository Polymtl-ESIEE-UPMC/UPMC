package model.radar;

import model.circuit.Circuit;

public class RadarFactory {

	public static final double epsi = 0.05;
	public static final double pi = Math.PI;
	public static final double[] thetas = new double[21];
	public static final double[] shetas = new double[101];

	public static Radar build2(Circuit c){
		for(int i = 0; i<thetas.length; i++){
			if(i<=thetas.length/4){
				thetas[i] = (pi/2) - (i*23*pi/50/(thetas.length/4));
			}else if(i > thetas.length/4 && i < 3*thetas.length/4){
				thetas[i] = (pi/25) - ((i-thetas.length/4)*2*pi/25/(thetas.length/2));
			}else{
				thetas[i] = (-pi/2) + (((thetas.length/4)-(i-3*thetas.length/4))*23*pi/50/(thetas.length/4));
			}
		}
		return new RadarClassique(epsi, c, thetas);
	}

	public static Radar build(Circuit c, Dijkstra dij){
		for(int i = 0; i<shetas.length; i++){
			if(i<=shetas.length/4){
				shetas[i] = (pi) - (i*24*pi/25/(shetas.length/4));
			}else if(i > shetas.length/4 && i < 3*shetas.length/4){
				shetas[i] = (pi/25) - ((i-shetas.length/4)*2*pi/25/(shetas.length/2));
			}else{
				shetas[i] = (-pi) + (((shetas.length/4)-(i-3*shetas.length/4))*24*pi/25/(shetas.length/4));
			}
//			she[i] = (pi)-(i*((pi)/((she.length/2))));
		}
		return new RadarDijkstra(epsi,c,shetas,dij);
	}
	
}