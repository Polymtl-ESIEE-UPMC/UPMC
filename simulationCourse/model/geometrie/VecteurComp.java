package model.geometrie;

import java.util.Comparator;

public class VecteurComp implements Comparator<Vecteur> {
	
	private double[][] dist;

	public VecteurComp(double[][] dist) {	
		this.dist = dist;	
	}
	
	public int compare( Vecteur v1,  Vecteur v2) {
		if(dist[(int) v1.x][(int) v1.y]>dist[(int) v2.x][(int) v2.y])
			return 1;
		else if (dist[(int) v1.x][(int) v1.y]==dist[(int) v2.x][(int) v2.y])
			return 0;
		return -1;
	}
}