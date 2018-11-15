package ui.face.classicmode;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	  public ImagePanel() {
	    try {
			img = ImageIO.read(new File("wallpaper.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

	}
