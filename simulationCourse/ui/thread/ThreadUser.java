package ui.thread;

import model.mains.SimulationUser;
import model.strategy.Strategy;

public class ThreadUser implements Runnable {

	private SimulationUser simuu;
	private Thread t2;

	public ThreadUser(SimulationUser simuu){
		this.simuu = simuu;
	}
	public void run(){
		simuu.play();
	}
	
	public void start(){
		t2 = new Thread(this);
		t2.start();
	}
	
	public boolean isFinished(){
		return simuu.isFinished();
	}
	
	public boolean isFailed(){
		return simuu.isFailed();
	}

	public void stop(){
		t2.stop();
	}

	public void resume(Strategy str){
		t2.resume();
	}

	public void suspend(){
		t2.suspend();
	}
	
	
}

