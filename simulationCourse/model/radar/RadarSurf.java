package model.radar;

import java.util.LinkedList;
import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.terrain.TerrainTools;
import model.voiture.Voiture;

public class RadarSurf {

	private Circuit c;
	private Vecteur direction, position;
	private double[] thetas;
	private LinkedList<Vecteur>list;
	private double surf, needi, needthe;
	private int nbob;
	private Radar r;

	public RadarSurf(Circuit c, Radar r){
		this.c = c;
		this.r = r;
		position = c.getPointDepart().add(c.getDirectionDepart().mult(0.9));
		thetas = r.thetas();
	}

	public void score(Voiture v, double angle){
		list = new LinkedList<Vecteur>();
		int pobef = 0;
		nbob = 0;
		surf = 0;
		double a = 0;
		list.clear();
		direction = v.getDirection();
		position= v.getPosition();
		direction =  direction.rotation(angle);
		direction =  direction.unitVec();

		int cpt1 = -1;
		while(TerrainTools.isRunnable(c.getTerrain(position.add(direction).mult(1)))==true
				&& position.x < c.getHeight() 
				&& position.y < c.getWidth()){
			position = position.add( direction.mult(1));
			cpt1++;
			if(cpt1 == 70) break;
		}
//		if(cpt1<70) position = position.add(direction.mult(-cpt1/1.3));
		
		needi = r.getBestIndex(position, direction);
		needthe = r.thetas()[(int) needi];
		direction =  direction.rotation(needthe);
		direction =  direction.unitVec();

		int cpt2 = -1;
		while(TerrainTools.isRunnable(c.getTerrain(position.add(direction).mult(1)))==true
				&& position.x < c.getHeight() 
				&& position.y < c.getWidth()){
			position = position.add( direction.mult(1));
			cpt2++;
			if(cpt2 == 70) break;
		}
		if(cpt2<70) position = position.add(direction.mult(-cpt2/1.3));
		
		thetas = new double[601];
		for(int i = 0; i<thetas.length; i++){
			thetas[i] = (Math.PI)-(i*((Math.PI)/((thetas.length/2))));
		}
		
		for(int i=0;i<thetas.length;i++){
			Vecteur pfan = position;
			Vecteur d = direction;
			d = d.rotation(thetas[i]);
			d = d.unitVec();
			int cpt3 = -1;
			while( TerrainTools.isRunnable(c.getTerrain(pfan))==true  
					&& pfan.x <= c.getHeight() 
					&& pfan.y <= c.getWidth() ){
				if(cpt3 == 60) break;
				cpt3++;
				pfan = pfan.add(d.mult(1));
			}
			synchronized(list){
				list.add(pfan);
			}
			if(i == 0) a = cpt3;
			if(a==60){
				if((cpt3<60 && pobef == 60) || (cpt3<60 && nbob == 1 && Math.abs(thetas[i])<Math.PI/36) ) nbob++;
			}
			else{
				if((cpt3 == 60 && pobef<60) || (cpt3==60 && nbob == 1 && Math.abs(thetas[i])<Math.PI/36) ) nbob++;
			}
			pobef = cpt3;
			surf += cpt3;
		}
		if(surf/thetas.length<60 && nbob == 0) nbob = 4;
	}

	public Vecteur getPo(){
		return position;
	}

	public LinkedList<Vecteur> getList(){
		return list;
	}

	public double getSurf(){
		return surf/thetas.length;
	}

	public int getNbob(){
		return nbob;
	}

}
