package ui.face.classicmode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import ui.face.runtimemode.ButtonCustom;

public class ButtonNext2 implements ActionListener, ButtonCustom {
	private JSpinner spinner;
	private JComboBox<String> box, box1, box2, box3, box4, box5;
	private JFrame wd;
	private ArrayList<String> listStr;
	private String carname;
	
	public ButtonNext2(JSpinner spinner, JComboBox<String> box, JComboBox<String> box1, JComboBox<String> box2, JComboBox<String> box3,
			JComboBox<String> box4, JComboBox<String> box5, JFrame wd, String carname){
		this.spinner = spinner;
		this.box = box;
		this.box1 = box1;
		this.box2 = box2;
		this.box3 = box3;
		this.box4 = box4;
		this.box5 = box5;
		this.wd = wd;
		this.carname = carname;
		listStr = new ArrayList<String>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wd.remove(wd.getContentPane());
		wd.getContentPane().removeAll();
		double n = (double) spinner.getValue();
		if(n == 1.){
			listStr.add(box.getSelectedItem().toString());
		}else if(n == 2.){
			listStr.add(box1.getSelectedItem().toString());
			listStr.add(box2.getSelectedItem().toString());
		}else if(n == 3.){
			listStr.add(box3.getSelectedItem().toString());
			listStr.add(box4.getSelectedItem().toString());
			listStr.add(box5.getSelectedItem().toString());
		}else{
			System.out.println("Number of players not supported yet");
		}
		FenetreClassicMode fcm = new FenetreClassicMode(wd, carname);
		fcm.go(n, listStr);
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
