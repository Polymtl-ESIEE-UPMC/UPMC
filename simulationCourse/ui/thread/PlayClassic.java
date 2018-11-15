package ui.thread;

import java.io.IOException;
import java.util.ArrayList;

import model.circuit.Circuit;
import model.circuit.CircuitFactoryFromFile;
import model.filtre.Filtre;
import model.filtre.FiltreGenerique;
import model.geometrie.Vecteur;
import model.mains.Simulation;
import model.mains.SimulationUser;
import model.radar.Dijkstra;
import model.radar.Radar;
import model.radar.RadarFactory;
import model.radar.RadarLight;
import model.radar.RadarSurf;
import model.strategy.Strategy;
import model.strategy.StrategyFactory;
import model.voiture.Voiture;
import model.voiture.VoitureFactory;
import observerswing.CircuitObserver;
import observerswing.IHMSwing;
import observerswing.LightObserver;
import observerswing.Radar3Observer;
import observerswing.RadarObserver;
import observerswing.TrajectoireObserver;
import observerswing.VoitureObserver;
import ui.face.classicmode.KeyboardCustom;

public class PlayClassic {

	private IHMSwing ihm;
	private ThreadSimu[] tsimu;
	private ThreadUser tsimuu;
	private Circuit c;
	private Voiture[] v;
	private Dijkstra dij;
	private Radar[] r;
	private Radar[] r1;
	private RadarLight light;
	private RadarSurf surface = null;
	private Strategy[] str;
	private Simulation[] simu;
	private SimulationUser simuu;
	private CircuitObserver cob;
	private double n;
	private String carname;
	private Voiture vuser;
	private KeyboardCustom key;

	public PlayClassic(IHMSwing ihm, String carname, KeyboardCustom key){
		this.ihm = ihm;
		this.carname = carname;
		this.key = key;
		System.out.println("so we begin");
	}

	public void create(String filename, double n, ArrayList<String> listStr){
		this.n = n;
		v = new Voiture[(int) n];
		r = new Radar[(int) n];
		r1 = new Radar[(int) n];
		str = new Strategy[(int) n];
		simu = new Simulation[(int) n];
		tsimu = new ThreadSimu[(int) n];
		System.out.println(filename);
		c = CircuitFactoryFromFile.build(filename);
		dij = new Dijkstra(c);
		dij.algo();
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
		cob = new CircuitObserver(c);
		vuser = VoitureFactory.build(c);
		vuser.reStart(new Vecteur(c.getPointDepart().x, c.getPointDepart().y + 20), c.getDirectionDepart(), 0.0);
		ihm.add(cob);
		try {
			ihm.add(new VoitureObserver(vuser,carname));
		} catch (IOException e) {
			e.printStackTrace();
		}
		simuu = new SimulationUser(vuser, c, key);
		simuu.add(ihm);
		light = new RadarLight(c);
		for(int i=0; i<n; i++){
			v[i] = VoitureFactory.build(c);
			v[i].reStart(new Vecteur(c.getPointDepart().x + 20 - i*20, c.getPointDepart().y), c.getDirectionDepart(), 0.0);
			try {
				ihm.add(new VoitureObserver(v[i],"Lambo.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ihm.add(new TrajectoireObserver(v[i]));
			switch(listStr.get(i)){
			case "StrategyDijkstra":
				r[i] = RadarFactory.build(c, dij);
				r1[i] = RadarFactory.build(c, dij);
				str[i] = StrategyFactory.build(r[i], r1[i], v[i], light, surface);
				ihm.add(new RadarObserver(v[i],r[i]));
				ihm.add(new Radar3Observer(r1[i],light,v[i]));
				ihm.add(new LightObserver(light));
				break;
			case "StrategyClassic":
				r[i] = RadarFactory.build2(c);
				str[i] = StrategyFactory.build2(r[i], v[i], surface);
				ihm.add(new RadarObserver(v[i],r[i]));
				break;
			}
			simu[i] = new Simulation(v[i], c, null, null);
			simu[i].add(ihm);
		}
		ihm.manageUpdate();
		if(tsimuu != null) {
			System.out.println("interrupt simu");
			tsimuu.stop();
		}
		tsimuu = new ThreadUser(simuu);
		tsimuu.start();
	}

	public void play(){
		if(light == null){
			System.out.println("Not charged yet!");
			return;
		}
		for(int i=0; i<3; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 2){
				System.out.println("Go !");
				break;
			}
			System.out.println("Readyyy");
		}
		for(int i=0; i<n; i++){
			if(tsimu[i] != null) {
				System.out.println("interrupt simu");
				tsimu[i].stop();
			}
			simu[i].setC(0);
			tsimu[i] = new ThreadSimu(simu[i]);
			tsimu[i].start(str[i]);
		}
	}

	public void replay(){
		for(int i=0; i<n; i++){
			v[i].reStart(new Vecteur(c.getPointDepart().x + 20 - i*20, c.getPointDepart().y), c.getDirectionDepart(), 0.0);
		}
		play();
	}

	public boolean isFinished(){
		for(int i=0; i<n; i++){
			if(tsimu[i] == null) return false;
			if(!tsimu[i].isFinished()) return false;
		}
		return true;
	}

	public boolean isFailed(){
		for(int i=0; i<n; i++){
			if(tsimu[i] == null) return false;
			if(tsimu[i].isFailed()) return true;
		}
		return false;
	}

	public void resume(){
		for(int i=0; i<n; i++){
			tsimu[i].resume(null);	
		}
	}

	public void suspend(){
		for(int i=0; i<n; i++){
			tsimu[i].suspend();	
		}
	}

	public void updateIHM(IHMSwing ihm){
		this.ihm = ihm;
	}

}
