package model.strategy;

import model.radar.Radar;
import model.radar.RadarSurf;
import model.voiture.Voiture;

public class StrategyClassic implements Strategy {
	
	private Radar r;
	private Voiture v;
	private RadarSurf surface;
	private int besti;
	private double the, ask, vitmust, turn, com;

	public StrategyClassic(Radar r, Voiture v, RadarSurf surface){
		this.r = r;
		this.v = v;
		this.surface = surface;
	}

	public Commande getCommande(){
		besti = r.getBestIndex(v.getPosition(), v.getDirection());
		the = r.thetas()[besti];
		if(surface != null) surface.score(v, the);
		ask = 0.0;
		vitmust = 0.0;
		vitmust = v.getVitesseMustHave(Math.abs(the));
		ask = (vitmust - v.getVitesse())/v.getC();
		turn = Math.min(Math.abs(the)/v.getBraquage(), v.getMaxTurn());
		com = Math.min(Math.abs(ask), 1.);
		return new Commande((Math.signum(ask)*com), Math.signum(the)*turn);
	}
	
}
