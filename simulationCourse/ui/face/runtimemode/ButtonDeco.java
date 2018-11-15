package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ui.thread.TheBrain;

public class ButtonDeco implements ActionListener, ButtonCustom {
	private TheBrain t;
	private ListButton list;
	private String filename3;
	private JComboBox<String> comboDeco;

	public ButtonDeco(TheBrain t, ListButton list, JComboBox<String> comboDeco){
		this.t = t;
		this.list = list;
		this.comboDeco = comboDeco;
	}

	public void actionPerformed(ActionEvent e) {
		list.reStart(null);
		filename3 = comboDeco.getSelectedItem().toString();
		System.out.println("decor");
		t.decor(filename3);
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
