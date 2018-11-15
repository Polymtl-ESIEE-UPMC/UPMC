package ui.thread;

import model.mains.Simulation;
import model.strategy.Strategy;

public class ThreadSimu implements Runnable {

	private Simulation simu;
	private Thread t2;

	public ThreadSimu(Simulation simu){
		this.simu = simu;
	}
	public void run(){
		simu.play();
	}

	public void start(Strategy str){
		simu.getChange(str);
		t2 = new Thread(this);
		t2.start();
	}
	
	public boolean isFinished(){
		return simu.isFinished();
	}
	
	public boolean isFailed(){
		return simu.isFailed();
	}

	public void stop(){
		t2.stop();
	}

	public void resume(Strategy str){
		if(str != null) simu.getChange(str);
		t2.resume();
	}

	public void suspend(){
		t2.suspend();
	}

	public void save(){
		simu.save();
	}
	
	public void decor(Strategy s){
		simu.getChange(s);
	}
	
}
