package model.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.terrain.TerrainTools;
import model.voiture.Voiture;

public class StrategyP2P implements Strategy {

	private Strategy sBase;
	private Voiture v;
	private Vecteur pfan, dir, p;
	private Circuit c;
	private ArrayList<Vecteur> list;
	private Iterator<Vecteur> ite;
	private Commande command;
	private double the, ask, vitmust, turn, com;

	public StrategyP2P(Strategy sBase, Voiture v, Circuit c, ArrayList<Vecteur> list) {
		this.sBase = sBase;
		this.v = v;
		this.c = c;
		this.list = list;
	}

	public Commande getCommande(){
		command = sBase.getCommande();
		ite = list.iterator();
		while(ite.hasNext()){
			p = ite.next();
			if(!TerrainTools.isRunnable(c.getTerrain(p))) ite.remove();
			else if(new Vecteur(v.getPosition(), p).norme() < 1) ite.remove();
			else if(testVisible()){
				the = (v.getDirection()).angle(dir);
				ask = 0.0;
				vitmust = 0.0;
				vitmust = v.getVitesseMustHave(Math.abs(the));
				ask = (vitmust - v.getVitesse())/v.getC();
				turn = Math.min(Math.abs(the)/v.getBraquage(), v.getMaxTurn());
				com = Math.min(Math.abs(ask), 1.);
				return new Commande((Math.signum(ask)*com), Math.signum(the)*turn);
			}
		}
		return command;
	}

	public boolean testVisible(){
		dir = new Vecteur(v.getPosition(), p);
		dir.unitVec();
		pfan = v.getPosition();
		while( TerrainTools.isRunnable(c.getTerrain(pfan)) 
				&& pfan.x < c.getHeight() 
				&& pfan.y < c.getWidth()){
			pfan = pfan.add(dir.mult(0.01));
			if(new Vecteur(pfan, p).norme() < 1){
				return true;
			}
		}
		return false;
	}

}
