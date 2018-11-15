package ui.thread;

import model.geometrie.Vecteur;
import model.geometrie.ZoneDanger;
import model.terrain.Terrain;
import observer3D.IHM3Dlight;
import observerswing.IHMSwing;

public class TheBrain {

	private IHMSwing ihm;
	private IHM3Dlight ihm2;
	private ThreadCreate t1;
	private String carname;

	public TheBrain(IHMSwing ihm, IHM3Dlight ihm2, String carname){
		this.ihm = ihm;
		this.ihm2 = ihm2;
		this.carname = carname;
		t1 = null;
		System.out.println("so we begin");
	}

	public void create(String filename){
		if(t1 != null) t1.stop();
		t1 = new ThreadCreate(ihm, ihm2, carname);
		t1.start(filename);
	}

	public void play(String filename2){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.play(filename2);
	}
	
	public void replay(String filename2){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.replay(filename2);
	}

	public boolean isFinished(){
		if(t1 == null) return false;
		return t1.isFinished();
	}
	
	public boolean isFailed(){
		if(t1 == null) return false;
		return t1.isFailed();
	}

	public boolean charging(){
		if(t1 == null) return false;
		else return t1.isCharged();
	}

	public void resume(String filename2){
		t1.resume(filename2);
	}

	public void suspend(){
		t1.suspend();
	}

	public void save(){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.save();
	}

	public void updateIHM(IHMSwing ihm, IHM3Dlight ihm2){
		this.ihm = ihm;
		this.ihm2 = ihm2;
	}

	public void decor(String filename3){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.decor(filename3);
	}
	
	public void changeCircuit(Vecteur centre, Terrain ter, double rayon){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.changeCircuit(centre, ter, rayon);
	}
	
	public void addPoints(Vecteur point){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.addPoints(point);
	}
	
	public void clearPoints(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.clearPoints();
	}
	
	public void savePoints(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.savePoints();
	}
	
	public void loadPoints(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.loadPoints();
	}
	
	public void activeP2P(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.activeP2P();
	}
	
	public boolean isP2P(){
		if(t1 == null) return false;
		else return t1.isP2P();
	}

	public void addZones(ZoneDanger zd){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.addZones(zd);
	}
	
	public void clearZones(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.clearZones();
	}
	
	public void saveZones(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.saveZones();
	}
	
	public void loadZones(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.loadZones();
	}
	
	public void activeZD(){
		if(t1 == null)
			System.out.println("the course is not charged");
		else t1.activeZD();
	}
	
	public boolean isZD(){
		if(t1 == null) return false;
		else return t1.isZD();
	}
	
	public void examen(String filename10){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.examen(filename10);
	}
	
	public void backinTime(){
		if(t1 == null) 
			System.out.println("the course is not charged");
		else t1.backinTime();
	}
	
}
