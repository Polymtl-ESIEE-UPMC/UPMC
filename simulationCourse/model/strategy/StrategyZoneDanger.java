package model.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;
import model.voiture.Voiture;

public class StrategyZoneDanger implements Strategy{

	private Strategy sBase;
	private Voiture v;
	private Vecteur p;
	private ZoneDanger zd;
	private ArrayList<ZoneDanger> list;
	private Iterator<ZoneDanger> ite;
	private Commande command;
	private double ask, com;

	public StrategyZoneDanger(Strategy sBase, Voiture v, ArrayList<ZoneDanger> list){
		this.sBase = sBase;
		this.v = v;
		this.list = list;
	}

	public Commande getCommande(){
		command = sBase.getCommande();
		ite = list.iterator();
		while(ite.hasNext()){
			zd = ite.next();
			p = zd.pdanger;
			ask = command.getAcc();
			if(new Vecteur(v.getPosition(), p).norme() < zd.rayon){
				if(v.getVitesse()>zd.vitmax) ask = -1.0;
				else if(v.getVitesse()<0.02) ask = 0.05;
				com = Math.min(Math.abs(ask), 1.0);
				return new Commande((Math.signum(ask)*com), command.getTurn());
			}
		}
		return command;
	}

}
