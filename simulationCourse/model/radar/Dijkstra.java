package model.radar;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import model.circuit.Circuit;
import model.geometrie.Vecteur;
import model.geometrie.VecteurComp;
import model.mains.UpdateEventSender;
import model.terrain.Terrain;
import model.terrain.TerrainTools;
import ui.thread.UpdateEventListener;

public class Dijkstra implements UpdateEventSender {

	private UpdateEventListener ecouteur;
	private Queue<Vecteur> queue;
	private double [][] dist1, dist;
	public final Circuit c;
	private VecteurComp compa;
	private boolean finish;
	private Vecteur s, v1, v2, arr;
	private double newdist;
	private int xv, yv;

	public Dijkstra(Circuit c) {
		dist1 = new double[c.getHeight()][c.getWidth()];
		compa = new VecteurComp(dist1);
		queue = new PriorityBlockingQueue<Vecteur>(1000, compa);
		this.c = c;
	}

	public void algo(){
		finish = false;
		for(int j=0; j<c.getWidth();j++){
			for(int i=0; i<c.getHeight();i++){
				if (c.getTerrain(i,j)== Terrain.EndLine){
					dist1[i][j] = 0;
				}
				else{
					dist1[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		queue.clear();
		for(int j=0; j<c.getWidth();j++){
			for(int i=0; i<c.getHeight();i++){
				if(dist1[i][j] == 0) queue.add(new Vecteur(i,j));
			}
		}
		System.out.println(queue.size());
		while (queue.size()!=0){
			s = queue.poll();	
			voisin(s);
		}
		dist = Arrays.copyOf(dist1, dist1.length);
//		dist = dist1.clone();
		if(ecouteur!=null){
			update();
		}
		finish = true;
	}

	public void voisin(Vecteur v){
		for(int i=-1; i<=1; i++){
			for(int j=-1 ; j<=1;j++){
				xv = (int) v.x+i;
				yv = (int) v.y+j;
				v1 = new Vecteur(xv, yv);
				if ((i!=0 || j!=0 ) && 
						TerrainTools.isRunnable(c.getTerrain(xv, yv))==true 
						&& xv < c.getHeight() 
						&& yv < c.getWidth()){ 
					v2 = new Vecteur(v, v1);
					arr = c.getDirectionArrivee();
					if(c.getTerrain ((int)v.x, (int)v.y) == Terrain.EndLine){
						if (arr.prodscal(v2)<0.0){
							if(queue.contains(v1)==false && dist1[xv][yv]==Double.POSITIVE_INFINITY) queue.add(v1);
							newdist = 0.0;
							newdist = dist1[(int)v.x][(int)v.y]+(Math.sqrt(v2.x*v2.x + v2.y*v2.y))*10;
							if(newdist < dist1[(int)xv][(int)yv]) dist1[(int)xv][(int)yv] = newdist;
						}
					}else{
						if(queue.contains(v1)==false && dist1[xv][yv]==Double.POSITIVE_INFINITY) queue.add(v1);
						newdist=0.0;
						newdist = dist1[(int)v.x][(int)v.y]+(Math.sqrt(v2.x*v2.x + v2.y*v2.y))*10;
						if(newdist < dist1[(int)xv][(int)yv]) dist1[(int)xv][(int)yv] = newdist;
					}
				}
			}
		}
	}

	public double[][] getDist(){
		return dist;
	}

	public boolean isFinished(){
		return finish;
	}
	
	public void add(UpdateEventListener listener) {
		ecouteur = listener;
	}

	public void update() {
		ecouteur.manageUpdate();
	}

	public void save() {
		ecouteur.save2();
	}

}

