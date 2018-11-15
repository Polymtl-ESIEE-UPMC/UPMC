package ui.face.runtimemode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ui.face.framzonedanger.ButtonAddZones;
import ui.face.framzonedanger.ButtonGetZones;
import ui.face.framzonedanger.ButtonLoadZones;
import ui.face.framzonedanger.ButtonSaveZones;
import ui.face.framzonedanger.ZDWindows;
import ui.thread.TheBrain;

public class ButtonZoneDanger implements ActionListener, ButtonCustom {
	private ToolDraw tool;
	private TheBrain t;
	private JFrame fenetre3;
	private JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	private JPanel panel, panel2, panel3, panel4;
	private JButton bouton12, bouton13, bouton14, bouton15;
	private ButtonCustom bc12, bc13, bc14, bc15;
	private ZDWindows zdw;
	private JSpinner spin1, spin2;

	public ButtonZoneDanger(ToolDraw tool, TheBrain t){
		this.tool = tool;
		this.t =t ;
		zdw = new ZDWindows();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		t.activeZD();
		
		if(t.isZD() && zdw.isClosed()){
			
			spin1 = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0, 1.0));
			spin2 = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 0.9, 0.05));
			
			tool.setSpin(spin1, spin2);
			
			bouton12 = new JButton("Get Zones");
			bc12 = new ButtonGetZones((ToolDraw) tool, t);
			bouton12.addActionListener((ActionListener) bc12);
			bouton13 = new JButton("Add Zones");
			bc13 = new ButtonAddZones((ToolDraw) tool);
			bouton13.addActionListener((ActionListener) bc13);
			bouton14 = new JButton("Save Zones");
			bc14 = new ButtonSaveZones(t);
			bouton14.addActionListener((ActionListener) bc14);
			bouton15 = new JButton("Load Zones");
			bc15 = new ButtonLoadZones(t);
			bouton15.addActionListener((ActionListener) bc15);
			
			fenetre3 = new JFrame("Strategy ZoneDanger");
			fenetre3.setPreferredSize(new Dimension(500,200));
			fenetre3.setLayout(new BorderLayout());
			fenetre3.addWindowListener(zdw);

			panel = new JPanel();
			panel.setLayout(null);
			panel2 = new JPanel();
			panel2.setLayout(new GridLayout(2,4));

			label1 = new JLabel();
			label1.setText("<html><h1>Instruction Strategy Zone Danger</h1></html>");
			label1.setBounds(10, 0, 500, 50);
			label2 = new JLabel();
			label2.setText("This is an option to drive you car");
			label2.setBounds(10, 40, 500, 50);
			label3 = new JLabel();
			label3.setText("From now, you can pick some zones on the map for that your car go slowly");
			label3.setBounds(10, 60, 500, 50);
			label4 = new JLabel();
			label4.setText("Remind that any action out of this window will stop you to choose zones");
			label4.setBounds(10, 80, 500, 50);
			label5 = new JLabel();
			label5.setText("The option Get Zones will erase all your points, so, save it if you wanna get back!");
			label5.setBounds(10, 100, 500, 50);
			label6 = new JLabel();
			label6.setText(" ");
			label6.setBounds(40, 100, 500, 50);
			
			label7 = new JLabel();
			label7.setText("Rayon");
			label7.setBounds(40, -13, 500, 50);
			label8 = new JLabel();
			label8.setText("Vitesse max");
			label8.setBounds(30, -13, 500, 50);
			panel3 = new JPanel();
			panel3.setLayout(null);
			panel4 = new JPanel();
			panel4.setLayout(null);
			panel3.add(label7);
			panel4.add(label8);
			
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
			panel.add(label6);
			panel2.add(bouton12);
			panel2.add(bouton13);
			panel2.add(bouton14);
			panel2.add(bouton15);
			panel2.add(panel3);
			panel2.add(spin1);
			panel2.add(panel4);
			panel2.add(spin2);
			fenetre3.add(panel, BorderLayout.CENTER);
			fenetre3.add(panel2, BorderLayout.PAGE_END);
			fenetre3.setVisible(true);
			fenetre3.pack();
		}
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
