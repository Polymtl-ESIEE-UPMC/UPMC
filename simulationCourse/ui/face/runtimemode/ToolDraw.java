package ui.face.runtimemode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSpinner;

import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;
import model.terrain.Terrain;
import ui.thread.TheBrain;

public class ToolDraw implements MouseListener, ButtonCustom {
	
	private Terrain ter;
	private TheBrain t;
	private double rayon, x, y, vitmax, zone;
	private boolean modedraw, modep2p, modezd;
	private JSpinner spin1, spin2;
	
	public ToolDraw(TheBrain t){
		ter = null;
		this.t = t;
		modedraw = false;
		modep2p = false;
		modezd = false;
	}
	
	public void mouseClicked(MouseEvent e) {
		x = e.getY();
		y = e.getX();
		if(modedraw){
			t.changeCircuit(new Vecteur(x, y), ter, rayon);
		}else if(modep2p){
			t.addPoints(new Vecteur(x, y));
		}else if(modezd){
			vitmax = (double)spin2.getValue();
			if(vitmax > 0.9) vitmax = 0.9;
			zone = (double)spin1.getValue();
			if(zone > 300) zone = 300; 
			t.addZones(new ZoneDanger(new Vecteur(x,y), zone, vitmax));
		}else{
			System.out.println("you need to validate your choice");
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void getT(Terrain ter, double rayon){
		this.ter = ter;
		this.rayon = rayon;
	}
	
	public void switchMode(int a){
		if(a == 1) modedraw = true;
		if(a == 2) modep2p = true;
		if(a == 3) modezd = true;
	}
	
	public void reStart(){
		modedraw = false;
		modep2p = false;
		modezd = false;
	}
	
	public void setSpin(JSpinner spin1, JSpinner spin2){
		this.spin1 = spin1;
		this.spin2 = spin2;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
}
