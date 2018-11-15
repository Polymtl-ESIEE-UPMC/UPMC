package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice1 implements ActionListener, Choices {

	private ChooseCars cc;
	
	public Choice1(ChooseCars cc){
		this.cc = cc;
	}
	
	public void actionPerformed(ActionEvent e) {
		cc.set("car1.jpg");
	}

}
