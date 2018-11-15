package model.mains;

import java.io.IOException;
import java.util.LinkedList;
import model.circuit.Circuit;
import model.circuit.CircuitFactoryFromFile;
import model.filtre.Filtre;
import model.filtre.FiltreGenerique;
import model.radar.Dijkstra;
import model.radar.Radar;
import model.radar.RadarFactory;
import model.radar.RadarLight;
import model.radar.RadarSurf;
import model.strategy.Commande;
import model.strategy.Strategy;
import model.strategy.StrategyFactory;
import model.voiture.Voiture;
import model.voiture.VoitureFactory;
import observeur.Controleur;
import observeur.DijkstraObserveur;
import observeur.VoitureObserveur;
import ui.face.menustart.Windowmenu;

public class Main {
	
	public static void main(String[] args) throws IOException {	
		Windowmenu menu = new Windowmenu();
		menu.go("Asphalt 7");
		
		//test
/*		String filename = "bond_safe.trk";
		LinkedList<Commande> record = new LinkedList<Commande>();
		Circuit c = CircuitFactoryFromFile.build(filename);
		if(filename == "aufeu.trk"){
			Circuit c1 = CircuitFactoryFromFile.build(filename);
			Filtre f = new Filtre(c,c1);
			f.filtrer();
		}
		if(filename == "bond_safe.trk"){
			boolean [][] b = { {true,false},{true,false} };
			FiltreGenerique f = new FiltreGenerique(b);
			f.filtrer(c.getMatrix());
		}
		Voiture v = VoitureFactory.build(c);
		Dijkstra dij = new Dijkstra(c);

		Controleur co2 = new Controleur(c);
		co2.add(new DijkstraObserveur(dij));
		dij.add(co2);
		dij.algo();

		Radar r2 = RadarFactory.build2(c);
		Radar r = RadarFactory.build(c,dij);
		Radar r3 = RadarFactory.build(c,dij);
		Radar r5 = RadarFactory.build(c,dij);
		RadarLight light = new RadarLight(c);
		RadarSurf surface = new RadarSurf(c,r5);
		Simulation simu = new Simulation(v,c,record);
		
		Strategy str = StrategyFactory.build(r, r3, v, light, surface);

		Controleur co = new Controleur(c);
		co.add(new VoitureObserveur(v));
		simu.add(co);
		simu.getChange(str);
		simu.play();
		simu.save();
		dij.save();*/
		
	}

}


