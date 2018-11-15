package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ui.thread.TheBrain;

public class ButtonPlay implements ActionListener, ButtonCustom {

	private TheBrain t;
	private boolean threadSuspend;
	private boolean threadRun;
	private JComboBox<String> comboStr;
	private String filename2;
	private ListButton list;

	public ButtonPlay(TheBrain t, JComboBox<String> comboStr, ListButton list){
		this.t = t;
		this.comboStr = comboStr;
		this.list = list;
		threadSuspend = false;
		threadRun = false;
	}

	public void actionPerformed(ActionEvent e){
		list.reStart(null);
		filename2 = comboStr.getSelectedItem().toString();
		
		if(t.isFailed()){
			System.out.println("replay");
			t.replay(filename2);
			threadRun = true;
		}
		else if(t.isFinished()){
			System.out.println("new lap");
			t.play(filename2);
			threadRun = true;
		}
		else if(!threadRun){
			System.out.println("play");
			t.play(filename2);
			if(t.charging()) threadRun = true;
		}
		else if(threadSuspend){
			System.out.println("resumme");
			t.resume(filename2);
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
