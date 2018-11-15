package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ui.thread.TheBrain;

public class ButtonExamen implements ActionListener, ButtonCustom {

	private TheBrain t;
	private JComboBox<String> comboStr;
	
	public ButtonExamen(TheBrain t, JComboBox<String> comboStr){
		this.t = t;
		this.comboStr = comboStr;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.examen(comboStr.getSelectedItem().toString());
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
