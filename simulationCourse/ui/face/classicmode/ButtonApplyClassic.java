package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import ui.face.runtimemode.ButtonCustom;
import ui.thread.PlayClassic;

public class ButtonApplyClassic implements ActionListener, ButtonCustom {
	
	private JComboBox<String> box;
	private String filename;
	private FenetreClassicMode f;
	private ButtonCustom b;
	private PlayClassic t;
	private double n;
	private ArrayList<String> listStr;
	
	public ButtonApplyClassic(PlayClassic t, JComboBox<String> box, FenetreClassicMode f, ButtonCustom b,
			double n, ArrayList<String> listStr){
		this.t = t;
		this.box = box;
		this.f = f;
		this.b = b;
		this.n = n;
		this.listStr = listStr;
	}

	public void actionPerformed(ActionEvent e) {
		b.reStart();
		System.out.println("charge the course");
		f.update();
		filename = box.getSelectedItem().toString();
		t.create(filename, n, listStr);
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
