package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ui.face.runtimemode.ButtonCustom;
import ui.face.classicmode.ChooseCars;

public class ButtonNext1 implements ActionListener, ButtonCustom {

	private JFrame wd;
	private ChooseCars cc;
	
	public ButtonNext1(JFrame wd, ChooseCars cc){
		this.wd = wd;
		this.cc = cc;
	}
	public void actionPerformed(ActionEvent e) {
		if(cc.get() == null){
			System.out.println("Choose your car!");
			return;
		}
		wd.remove(wd.getContentPane());
		wd.getContentPane().removeAll();
		WindowCar cars = new WindowCar(wd, cc.get());
		cars.go();
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
