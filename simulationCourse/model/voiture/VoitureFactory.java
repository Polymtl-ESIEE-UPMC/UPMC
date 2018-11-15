package model.voiture;

import model.circuit.Circuit;
import model.geometrie.Vecteur;

public class VoitureFactory {
	  public static final double[] tabVitesse     = {0.1, 0.2, 0.3, 0.4, 0.5, 
		  											0.6, 0.7, 0.8, 0.9, 1.}; 
	  public static final double[] tabTurn        = {1.,  0.9, 0.75, 0.6, 0.5, 0.4, 
			  								0.3, 0.2, 0.1, 0.05};
	  public static final double vmax = 0.9;
	  public static final double alpha_c = 0.005;
	  public static final double braquage = 0.1;
	  public static final double alpha_f = 0.0002;
	  public static final double beta_f = 0.0005;

	  private static double vitesse = 0.0; // vitesse initiale
	  
	  public static Voiture build(Circuit c){
		  Vecteur position = c.getPointDepart();
		  Vecteur direction = c.getDirectionDepart();
		  return new VoitureImpl(vmax,braquage,
			        alpha_c, alpha_f, beta_f,
					vitesse, position, direction, tabVitesse, tabTurn);
	  }
}
