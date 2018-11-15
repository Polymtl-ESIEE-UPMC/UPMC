package ui.face.menustart;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ui.face.runtimemode.ButtonCustom;


public class Windowmenu {

	private JFrame wd;
	private JPanel panel;
	private JButton bouton1, bouton2, bouton3;
	private ImageIcon img, img1, img2, img3;
	private ButtonCustom bc1, bc2;

	public void go(String name){
		wd = new JFrame(name);

		img = new ImageIcon("background1.gif");

		JFXPanel fxpanel = new JFXPanel();
		wd.setContentPane(new JLabel(img));
		
//		wd.add(fxpanel);
//
//		final File f = new File("videoplayback.mp4");
//		final Media m = new Media(f.toUri().toString());
//		final MediaPlayer mp = new MediaPlayer(m);
//		mp.setAutoPlay(true);
//		final MediaView mv = new MediaView(mp);
//		Group root = new Group();
//		Scene scene = new Scene(root, 540, 210);
//		((Group)scene.getRoot()).getChildren().add(mv);
//		fxpanel.setScene(scene);

		panel = new JPanel();
		panel.setBounds(177, 648, 670, 120);
		panel.setLayout(null);
		panel.setBackground(Color.RED);

		img1 = new ImageIcon("b1.jpg");
		bouton1 = new JButton();
		bouton1.setLayout(null);
		bouton1.setIcon(img1);
		bouton1.setBounds(10, 10, 210, 100);
		bouton1.setToolTipText("Runtime mode");
		bc1 = new ButtonRuntime(wd);
		bouton1.addActionListener((ActionListener) bc1);
		bouton1.setBorderPainted(false);

		img2 = new ImageIcon("b2.jpg");
		bouton2 = new JButton();
		bouton2.setLayout(null);
		bouton2.setIcon(img2);
		bouton2.setBounds(230, 10, 210, 100);
		bouton2.setToolTipText("Classic mode");
		bc2 = new ButtonClassic(wd);
		bouton2.addActionListener((ActionListener) bc2);
		bouton2.setBorderPainted(false);

		img3 = new ImageIcon("b3.jpg");
		bouton3 = new JButton();
		bouton3.setLayout(null);
		bouton3.setIcon(img3);
		bouton3.setBounds(450, 10, 210, 100);
		bouton3.setToolTipText("Settings");
		bouton3.setBorderPainted(false);

		panel.add(bouton1);
		panel.add(bouton2);
		panel.add(bouton3);

		wd.add(panel);
		wd.pack();
		wd.setVisible(true);
		wd.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
