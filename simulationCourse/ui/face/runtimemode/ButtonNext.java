package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ButtonNext implements ActionListener, ButtonCustom {

	private JFrame wd;
	private ChooseCars cc;
	
	public ButtonNext(JFrame wd, ChooseCars cc){
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
		Fenetre fenetre = new Fenetre(wd, cc.get());
		fenetre.go();
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
