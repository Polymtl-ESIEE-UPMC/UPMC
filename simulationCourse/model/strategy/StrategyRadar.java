package model.strategy;


import model.geometrie.Vecteur;
import model.radar.Radar;
import model.radar.RadarLight;
import model.radar.RadarSurf;
import model.voiture.Voiture;

public class StrategyRadar implements Strategy {

	private Radar r, r2;
	private Voiture v;
	private RadarLight light;
	private RadarSurf surface;
	private Vecteur raylight;
	private int besti, needi;
	private double the, needthe, speed, ask, vitmust, com, turn;

	public StrategyRadar(Radar r, Radar r2, Voiture v, RadarLight light, RadarSurf surface){
		this.r = r;
		this.r2 = r2;
		this.v = v;
		this.light = light;
		this.surface = surface;
	}

	public Commande getCommande(){
		besti = r.getBestIndex(v.getPosition(), v.getDirection());
		the = r.thetas()[besti];
		light.score(v, the);
		raylight = light.getPo();
		if(surface != null) surface.score(v, the);
		needi = r2.getBestIndex(raylight, v.getDirection());
		needthe = r2.thetas()[needi];
		speed = v.getVitesse();
		ask = 0.0;
		vitmust = 0.0;

		if(speed==0.0) ask = 1.0;
		else if(speed<=0.05) ask = 0.5;
		else if(Math.abs(the)>Math.PI/1.5 && speed>0.7) {
			ask = -1.;
		}
		else if(Math.abs(the)>Math.PI/1.5 && speed>0.3) {
			ask = -0.5;
		}
		else if(Math.abs(the)>Math.PI/2 && speed>0.7) {
			ask = -0.7;
		}
		else if(Math.abs(the)>Math.PI/2 && speed>0.3) {
			ask = -0.4;
		}
		else{
			if(Math.abs(needthe)>Math.PI/1.5 && speed>0.7) ask = -1.;
			else if(Math.abs(needthe)>Math.PI/1.5 && speed>0.3) ask = -0.5;
			else if(Math.abs(needthe)>Math.PI/4){
				vitmust = v.getVitesseMustHave(Math.abs(the));
				ask = (vitmust - v.getVitesse())/v.getC();
			}
			else if(Math.abs(needthe)>Math.PI/18){
				vitmust = v.getVitesseMustHave(Math.abs(the));
				ask = (vitmust*3 - v.getVitesse())/v.getC();
			}
			else{
				ask = 1.;
			}
		}
		turn = Math.min(Math.abs(the)/v.getBraquage(), v.getMaxTurn());
		com = Math.min(Math.abs(ask), 1.);
		return new Commande((Math.signum(ask)*com), Math.signum(the)*turn);
	}

}