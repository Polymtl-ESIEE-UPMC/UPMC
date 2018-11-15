package model.mains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.circuit.Circuit;
import model.geometrie.PastPosition;
import model.strategy.Commande;
import model.strategy.Strategy;
import model.terrain.Terrain;
import model.terrain.TerrainTools;
import model.voiture.Voiture;
import ui.thread.UpdateEventListener;

public class Simulation implements UpdateEventSender {
	
	private Voiture v;
	private Circuit c;
	private Strategy str;
	private LinkedList<Commande> record;
	private ArrayList<UpdateEventListener> ecouteur;
	private int coup=0;
	private boolean finish, fail;
	private static DataInputStream os;
	private LinkedList<PastPosition> bit;

	public Simulation(Voiture v, Circuit c, LinkedList<Commande> record, LinkedList<PastPosition> bit){
		this.v = v;
		this.c = c;
		this.record = record;
		this.bit = bit;
		ecouteur = new ArrayList<UpdateEventListener>();
		coup = 0;
	}

	public void play(){
		finish = false;
		fail = false;
		coup=0;
		while(TerrainTools.isRunnable(c.getTerrain(v.getPosition()))
				&& v.getPosition().x <= c.getHeight() 
				&& v.getPosition().y <= c.getWidth()){
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if(coup%100 == 0 && bit!= null) bit.add(new PastPosition(v.getPosition(), v.getDirection(), v.getVitesse()));
			Commande co = str.getCommande();
			v.drive(co);
			if(record != null) record.add(co);
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

	public LinkedList<Commande> getRecord(){
		return record;
	}

	public static void saveListeCommande(LinkedList<Commande> liste, String filename){
		try {
			DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
			for(Commande c:liste){
				os.writeDouble(c.getAcc());
				os.writeDouble(c.getTurn());
			}
			os.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LinkedList<Commande> loadListeCommande(String filename) throws IOException{
		LinkedList<Commande> liste = null;
		try {
			os = new DataInputStream(new FileInputStream(filename));
			liste = new LinkedList<Commande>();
			double a,t;
			while(true){ // on attend la fin de fichier
				a = os.readDouble();
				t = os.readDouble();
				liste.add(new Commande(a,t));
			}
		} catch (EOFException e){
			return liste;
		}
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
		saveListeCommande(record, "listecommande.txt");
		for(UpdateEventListener listen: ecouteur)
			listen.save();
	}

	public boolean isFinished(){
		return finish;
	}
	
	public boolean isFailed(){
		return fail;
	}

	public void getChange(Strategy str){
		this.str = str;
	}
	
	public void setC(int c){
		coup = c;
	}
	
	public int getC(){
		return coup;
	}
	
}