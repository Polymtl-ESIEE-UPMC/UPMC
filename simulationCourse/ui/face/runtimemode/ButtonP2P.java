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

import ui.face.framp2p.ButtonAddPoints;
import ui.face.framp2p.ButtonGetPoints;
import ui.face.framp2p.ButtonLoadPoints;
import ui.face.framp2p.ButtonSavePoints;
import ui.face.framp2p.P2PWindows;
import ui.thread.TheBrain;


public class ButtonP2P implements ActionListener, ButtonCustom {

	private ToolDraw tool;
	private TheBrain t;
	private JFrame fenetre2;
	private JLabel label1, label2, label3, label4, label5, label6;
	private JPanel panel, panel2;
	private JButton bouton7, bouton8, bouton9, bouton10;
	private ButtonCustom bc7, bc8, bc9, bc10;
	private P2PWindows p2pw;

	public ButtonP2P(ToolDraw tool, TheBrain t){
		this.tool = tool;
		this.t =t ;
		p2pw = new P2PWindows();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		t.activeP2P();
		
		if(t.isP2P() && p2pw.isClosed()){
			
			bouton7 = new JButton("Get Points");
			bc7 = new ButtonGetPoints((ToolDraw) tool, t);
			bouton7.addActionListener((ActionListener) bc7);
			bouton8 = new JButton("Add Points");
			bc8 = new ButtonAddPoints((ToolDraw) tool);
			bouton8.addActionListener((ActionListener) bc8);
			bouton9 = new JButton("Save Points");
			bc9 = new ButtonSavePoints(t);
			bouton9.addActionListener((ActionListener) bc9);
			bouton10 = new JButton("Load Points");
			bc10 = new ButtonLoadPoints(t);
			bouton10.addActionListener((ActionListener) bc10);

			
			
			fenetre2 = new JFrame("Strategy P2P");
			fenetre2.setPreferredSize(new Dimension(500,200));
			fenetre2.setLayout(new BorderLayout());
			fenetre2.addWindowListener(p2pw);

			panel = new JPanel();
			panel.setLayout(null);
			panel2 = new JPanel();
			panel2.setLayout(new GridLayout(1,4));

			label1 = new JLabel();
			label1.setText("<html><h1>Instruction Strategy point to point</h1></html>");
			label1.setBounds(10, 0, 500, 50);
			label2 = new JLabel();
			label2.setText("This is an option to drive you car");
			label2.setBounds(10, 40, 500, 50);
			label3 = new JLabel();
			label3.setText("From now, you can pick some points on the map for that your car will follow");
			label3.setBounds(10, 60, 500, 50);
			label4 = new JLabel();
			label4.setText("Remind that any action out of this window will stop you to choose points");
			label4.setBounds(10, 80, 500, 50);
			label5 = new JLabel();
			label5.setText("The option Get Points will erase all your points, so, save it if you wanna get back!");
			label5.setBounds(10, 100, 500, 50);
			label6 = new JLabel();
			label6.setText(" ");
			label6.setBounds(40, 100, 500, 50);
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(label5);
			panel.add(label6);
			panel2.add(bouton7);
			panel2.add(bouton8);
			panel2.add(bouton9);
			panel2.add(bouton10);
			fenetre2.add(panel, BorderLayout.CENTER);
			fenetre2.add(panel2, BorderLayout.PAGE_END);
			fenetre2.setVisible(true);
			fenetre2.pack();
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
