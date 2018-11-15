package ui.face.framp2p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.thread.TheBrain;

public class ButtonLoadPoints implements ActionListener, ButtonCustom {
	
	private TheBrain t;
	
	public ButtonLoadPoints(TheBrain t){
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Load your points");
		t.loadPoints();
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reStart() {
		// TODO Auto-generated method stub
		
	}
}
