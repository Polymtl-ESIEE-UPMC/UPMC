package ui.face.framzonedanger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.thread.TheBrain;

public class ButtonLoadZones implements ActionListener, ButtonCustom {
	
	private TheBrain t;
	
	public ButtonLoadZones(TheBrain t){
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Load your zones");
		t.loadZones();
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
