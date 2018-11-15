package ui.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import model.circuit.Circuit;
import model.circuit.CircuitFactoryFromFile;
import model.filtre.Filtre;
import model.filtre.FiltreGenerique;
import model.geometrie.PastPosition;
import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;
import model.mains.Simulation;
import model.radar.Dijkstra;
import model.radar.Radar;
import model.radar.RadarFactory;
import model.radar.RadarLight;
import model.radar.RadarSurf;
import model.strategy.Commande;
import model.strategy.Strategy;
import model.strategy.StrategyClassic;
import model.strategy.StrategyFactory;
import model.terrain.Terrain;
import model.terrain.TerrainTools;
import model.voiture.Voiture;
import model.voiture.VoitureFactory;
import observer3D.CircuitObserveur3D;
import observer3D.IHM3Dlight;
import observer3D.VoitureObserveur3D;
import observerswing.CircuitObserver;
import observerswing.IHMSwing;
import observerswing.LightObserver;
import observerswing.P2PObserver;
import observerswing.Radar3Observer;
import observerswing.RadarObserver;
import observerswing.SurfObserver;
import observerswing.TrajectoireObserver;
import observerswing.VoitureObserver;
import observerswing.ZoneDangerObserver;
import observeur.Controleur;
import observeur.DijkstraObserveur;
import observeur.VoitureObserveur;

public class ThreadCreate implements Runnable {

	private IHMSwing ihm;
	private IHM3Dlight ihm2;
	private Simulation simu;
	private Thread t1;
	private ThreadSimu t2;
	private ThreadDijkstra t3;
	private String filename, filename2, filename3, carname;
	private Dijkstra dij;
	private Radar r, r2, r3, r5;
	private Voiture v;
	private RadarLight light;
	private RadarSurf surface;
	private boolean ok, deco, threadSuspend, threadRun,  go, p2p, zd, backit;
	private Strategy str, s, sp2p, spresent, sbefore, szd;
	private Circuit c;
	private Controleur co;
	private CircuitObserver cob;
	private Terrain[][] mat;
	private Vecteur pfan, test, p;
	private ArrayList<Vecteur> listpoint, listpoint2;
	private ArrayList<ZoneDanger> listzone, listzone2;
	private LinkedList<Commande> record;
	private LinkedList<PastPosition> bit;
	private PastPosition pp;

	public ThreadCreate(IHMSwing ihm, IHM3Dlight ihm2, String carname){
		this.ihm = ihm;
		this.ihm2 = ihm2;
		this.carname = carname;
		ok = false;
		deco = false;
		threadSuspend = false;
		threadRun = false;
		go = false;
		p2p = false;
		zd = false;
		backit = false;
	}

	public void run() {
		System.out.println(filename);
		record = new LinkedList<Commande>();
		bit = new LinkedList<PastPosition>();
		listpoint = new ArrayList<Vecteur>();
		listpoint2 = new ArrayList<Vecteur>();
		listzone = new ArrayList<ZoneDanger>();
		listzone2 = new ArrayList<ZoneDanger>();
		c = CircuitFactoryFromFile.build(filename);
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
		v = VoitureFactory.build(c);
		dij = new Dijkstra(c);

		Controleur co2 = new Controleur(c);
		co2.add(new DijkstraObserveur(dij));
		dij.add(co2);

		if(t3 != null) {
			System.out.println("interrupt dij");
			t3.stop();
		}
		t3 = new ThreadDijkstra(dij);
		t3.start();

		r2 = RadarFactory.build2(c);
		r = RadarFactory.build(c,dij);
		r3 = RadarFactory.build(c,dij);
		r5 = RadarFactory.build(c,dij);
		light = new RadarLight(c);
		surface = new RadarSurf(c,r5);
		simu = new Simulation(v,c,record, bit);

		co = new Controleur(c);
		co.add(new VoitureObserveur(v));
		simu.add(co);

		cob = new CircuitObserver(c);
		ihm.add(cob);

		try {
			ihm.add(new VoitureObserver(v,carname));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ihm.add(new TrajectoireObserver(v));
//		ihm.add(new SurfObserver(surface));
		ihm.add(new P2PObserver(listpoint));
		ihm.add(new ZoneDangerObserver(listzone, c));
//		ihm2.add(new CircuitObserveur3D(c));	//3D
//		ihm2.add(new VoitureObserveur3D(v));	//3D
		simu.add(ihm);
		ihm.manageUpdate();
		record.clear();
	}

	public void start(String filename){
		this.filename = filename;
		t1 = new Thread(this);
		t1.start();
	}

	public void play(String filename2){
		ihm.delete();
		test();
		if(ok){
			threadRun = true;
			if(go){
				dij.algo();
				go = false;
			}
			this.filename2 = filename2;
			System.out.println(filename2);
			if(t2 != null) {
				System.out.println("interrupt simu");
				t2.stop();
			}
			simu.setC(0);
			t2 = new ThreadSimu(simu);

			switch(filename2){
			case "StrategyDijkstra":
				str = StrategyFactory.build(r, r3, v, light, surface);
//				ihm.add(new RadarObserver(v,r));
//				ihm.add(new Radar3Observer(r3,light,v));
//				ihm.add(new LightObserver(light));
				break;
			case "StrategyClassic":
				str = StrategyFactory.build2(r2, v, surface);
				ihm.add(new RadarObserver(v,r2));
				break;
			}
			spresent = str;
			if(p2p){
				sp2p = StrategyFactory.build4(spresent, v, c, listpoint);
				sbefore = spresent;
				spresent = sp2p;
			}
			if(zd){
				szd = StrategyFactory.build5(spresent, v, listzone);
				System.out.println("haha");
				sbefore = spresent;
				spresent = szd;
			}
			if(deco){
				System.out.println(filename3);
				switch(filename3){
				case "DecoHao":
					s = StrategyFactory.build3(spresent, v, surface);
					t2.start(s);
					break;
				case "Deco 0.4":
					s = StrategyFactory.build6(spresent, v);
					t2.start(s);
					break;
				case "Deco 0.2":
					s = StrategyFactory.build7(spresent, v);
					t2.start(s);
					break;
				}
			}else t2.start(spresent);
		}
	}

	public void replay(String filename2){
		if(!backit) {
			v.reStart(c.getPointDepart(), c.getDirectionDepart(), 0.0);
			record.clear();
		}
		backit = false;
		play(filename2);
	}
	
	public void backinTime(){
		ihm.delete();
		backit = true;
		if(bit.size() != 0) pp = bit.getLast();
		v.reStart(pp.p, pp.d, pp.vitesse);
		bit.removeLast();
		int size = record.size();
		for(int i = size-1; i > size - 102; i--){
			record.remove(i);
		}
		simu.setC(simu.getC() - 100);
	}

	public boolean isFinished(){
		if(t2 == null) return false;
		else return t2.isFinished();
	}

	public boolean isFailed(){
		if(t2 == null) return false;
		else return t2.isFailed();
	}

	public void stop(){
		if(t2 != null) {
			System.out.println("interrupt simu");
			t2.stop();
		}
		System.out.println("interrupt circuit");
		t1.stop();
	}

	public boolean isCharged(){
		return ok;
	}

	public void resume(String filename2){
		System.out.println(filename2);
		if(go){
			dij.algo();
			go = false;
		}
		threadSuspend = false;

		switch(filename2){
		case "StrategyDijkstra":
			str = StrategyFactory.build(r, r3, v, light, surface);
			ihm.add(new RadarObserver(v,r));
			ihm.add(new Radar3Observer(r3,light,v));
			ihm.add(new LightObserver(light));
			break;
		case "StrategyClassic":
			str = StrategyFactory.build2(r2, v, surface);
			ihm.add(new RadarObserver(v,r2));
			break;
		}
		spresent = str;
		if(p2p){
			sp2p = StrategyFactory.build4(spresent, v, c, listpoint);
			sbefore = spresent;
			spresent = sp2p;
		}
		if(zd){
			szd = StrategyFactory.build5(spresent, v, listzone);
			sbefore = spresent;
			spresent = szd;
		}
		if(deco){
			System.out.println(filename3);
			switch(filename3){
			case "DecoHao":
				s = StrategyFactory.build3(spresent, v, surface);
				t2.resume(s);
				break;
			case "Deco 0.4":
				s = StrategyFactory.build6(spresent, v);
				t2.resume(s);
				break;
			case "Deco 0.2":
				s = StrategyFactory.build7(spresent, v);
				t2.resume(s);
				break;
			}
		}else t2.resume(spresent);
	}

	public void suspend(){
		threadSuspend = true;
		t2.suspend();
		ihm.delete();
		ihm.manageUpdate();
	}

	public void save(){
		test();
		if(ok && t2 == null){
			System.out.println("save Dijkstra");
			dij.save();
		}else if(ok){
			if(threadSuspend || t2.isFinished() || t2.isFailed()){
				System.out.println("save all");
				dij.save();
				t2.save();
			}else{
				System.out.println("pause and save");
				suspend();
				dij.save();
				t2.save();
				resume(filename2);
			}
		}
	}

	public void decor(String filename3){
		this.filename3 = filename3;
		s = null;
		if(t2 == null || isFinished() || isFailed() || threadSuspend){
			if(!deco){
				System.out.println("pre-activate decor strategy");
				deco = true;
			}else{
				System.out.println("deactive decor strategy");
				deco = false;
			}
		}else{
			if(!deco){
				deco = true;
				System.out.println("decor strategy");
				System.out.println(filename3);
				switch(filename3){
				case "DecoHao":
					s = StrategyFactory.build3(spresent, v, surface);
					t2.decor(s);
					break;
				case "Deco 0.4":
					s = StrategyFactory.build6(spresent, v);
					t2.decor(s);
					break;
				case "Deco 0.2":
					s = StrategyFactory.build7(spresent, v);
					t2.decor(s);
					break;
				}
			}else{
				deco = false;
				System.out.println("undecor strategy");
				t2.decor(spresent);
			}
		}
	}

	public void changeCircuit(Vecteur centre, Terrain ter, double rayon){
		test();
		if(ok){
			co.go = true;
			cob.go = true;
			pfan = centre;
			mat = c.getMatrix();
			mat[(int) pfan.x][(int)pfan.y] = ter;
			for(int i=0; i<mat.length; i++)
				for(int j=0; j<mat[0].length; j++){
					if(new Vecteur(pfan, new Vecteur(i, j)).norme()<=rayon){
						mat[i][j] = ter;
					}
				}
			co.manageUpdate();
			ihm.manageUpdate();
			if(threadRun && !isFinished() && !isFailed() && !threadSuspend){
				if(spresent instanceof StrategyClassic){
					if(t3 != null) {
						System.out.println("interrupt dij");
						t3.stop();
					}
					t3 = new ThreadDijkstra(dij);
					t3.start();
				}else{
					go = true;
					suspend();
					resume(filename2);
				}
			}else{
				go = true;
			}
		}
	}

	public void activeP2P(){
		if(t2 == null || isFinished() || isFailed() || threadSuspend){
			if(!p2p){
				System.out.println("pre-active P2P");
				p2p = true;
			}else{
				System.out.println("deactive P2P");
				p2p = false;
			}
		}else{
			if(!p2p){
				p2p = true;
				System.out.println("active P2P");
				sp2p = StrategyFactory.build4(spresent, v, c, listpoint);
				sbefore = spresent;
				spresent = sp2p;
				if(deco){
					deco = false;
					decor(filename3);
				}else t2.decor(spresent);
			}else{
				p2p = false;
				System.out.println("deactive P2P");
				spresent = sbefore;
				if(deco){
					deco = false;
					decor(filename3);
				}else t2.decor(spresent);
			}
		}
	}

	public void addPoints(Vecteur point){
		if(TerrainTools.isRunnable(c.getTerrain(point))){
			listpoint.add(point);
		}
		ihm.manageUpdate();
	}

	public void clearPoints(){
		listpoint.clear();
		ihm.manageUpdate();
	}

	public void savePoints(){
		listpoint2.clear();
		for(Vecteur p:listpoint){
			listpoint2.add(p);
		}
		ihm.manageUpdate();
	}

	public void loadPoints(){
		listpoint.clear();
		for(Vecteur p:listpoint2){
			listpoint.add(p);
		}
		ihm.manageUpdate();
	}

	public boolean isP2P(){
		return p2p;
	}

	public void test(){
		System.out.println("test");
		if(dij.getDist() == null){
			System.out.println("still in process charging the course");
		}else{
			ok = true;
		}
	}

	public void activeZD(){
		if(t2 == null || isFinished() || isFailed() || threadSuspend){
			if(!zd){
				System.out.println("pre-active ZoneDanger");
				zd = true;
			}else{
				System.out.println("deactive ZoneDanger");
				zd = false;
			}
		}else{
			if(!zd){
				zd = true;
				System.out.println("active ZoneDanger");
				szd = StrategyFactory.build5(spresent, v, listzone);
				sbefore = spresent;
				spresent = sp2p;
				if(deco){
					deco = false;
					decor(filename3);
				}else t2.decor(spresent);
			}else{
				zd = false;
				System.out.println("deactive ZoneDanger");
				spresent = sbefore;
				if(deco){
					deco = false;
					decor(filename3);
				}else t2.decor(spresent);
			}
		}
	}

	public void addZones(ZoneDanger zone){
		p = zone.pdanger;
		if(!TerrainTools.isRunnable(c.getTerrain(p))){
			for(int i=0; i<c.getHeight(); i++){
				for(int j=0; j<c.getWidth(); j++){
					test = new Vecteur(i, j);
					if(new Vecteur(test, p).norme()<zone.rayon && TerrainTools.isRunnable(c.getTerrain(test))){
						listzone.add(zone);
						break;
					}
				}
			}
		}else{
			listzone.add(zone);
		}
		ihm.manageUpdate();
	}

	public void clearZones(){
		listzone.clear();
		ihm.manageUpdate();
	}

	public void saveZones(){
		listzone2.clear();
		for(ZoneDanger zo:listzone){
			listzone2.add(zo);
		}
		ihm.manageUpdate();
	}

	public void loadZones(){
		listzone.clear();
		for(ZoneDanger zo:listzone2){
			listzone.add(zo);
		}
		ihm.manageUpdate();
	}

	public boolean isZD(){
		return zd;
	}
	
	public void examen(String filename10){
		record.clear();
		test();
		simu.remove(ihm);
		if(ok){
			System.out.println(ok);
			threadRun = true;
			if(go){
				dij.algo();
				go = false;
			}
			System.out.println(filename10);
			switch(filename10){
			case "StrategyDijkstra":
				str = StrategyFactory.build(r, r3, v, light, surface);
				break;
			case "StrategyClassic":
				str = StrategyFactory.build2(r2, v, surface);
				break;
			}
			spresent = str;
			if(p2p){
				sp2p = StrategyFactory.build4(spresent, v, c, listpoint);
				sbefore = spresent;
				spresent = sp2p;
			}
			if(zd){
				szd = StrategyFactory.build5(spresent, v, listzone);
				System.out.println("haha");
				sbefore = spresent;
				spresent = szd;
			}
			if(deco){
				System.out.println(filename3);
				switch(filename3){
				case "DecoHao":
					s = StrategyFactory.build3(spresent, v, surface);
					simu.getChange(s);
					break;
				case "Deco 0.4":
					s = StrategyFactory.build6(spresent, v);
					simu.getChange(s);;
					break;
				case "Deco 0.2":
					s = StrategyFactory.build7(spresent, v);
					simu.getChange(s);
					break;
				}
			}else simu.getChange(spresent);
			simu.play();
			simu.save();
			dij.save();
			System.out.println("finish");
		}
	}

}