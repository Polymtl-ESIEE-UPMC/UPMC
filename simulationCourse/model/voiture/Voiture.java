package model.voiture;

import model.geometrie.Vecteur;
import model.strategy.Commande;

public interface Voiture {

	// pour le pilotage
	public void drive(Commande c);
	public double getMaxTurn(); 
	// pour l'observation
	public double getVitesse();
	public Vecteur getPosition();
	public Vecteur getDirection();
	public double getBraquage();
	public double getVitesseMustHave(double alpha);
	public double getC();
	public void reStart(Vecteur p, Vecteur d, double speed);
	
}