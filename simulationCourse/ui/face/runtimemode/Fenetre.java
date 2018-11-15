package ui.face.runtimemode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.lwjgl.LWJGLException;

import observer3D.IHM3Dlight;
import observerswing.IHMSwing;
import ui.thread.TheBrain;

public class Fenetre {
	
	private JFrame wd;
	private IHMSwing ihm;
	private IHM3Dlight ihm2;
	private TheBrain t;
	private JComboBox<String> comboCourse, comboStr, comboTer, comboDeco;
	private JSpinner spin;
	private ListButton list;
	private JButton bouton1, bouton2, bouton3, bouton4, bouton5, bouton6, bouton11, bouton12, bouton13;
	private ButtonCustom bc1, bc2, bc3, bc4, bc5, bc6, tool, bc11, bc12, bc13;
	private JPanel pan2, pan4, pan6, pan8, pan10;
	private String carname;

	public Fenetre(JFrame wd, String carname){
		this.wd = wd;
		this.carname = carname;
	}
	
	public void go(){
		ihm = new IHMSwing();
		ihm.setPreferredSize(new Dimension(1024, 768));
		wd.setPreferredSize(new Dimension(1270, 868));

		t = new TheBrain(ihm, ihm2, carname);

		String[] tab = {"1_safe.trk", "2_safe.trk", "3_safe.trk", "4_safe.trk", "5_safe.trk", "6_safe.trk"
				, "7_safe.trk", "8_safe.trk","aufeu.trk","bond_safe.trk","Examarrivees.trk","jussieu.trk","Een2.trk","labymod.trk",
				"branly.trk","ExampointApoint.trk","labyperso.trk","perso.trk","t2009.trk","t260_safe.trk", "longbeachfwy.trk", "losangeles.trk",
				"newyork.trk", "schtroumpf.trk"};
		String[] tab2 = {"StrategyDijkstra", "StrategyClassic"};
		String[] tab3 = {"Route", "Herbe", "Eau", "Obstacle", "BandeRouge", "BandeBlanche", 
				"StartPoint", "EndLine", "Boue"};
		String[] tab4 = {"DecoHao","Deco 0.4", "Deco 0.2"};

		comboCourse = new JComboBox<String>(tab);
		comboCourse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboCourse.setAlignmentY(SwingConstants.CENTER);
		comboStr = new JComboBox<String>(tab2);
		comboStr.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboTer = new JComboBox<String>(tab3);
		comboTer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboDeco = new JComboBox<String>(tab4);
		comboDeco.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		tool = new ToolDraw(t);
		ihm.addMouseListener((MouseListener) tool);
		
		spin = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999.0, 1.0));

		list = new ListButton();
		
		bouton2 = new JButton("Play/Pause");
		bouton2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bouton2.setBackground(Color.CYAN);
		bc2 = new ButtonPlay(t, comboStr, list);
		bouton2.addActionListener((ActionListener) bc2);
		bouton1 = new JButton("Apply");
		bouton1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bouton1.setBackground(Color.CYAN);
		bc1 = new ButtonApply(t, comboCourse, this, list);
		bouton1.addActionListener((ActionListener) bc1);
		bouton3 = new JButton("Save");
		bouton3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bouton3.setBackground(Color.CYAN);
		bc3 = new ButtonSave(t, list);
		bouton3.addActionListener((ActionListener) bc3);
		bouton4 = new JButton("Decor");
		bouton4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bouton4.setBackground(Color.CYAN);
		bc4 = new ButtonDeco(t, list, comboDeco);
		bouton4.addActionListener((ActionListener) bc4);
		bouton5 = new JButton("Valide");
		bouton5.setBackground(new Color(183, 110, 121));
		bouton5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bc5 = new ButtonModif(comboTer, (ToolDraw) tool, spin);
		bouton5.addActionListener((ActionListener) bc5);
		bouton6 = new JButton("P2P");
		bouton6.setBackground(new Color(183, 110, 121));
		bouton6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bc6 = new ButtonP2P((ToolDraw) tool, t);
		bouton6.addActionListener((ActionListener) bc6);
		bouton11 = new JButton("ZoneDanger");
		bouton11.setBackground(new Color(183, 110, 121));
		bouton11.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bc11 = new ButtonZoneDanger((ToolDraw) tool, t);
		bouton11.addActionListener((ActionListener) bc11);
		
		bouton12 = new JButton("Examen");
		bouton12.setBackground(new Color(114, 47, 55));
		bouton12.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bc12 = new ButtonExamen(t, comboStr);
		bouton12.addActionListener((ActionListener) bc12);
		
		bouton13 = new JButton("Back in time");
		bouton13.setBackground(new Color(183, 110, 121));
		bouton13.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bc13 = new ButtonBIT(t);
		bouton13.addActionListener((ActionListener) bc13);
		
		
		list.add(bc1);
		list.add(bc2);
		list.add(bc3);
		list.add(bc4);
		list.add(bc5);
		list.add(tool);
		list.add(bc6);
		

//		JPanel pan = new JPanel();
//		pan.setLayout(new BorderLayout());
//		JPanel pan3 = new JPanel();
//		pan3.setLayout(new BorderLayout());
		pan2 = new JPanel();
		pan2.setLayout(new GridLayout(8,1));
		pan4 = new JPanel();
		pan4.setLayout(new GridLayout(3,1));
		pan6 = new JPanel();
		pan6.setLayout(new GridLayout(2,2));
		pan8 = new JPanel();
		pan8.setLayout(new GridLayout(3,1));
		pan10 = new JPanel();
		pan10.setLayout(new GridLayout(1,2));
		
//		pan.add(pan3, BorderLayout.LINE_END);
		pan2.add(pan4);
		pan2.add(pan6);
		pan2.add(pan8);
		pan2.add(pan10);
		pan4.add(comboCourse);
		pan4.add(comboStr);
		pan4.add(comboDeco);
		pan6.add(bouton1);
		pan6.add(bouton3);
		pan6.add(bouton2);
		pan6.add(bouton4);
		pan8.add(comboTer);
		pan8.add(spin);
		pan8.add(bouton5);
		pan10.add(bouton6);
		pan10.add(bouton11);
		pan2.add(bouton13);
		pan2.add(bouton12);

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
//		try {
//			ihm2 = new IHM3Dlight();
//		} catch (LWJGLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ihm2.setPreferredSize(new Dimension(1024, 768));
		ihm.setPreferredSize(new Dimension(1024, 768));
		ihm.addMouseListener((MouseListener) tool);
		t.updateIHM(ihm, ihm2);
		wd.add(ihm, BorderLayout.CENTER);
		wd.pack();
		wd.setVisible(true);
	}
	
}
