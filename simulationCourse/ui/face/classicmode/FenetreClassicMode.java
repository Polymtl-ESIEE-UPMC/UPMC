package ui.face.classicmode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import observerswing.IHMSwing;
import ui.face.runtimemode.ButtonCustom;
import ui.thread.PlayClassic;

public class FenetreClassicMode {
	private JFrame wd;
	private IHMSwing ihm;
	private PlayClassic t;
	private JComboBox<String> comboCourse;
	private JButton bouton1, bouton2;
	private ButtonCustom bc1, bc2;
	private JPanel pan2, pan4, pan6;
	private String carname;

	public FenetreClassicMode(JFrame wd, String carname){
		this.wd = wd;
		this.carname = carname;
	}

	public void go(double n, ArrayList<String> listStr){
		ihm = new IHMSwing();
		ihm.setPreferredSize(new Dimension(1024, 768));
		
		KeyboardCustom key = new KeyboardCustom();
		t = new PlayClassic(ihm, carname, key);

		String[] tab = {"1_safe.trk", "2_safe.trk", "3_safe.trk", "4_safe.trk", "5_safe.trk", "6_safe.trk"
				, "7_safe.trk", "8_safe.trk","aufeu.trk","bond_safe.trk","Examarrivees.trk","jussieu.trk","Een2.trk","labymod.trk",
				"branly.trk","ExampointApoint.trk","labyperso.trk","perso.trk","t2009.trk","t260_safe.trk"};

		comboCourse = new JComboBox<String>(tab);

		bouton2 = new JButton("Play/Pause");
		bc2 = new ButtonPlayClassic(t);
		bouton2.addActionListener((ActionListener) bc2);
		bouton1 = new JButton("Apply");
		bc1 = new ButtonApplyClassic(t, comboCourse, this, bc2, n, listStr);
		bouton1.addActionListener((ActionListener) bc1);

		pan2 = new JPanel();
		pan2.setLayout(new GridLayout(8,1));
		pan4 = new JPanel();
		pan4.setLayout(new GridLayout(3,1));
		pan6 = new JPanel();
		pan6.setLayout(new GridLayout(2,2));

		pan2.add(pan4);
		pan2.add(pan6);
		pan4.add(comboCourse);
		pan6.add(bouton1);
		pan6.add(bouton2);


		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		wd.setLayout(new BorderLayout());
		//		fenetre.add(pan, BorderLayout.PAGE_START);
		wd.add(pan2, BorderLayout.LINE_END);
		wd.add(ihm, BorderLayout.CENTER);
		wd.revalidate();
		wd.repaint();
		wd.pack();
		wd.setVisible(true); 
	}
	
	public void update(){
		wd.remove(ihm);
		ihm = new IHMSwing();
		ihm.setPreferredSize(new Dimension(1024, 768));
		t.updateIHM(ihm);
		wd.add(ihm, BorderLayout.CENTER);
		wd.pack();
		wd.setVisible(true);
	}
}
