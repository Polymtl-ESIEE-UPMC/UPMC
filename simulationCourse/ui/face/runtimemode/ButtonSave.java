package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.thread.TheBrain;

public class ButtonSave implements ActionListener, ButtonCustom {
	
	private TheBrain t;
	private ListButton list;

	public ButtonSave(TheBrain t, ListButton list){
		this.t = t;
		this.list = list;
	}

	public void actionPerformed(ActionEvent e) {
		list.reStart(null);
		System.out.println("save");
		t.save();
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
