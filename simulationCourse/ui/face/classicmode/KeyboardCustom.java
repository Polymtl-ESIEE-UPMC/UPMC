package ui.face.classicmode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class KeyboardCustom extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double a, r;
	public boolean start;
	
	public KeyboardCustom(){
		addKeyListener(this);
		start = false;
		setSize(300,300);
		setVisible(true);
	}
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		start = true;
		if(e.getKeyCode() == 37){
			r = 0.05;
		}else if(e.getKeyCode() == 39){
			r = -0.05;
		}
		if(e.getKeyCode() == 38){
			a = 1.0;
		}else if(e.getKeyCode() == 40){
			a = -1.0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 37 || e.getKeyCode() == 39){
			r = 9;
		}else if(e.getKeyCode() == 38 || e.getKeyCode() == 40){
			a = 9;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public double getA(){
		return a;
	}
	public double getR(){
		return r;
	}
	
	public boolean start(){
		return start;
	}
}
