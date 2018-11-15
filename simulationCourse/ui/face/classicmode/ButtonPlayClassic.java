package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.thread.PlayClassic;

public class ButtonPlayClassic implements ActionListener, ButtonCustom {

	private PlayClassic t;
	private boolean threadSuspend;
	private boolean threadRun;

	public ButtonPlayClassic(PlayClassic t){
		this.t = t;
		threadSuspend = false;
		threadRun = false;
	}

	public void actionPerformed(ActionEvent e){
	
		if(t.isFailed()){
			System.out.println("replay");
			t.replay();
			threadRun = true;
		}
		else if(t.isFinished()){
			System.out.println("new lap");
			t.play();
			threadRun = true;
		}
		else if(!threadRun){
			System.out.println("play");
			t.play();
			threadRun = true;
		}
		else if(threadSuspend){
			System.out.println("resumme");
			t.resume();
			threadSuspend = false;
		}
		else{
			System.out.println("pause");
			t.suspend();
			threadSuspend = true;
		}
	}

	public void save(){}

	public void reStart(){
		threadRun = false;
		threadSuspend = false;
	}

}
