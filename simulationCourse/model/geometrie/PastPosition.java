package model.geometrie;

public class PastPosition {
	
	public final Vecteur p, d;
	public final double vitesse;
	
	public PastPosition(Vecteur p, Vecteur d, double vitesse){
		this.p = p;
		this.d = d;
		this.vitesse = vitesse;
	}
}
