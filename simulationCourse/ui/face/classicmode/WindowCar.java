package ui.face.classicmode;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.face.runtimemode.ButtonCustom;

import javax.swing.JLabel;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.CardLayout;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class WindowCar implements ChangeListener {

	private JPanel contentPane, panel_1;
	private JFrame wd;
	private JSpinner spinner;
	private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_3, comboBox_4, comboBox_5;
	private String carname;

	public WindowCar(JFrame wd, String carname) {
		this.wd = wd;
		this.carname = carname;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void go(){
		String[] tab = {"StrategyDijkstra", "StrategyClassic"};
//		String[] tab1 = {"DecoHao","Deco 0.69", "Deco 0.1"};

		wd.getContentPane().setLayout(new BorderLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		wd.getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new Image2Panel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNumberBots = new JLabel("Number bots");
		panel.add(lblNumberBots);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JPanel panel_2 = new ImagePanel();
		panel_1.add(panel_2, "1.0");
		panel_2.setLayout(null);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBounds(468, 120, 87, 14);
		panel_2.add(lblPlayer);
		
		comboBox = new JComboBox(tab);
		comboBox.setBounds(447, 139, 129, 20);
		panel_2.add(comboBox);
		
		JPanel panel_3 = new ImagePanel();
		panel_1.add(panel_3, "2.0");
		panel_3.setLayout(null);
		
		JLabel lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setBounds(306, 120, 87, 14);
		panel_3.add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setBounds(616, 120, 87, 14);
		panel_3.add(lblPlayer_2);
		
		comboBox_1 = new JComboBox(tab);
		comboBox_1.setBounds(289, 139, 129, 20);
		panel_3.add(comboBox_1);
		
		comboBox_2 = new JComboBox(tab);
		comboBox_2.setBounds(598, 139, 129, 20);
		panel_3.add(comboBox_2);
		
		JPanel panel_4 = new ImagePanel();
		panel_1.add(panel_4, "3.0");
		panel_4.setLayout(null);
		
		comboBox_3 = new JComboBox(tab);
		comboBox_3.setBounds(433, 134, 129, 20);
		panel_4.add(comboBox_3);
		
		comboBox_4 = new JComboBox(tab);
		comboBox_4.setBounds(672, 134, 129, 20);
		panel_4.add(comboBox_4);
		
		JLabel lblPlayer_3 = new JLabel("Player 1");
		lblPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_3.setBounds(196, 112, 87, 14);
		panel_4.add(lblPlayer_3);
		
		JLabel lblPlayer_4 = new JLabel("Player 2");
		lblPlayer_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_4.setBounds(447, 112, 87, 14);
		panel_4.add(lblPlayer_4);
		
		JLabel lblPlayer_5 = new JLabel("Player 3");
		lblPlayer_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_5.setBounds(689, 112, 87, 14);
		panel_4.add(lblPlayer_5);
		
		comboBox_5 = new JComboBox(tab);
		comboBox_5.setBounds(184, 134, 129, 20);
		panel_4.add(comboBox_5);
		
		spinner = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 3.0, 1.0));
		spinner.addChangeListener(this);
		panel.add(spinner);
		
		ButtonCustom bcnext = new ButtonNext2(spinner, comboBox, comboBox_1, comboBox_2, comboBox_3,
				comboBox_4, comboBox_5, wd, carname);
		
//		ImageIcon ic1 = new ImageIcon("car1.gif");
//		ImageIcon ic2 = new ImageIcon("car2.gif");
//		ImageIcon ic3 = new ImageIcon("car3.gif");
//		
//		JLabel lblNewLabel = new JLabel(ic1);
//		lblNewLabel.setBounds(30, 286, ic1.getIconWidth(), ic1.getIconHeight());
//		panel_2.add(lblNewLabel);
//		
//		JLabel lblNewLabel_1 = new JLabel(ic2);
//		lblNewLabel_1.setBounds(389, 286, ic1.getIconWidth(), ic1.getIconHeight());
//		panel_2.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_2 = new JLabel(ic3);
//		lblNewLabel_2.setBounds(752, 286, ic1.getIconWidth(), ic1.getIconHeight());
//		panel_2.add(lblNewLabel_2);
		
		
		JButton btnNext = new JButton("Next");
		panel.add(btnNext);

		btnNext.addActionListener((ActionListener) bcnext);
		
		wd.revalidate();
		wd.repaint();
		wd.pack();
		wd.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		CardLayout c1 = (CardLayout) panel_1.getLayout();
		c1.show(panel_1, spinner.getValue().toString());
	}
}
