package model.mains;

import java.util.ArrayList;

import model.circuit.Circuit;
import model.strategy.Commande;
import model.terrain.Terrain;
import model.terrain.TerrainTools;
import model.voiture.Voiture;
import ui.face.classicmode.KeyboardCustom;
import ui.thread.UpdateEventListener;

public class SimulationUser implements UpdateEventSender {
	private Voiture v;
	private Circuit c;
	private ArrayList<UpdateEventListener> ecouteur;
	private int coup=0;
	private boolean finish, fail;
	private Commande co;
	private double a, r;
	private KeyboardCustom key;

	public SimulationUser(Voiture v, Circuit c, KeyboardCustom key){
		this.v = v;
		this.c = c;
		this.key = key;
		ecouteur = new ArrayList<UpdateEventListener>();
	}

	public void play(){
		finish = false;
		fail = false;
		coup=0;
		while(TerrainTools.isRunnable(c.getTerrain(v.getPosition()))
				&& v.getPosition().x <= c.getHeight() 
				&& v.getPosition().y <= c.getWidth()){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!key.start()) coup--;
			a = key.getA();
			r = key.getR();
			if(a == 9) a = -0.1;
			if(r == 9) r = 0;
			co = new Commande(a,r);
			v.drive(co);
			coup++;
			update();
			if(c.getTerrain(v.getPosition()) == Terrain.EndLine){
				if((v.getDirection()).prodscal(c.getDirectionArrivee())<0.0 && coup>5){
					break;
				}
				finish = true;
				break;
			}
		}
		if(!finish){
			System.out.println("Crash!");
			fail = true;
		}/*else{
			JFrame win = new JFrame();
			ImageIcon ii = new ImageIcon("gifwin.gif");
			win.setContentPane(new JLabel(ii));           
            win.pack();
            win.setLocationRelativeTo(null);
            win.setVisible(true);
		}*/
		System.out.println("Nombre de coups : "+coup);
	}

	public void add(UpdateEventListener listener) {
		ecouteur.add(listener);
	}
	
	public void remove(UpdateEventListener listener){
		ecouteur.remove(listener);
	}

	public void update() {
		for(UpdateEventListener listen: ecouteur)
			listen.manageUpdate();
	}

	public void save() {
		for(UpdateEventListener listen: ecouteur)
			listen.save();
	}

	public boolean isFinished(){
		return finish;
	}
	
	public boolean isFailed(){
		return fail;
	}
	
}
