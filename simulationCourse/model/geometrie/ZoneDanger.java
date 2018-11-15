package model.geometrie;

public class ZoneDanger {
	
	public final Vecteur pdanger;
	public final double rayon, vitmax;
	
	public ZoneDanger(Vecteur pdanger, double rayon, double vitmax){
		this.pdanger = pdanger;
		this.rayon = rayon;
		this.vitmax = vitmax;
	}
	
}
