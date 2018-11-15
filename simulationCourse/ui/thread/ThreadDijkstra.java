package ui.thread;

import model.radar.Dijkstra;

public class ThreadDijkstra implements Runnable {
	
	private Dijkstra dij;
	private Thread t3;

	public ThreadDijkstra(Dijkstra dij){
		this.dij = dij;
	}
	public void run(){
		dij.algo();
	}

	public void start(){
		t3 = new Thread(this);
		t3.start();
	}
	
	public void stop(){
		t3.stop();
	}
}
