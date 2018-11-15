package model.strategy;

import java.util.ArrayList;

import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;
import model.radar.Radar;
import model.radar.RadarLight;
import model.radar.RadarSurf;
import model.voiture.Voiture;

public class StrategyFactory {

	public static Strategy build(Radar r, Radar r3, Voiture v, RadarLight light, RadarSurf surface){ //Dijk
		return new StrategyRadar(r, r3, v, light, surface);
	}
	
	public static Strategy build2(Radar r, Voiture v, RadarSurf surface){ //Classic
		return new StrategyClassic(r, v, surface);
	}
	
	public static Strategy build3(Strategy str, Voiture v, RadarSurf surface){ //Deco
		return new StrategyDeco(str, v, surface);
	}
	
	public static Strategy build4(Strategy str, Voiture v, Circuit c, ArrayList<Vecteur> list){ //P2P
		return new StrategyP2P(str, v, c, list);
	}
	
	public static Strategy build5(Strategy sBase, Voiture v, ArrayList<ZoneDanger> listdanger){ //ZoneDanger
		return new StrategyZoneDanger(sBase, v, listdanger);
	}
	
	public static Strategy build6(Strategy str, Voiture v){ //Deco2
		return new StrategyDeco2(str, v);
	}
	public static Strategy build7(Strategy str, Voiture v){ //Deco3
		return new StrategyDeco3(str, v);
	}
	
}
