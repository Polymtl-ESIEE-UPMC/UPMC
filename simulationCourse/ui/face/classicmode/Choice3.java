package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice3 implements ActionListener, Choices {

	private ChooseCars cc;
	
	public Choice3(ChooseCars cc){
		this.cc = cc;
	}
	
	public void actionPerformed(ActionEvent e) {
		cc.set("car3.jpg");
	}

}
