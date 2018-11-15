package ui.face.runtimemode;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ChooseCars {

	private JPanel contentPane;
	private JFrame wd;
	private String carname;
	private Choices choice1, choice2, choice3;
	/**
	 * Launch the application.
	 */
	public ChooseCars(JFrame wd) {
		this.wd = wd;
	}

	/**
	 * Create the frame.
	 * @wbp.parser.entryPoint
	 */
	public void go() {
		contentPane = new ImagePanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		wd.getContentPane().setLayout(new BorderLayout());
		wd.getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		
		JLabel lblChooseYourCar = new JLabel("Choose your car");
		lblChooseYourCar.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourCar.setFont(new Font("Ravie", Font.BOLD, 43));
		lblChooseYourCar.setBounds(204, 80, 619, 57);
		contentPane.add(lblChooseYourCar);
		
		choice1 = new Choice1(this);
		choice2 = new Choice2(this);
		choice3 = new Choice3(this);
		
		ImageIcon ic1 = new ImageIcon("car1.gif");
		ImageIcon ic2 = new ImageIcon("car2.gif");
		ImageIcon ic3 = new ImageIcon("car3.gif");
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(481, 618, 89, 23);
		ButtonCustom bn = new ButtonNext(wd, this);
		btnNext.addActionListener((ActionListener) bn);
		contentPane.add(btnNext);
		
		JButton lblNewLabel = new JButton(ic1);
		lblNewLabel.addActionListener((ActionListener) choice1);
		lblNewLabel.setBounds(32, 266, ic1.getIconWidth(), ic1.getIconHeight());
		contentPane.add(lblNewLabel);
		
		JButton lblNewLabel_1 = new JButton(ic2);
		lblNewLabel_1.addActionListener((ActionListener) choice2);
		lblNewLabel_1.setBounds(401, 266, ic1.getIconWidth(), ic1.getIconHeight());
		contentPane.add(lblNewLabel_1);
		
		JButton lblNewLabel_2 = new JButton(ic3);
		lblNewLabel_2.addActionListener((ActionListener) choice3);
		lblNewLabel_2.setBounds(759, 266, ic1.getIconWidth(), ic1.getIconHeight());
		contentPane.add(lblNewLabel_2);
		wd.revalidate();
		wd.repaint();
		wd.pack();
		wd.setVisible(true);
	}
	
	public void set(String cn){
		carname = cn;
	}
	
	public String get(){
		return carname;
	}
}
