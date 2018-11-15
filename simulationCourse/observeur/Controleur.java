package observeur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.circuit.Circuit;
import model.terrain.TerrainTools;
import ui.thread.UpdateEventListener;

public class Controleur implements UpdateEventListener {
	
	private ArrayList<ObserveurImage> list;
	private BufferedImage im;
	public boolean go; 
	private Circuit c;

	public Controleur(Circuit c){
		this.c = c;
		im = TerrainTools.imageFromCircuit(c.getMatrix());
		list = new ArrayList<ObserveurImage>();
	}

	public void manageUpdate() {
		if(go){
			im = TerrainTools.imageFromCircuit(c.getMatrix());
			go = false;
		}
		for(ObserveurImage l:list)
			l.print(im);
	}
	
	public void add(ObserveurImage vo){
		list.add(vo);
	}

	public void save(){ // save test
		try {
			File outputfile = new File("test.png");
			ImageIO.write(im, "png", outputfile);
		}catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde");
		}	
	}

	public void save2(){ // save dijkstra
		try {
			File outputfile = new File("dijk.png");
			ImageIO.write(im, "png", outputfile);
		}catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde");
		}
	}

}
