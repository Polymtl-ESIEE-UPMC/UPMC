package ui.face.menustart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.face.classicmode.ChooseCars;
import ui.face.runtimemode.ButtonCustom;

public class ButtonClassic implements ActionListener, ButtonCustom {

	private JFrame wd;
	
	public ButtonClassic(JFrame wd){
		this.wd = wd;
	}
	
	public void actionPerformed(ActionEvent e) {
		wd.remove(wd.getContentPane());
		wd.getContentPane().removeAll();
		ChooseCars cc = new ChooseCars(wd);
		cc.go();
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
