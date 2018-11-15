package observer3D;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Color;
import java.io.IOException;

import model.voiture.Voiture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class VoitureObserveur3D implements Observeur3D{
	
	
	private Sphere sphere;
	private Voiture voiture;
	
	private int size;

	public VoitureObserveur3D(Voiture voiture) {
		super();
		this.voiture = voiture;
		sphere = new Sphere();
		size = 20;
	}

	public float getX(){
		return  (float) voiture.getPosition().getX();
	}
	public float getY(){
		return  (float) voiture.getPosition().getY();
	}
	public float getZ() {
		return 0;
	}
	public float getDX(){
		return  (float) voiture.getDirection().getX();
	}
	public float getDY(){
		return  (float) voiture.getDirection().getY();
	}
	public float getDZ() {
		return 0;
	}


	public void drawGL() {
		
		glColor3f(1, 0, 1);
		glTranslatef(getX(), getY(), 0);
		sphere.draw(size, 10, 10);
		glTranslatef(-getX(), -getY(), 0);
		glColor3f(1, 1, 1);
	}

	
}
