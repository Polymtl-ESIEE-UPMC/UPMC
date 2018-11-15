package ui.face.menustart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.face.runtimemode.ButtonCustom;
import ui.face.runtimemode.ChooseCars;

public class ButtonRuntime implements ActionListener, ButtonCustom {
	
	private JFrame wd;
	
	public ButtonRuntime(JFrame wd){
		this.wd = wd;
	}
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		wd.remove(wd.getContentPane());
		wd.getContentPane().removeAll();
		ChooseCars cc = new ChooseCars(wd);
		cc.go();
	}

}
