package observer3D;

import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SHININESS;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColorMaterial;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightModel;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMaterial;
import static org.lwjgl.opengl.GL11.glMaterialf;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import ui.thread.UpdateEventListener;
import observer3D.Observeur3D;
import observer3D.VoitureObserveur3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;


public class IHM3Dlight extends AWTGLCanvas implements  KeyListener, UpdateEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Observeur3D> toDraw;

	// gestion de la caméra
	private float x=0, y=0, z=0;
	private float facteur = 10;
	private double zoom = 1.3;

	// matériaux/éclairage de base
	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight; 
	private FloatBuffer lModelAmbient;


	public IHM3Dlight() throws LWJGLException {
		
		toDraw = new ArrayList<Observeur3D>();
		this.addKeyListener(this); // je m'auto-ecoute pour les touches
	}

	protected void initGL() {
		glEnable(GL_TEXTURE_2D); // activation des textures
		glEnable(GL_DEPTH_TEST); // gestion de la profondeur
	}

	private void glsettings(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
		glViewport(0, 0, getWidth(), getHeight());
		glClearColor(0.f, 0.f, 0.f, 1.0f);
		glMatrixMode(GL_PROJECTION);

		glLoadIdentity();

		// definition de la taille de la fenetre o� l'on regarde
		
		glOrtho((float) -getWidth()*zoom*1.15, (float) getWidth()*zoom*0.85, 
				(float) -getHeight()*zoom*0.5, (float) getHeight()*zoom*1.5, -2000, 2000);

		glMatrixMode(GL_MODELVIEW);

		initLightArrays();

		glShadeModel(GL_SMOOTH);

		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);

		glLight(GL_LIGHT0, GL_POSITION, lightPosition);
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient); 

		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);

		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

		glLoadIdentity();
	
	}

	private void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(-300.0f).put(-300.0f).put(300.0f).put(0.0f).flip();

		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.8f).put(0.8f).put(0.8f).put(1.0f).flip();
	}	


	public void paintGL() {
		try {
			glsettings();
			
			
			GLU.gluLookAt( x, y, z, 500, 500, -500, 0, 0, 1);
			
			
			for(Observeur3D d: toDraw){
				glPushMatrix();
				d.drawGL();
				glPopMatrix();
			}

			
			swapBuffers();
			
			repaint();
		} catch (LWJGLException e) {throw new RuntimeException(e);}
		
	}

	
	public void add(Observeur3D d){
		toDraw.add(d);
	}





	public void keyPressed(KeyEvent e) {
		System.out.println("x, y, z:"+x+" "+y+" "+z);

		if(e.getKeyChar() == 'z')
			z+=facteur;
		if(e.getKeyChar() == 'Z')
			z-=facteur;

		if(e.getKeyChar() == 'x')
			x+=facteur;
		if(e.getKeyChar() == 'X')
			x-=facteur;

		if(e.getKeyChar() == 'y')
			y+=facteur;
		if(e.getKeyChar() == 'Y')
			y-=facteur;
		
		if(e.getKeyChar() == 'w')
			zoom *= 1.1;
		if(e.getKeyChar() == 'W')
			zoom *= 0.9;
		
	}


	public void keyReleased(KeyEvent e) {
	}


	public void keyTyped(KeyEvent e) {
	
	}

	public void manageUpdate() {
		
//			repaint();
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save2() {
		// TODO Auto-generated method stub
		
	}



}