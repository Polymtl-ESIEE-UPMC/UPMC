package model.strategy;

import model.radar.RadarSurf;
import model.voiture.Voiture;

public class StrategyDeco implements Strategy{

	private Strategy sBase;
	private Voiture v;
	private RadarSurf surf;
	private double surface, vitesse, acc, com;
	private int nbob;
	private Commande command;

	public StrategyDeco(Strategy sBase, Voiture v, RadarSurf surf) {
		super();
		this.sBase=sBase;
		this.v = v;
		this.surf = surf;
	}

	public Commande getCommande(){
		command = sBase.getCommande();
		nbob = surf.getNbob();
		surface = surf.getSurf();
		vitesse = v.getVitesse();
		acc = command.getAcc();
		
		if(vitesse<0.05){
			if(acc < 0) acc = 0.3;
			else acc = acc + 0.3;
//			System.out.println("1");
		}
		else if(surface < 30 && vitesse>0.6){
			acc = acc - 1.0;
//			System.out.println("2");
		}else if(surface < 50 && vitesse>0.7){
			acc = acc - 0.5;
//			System.out.println("3");
		}else if(surface < 60 && vitesse>0.8){
			acc = acc - 0.5;
//			System.out.println("4");
		}
/*		else{
			if(surface<15){
				acc = acc - 1.7;
			}
			else if(surface<50 && vitesse>0.6) acc = acc - 1.5;
			else if(surface<55 && vitesse>0.8) acc = acc - 1.0;
		}*/
		com = Math.min(Math.abs(acc), 1.);
		return new Commande((Math.signum(acc)*com), command.getTurn());
	}

}

