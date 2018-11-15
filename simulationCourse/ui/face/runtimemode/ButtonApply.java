package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import ui.thread.TheBrain;

public class ButtonApply implements ActionListener, ButtonCustom {
	
	private TheBrain t;
	private JComboBox<String> box;
	private String filename;
	private Fenetre f;
	private ListButton list;

	public ButtonApply(TheBrain t, JComboBox<String> box, Fenetre f, ListButton list){
		this.t = t;
		this.box = box;
		this.f = f;
		this.list = list;
	}

	public void actionPerformed(ActionEvent e) {
		list.reStart(this);
		System.out.println("charge the course");
		f.update();
		filename = box.getSelectedItem().toString();
		t.create(filename);
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
