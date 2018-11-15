package model.radar;

import model.geometrie.Vecteur;

public interface Radar {
	public double[] scores(Vecteur v, Vecteur v1); // score de chaque branche
	public double[] distancesInPixels(); // pour l'observer
	public int getBestIndex(Vecteur v, Vecteur v1); // meilleur indice
	public double[] thetas(); // angles de chaque faisceau
}
