package observeur;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.voiture.Voiture;

public class VoitureObserveur implements ObserveurImage{

	private Voiture voiture;

	public VoitureObserveur(Voiture voiture) {
		this.voiture = voiture;
	}

	public int getX(){ // a inverser pour l'affichage horizontal
		return (int) voiture.getPosition().x;
	}
	public int getY(){
		return (int) voiture.getPosition().y;
	}

	public Color getColor() {
		if(voiture.getVitesse()<0.3) // vitesse faible -> cyan 
			return new Color(0, (int)(voiture.getVitesse()*255*2), (int) (voiture.getVitesse()*255*2));

		if(voiture.getVitesse() == 0.9)
			return new Color((int)(voiture.getVitesse()*255),  (int) (voiture.getVitesse()*255), 0);

		return new Color((int)(voiture.getVitesse()*255), 0, (int) (voiture.getVitesse()*255));
	}

	public void print(BufferedImage im) {
		im.setRGB((int) voiture.getPosition().y, (int) voiture.getPosition().x, getColor().getRGB());	
	}

}
