package observerswing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.geometrie.Vecteur;
import model.voiture.Voiture;

public class VoitureObserver implements ObserverSWING{

	private Voiture voiture;
	private BufferedImage car;

	public VoitureObserver(Voiture voiture, String nom) throws IOException {
		super();
		this.voiture = voiture;
		car = ImageIO.read(new File(nom));
	}

	public int getX(){
		return (int) voiture.getPosition().x;
	}
	public int getY(){
		return (int) voiture.getPosition().y;
	}

	public void print(Graphics g){
		// calcul de l'angle à appliquer sur l'image de la voiture pour la rendre
		// cohérente avec la simulation à chaque instant
		// -> dépend de l'image et de l'affichage de l'interface (horizontale ou vertical)
		double angle =  voiture.getDirection().angle( new Vecteur(0, 1)); 

		// construction d'une transformation 
		AffineTransform transform = new AffineTransform();
		// rotation par rapport à un centre à définir (cf javadoc)
		transform.rotate(angle, (car.getWidth() / 2), (car.getHeight() / 2));
		// transformation => transformation d'image
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

		// image finale
		BufferedImage carMod = op.filter(car, null);
		g.drawImage(carMod, getY()-15, getX()-3, null);

		g.setColor(Color.black);
		g.drawString(String.format("v: %.2f d: (%6.2f, %6.2f)", voiture.getVitesse(), 
				voiture.getDirection().getY(), voiture.getDirection().getX()),
				10, 10);
	}
	
}