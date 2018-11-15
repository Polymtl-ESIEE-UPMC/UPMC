package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.thread.TheBrain;

public class ButtonBIT implements ActionListener, ButtonCustom {

	private TheBrain t;
	
	public ButtonBIT(TheBrain t){
		this.t = t;
	}
	public void actionPerformed(ActionEvent arg0) {
		t.backinTime();
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
