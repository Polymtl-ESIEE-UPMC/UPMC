package observer3D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.circuit.Circuit;
import model.terrain.TerrainTools;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class CircuitObserveur3D implements Observeur3D{
	private Circuit track;
	
	private Texture texture; // requiert slick
	private int width, height;
	private int widthR, heightR;
	private float widthPc, heightpc;
	
	public CircuitObserveur3D(Circuit track) {
		super();
		this.track = track;		
		texture = null;
	}

	public void drawGL()  {

		if(texture == null){
			BufferedImage im = TerrainTools.imageFromCircuit(track.getMatrix()); // creation
			try {
				ImageIO.write(im, "PNG", new File("terrain.png"));
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("terrain.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			widthR = im.getWidth();
			heightR = im.getHeight();
			width = texture.getTextureWidth();
			height = texture.getTextureHeight();
			widthPc = (float) widthR/width;
			heightpc = (float) heightR/height;
			System.out.println("Texture loaded: "+texture);
			System.out.println(">> Image width: "+texture.getImageWidth());
			System.out.println(">> Image height: "+texture.getImageHeight());
			System.out.println(">> Texture width: "+texture.getTextureWidth());
			System.out.println(">> Texture height: "+texture.getTextureHeight());
			System.out.println(">> Texture ID: "+texture.getTextureID());
		
		}
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glPixelStorei( GL11.GL_UNPACK_ALIGNMENT, 1 );

		// draw a quad textured to match the sprite
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0); GL11.glVertex3f(0, 0, 0);
			GL11.glTexCoord2f(0, heightpc); GL11.glVertex3f(0, heightR, 0);
			GL11.glTexCoord2f(widthPc, heightpc); GL11.glVertex3f(widthR,heightR, 0);
			GL11.glTexCoord2f(widthPc, 0); GL11.glVertex3f(widthR,0, 0);
		}
		GL11.glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

	}


}
