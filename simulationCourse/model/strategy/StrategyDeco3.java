package model.strategy;

import model.voiture.Voiture;

public class StrategyDeco3 implements Strategy{

	private Strategy sBase;
	private Voiture v;
	private double vitesse, acc, com;
	private Commande command;

	public StrategyDeco3(Strategy sBase, Voiture v) {
		super();
		this.sBase=sBase;
		this.v = v;
	}

	public Commande getCommande(){
		command = sBase.getCommande();
		vitesse = v.getVitesse();
		acc = command.getAcc();
		
		if(vitesse < 0.05){
			if(acc < 0) acc = 0.1;
			else acc = acc + 0.1;
		}
		else if(vitesse > 0.2){
			acc = -0.7;
		}
		
		com = Math.min(Math.abs(acc), 1.);
		return new Commande((Math.signum(acc)*com), command.getTurn());
	}

}