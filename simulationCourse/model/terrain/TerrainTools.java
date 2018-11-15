package model.terrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TerrainTools {

	public static Terrain terrainFromChar( char c) throws RuntimeException {
		Terrain[] values = Terrain.values();
		for(int i=0; i<values.length; i++)
			if(c == Terrain.conversion[i])
				return values [i];
	throw new RuntimeException("Terrain inconnu : "+c);
	}
	
	public static char charFromTerrain(Terrain c) throws RuntimeException {
		Terrain[] values = Terrain.values();
		for(int i=0; i<values.length; i++)
			if(c == Terrain.values()[i])
				return Terrain.conversion[i];
	throw new RuntimeException("Terrain inconnu : "+c);
	}
	
	public static Color terrainToRGB(Terrain c) throws RuntimeException {
		Terrain[] values = Terrain.values();
		for(int i=0; i<values.length; i++)
			if(c == Terrain.values()[i])
				return Terrain.convColor[i];
	throw new RuntimeException("Terrain inconnu : "+c);
	}
	
	public static boolean isRunnable(Terrain t) throws RuntimeException {
		if (t == Terrain.Route || t == Terrain.Boue || t == Terrain.BandeRouge ||
				t == Terrain.BandeBlanche || t == Terrain.StartPoint || 
				t == Terrain.EndLine)
			return true;
		return false;
	}
	
	public static BufferedImage imageFromCircuit(Terrain[][] track){
        // Construction d'une image 600x800
        int nColonnes = track[0].length;
        int nLignes = track.length;
        BufferedImage im = // objet image en mémoire
                new BufferedImage(nColonnes, nLignes, BufferedImage.TYPE_INT_ARGB);

        /*// récupération d'un stylo pour écrire dans l'image
        Graphics g = im.getGraphics();

        // tracer une ligne entre le point (10,10) et le point (100, 50)
        g.drawLine(10, 10, 100, 50);*/
        
        for(int i=0; i<nLignes; i++)
        	for(int j=0; j<nColonnes; j++)
        		im.setRGB(j, i, terrainToRGB(track[i][j]).getRGB());
       

        // Sauver l'image dans un fichier
//        try {
//            File outputfile = new File("saved.png");
//            ImageIO.write(im, "png", outputfile);
//         } catch (IOException e) {
//            System.out.println("Erreur lors de la sauvegarde");
//         }
        
        return im;
	}

	public static Terrain[][] readFromFile(String name) {
		 InputStream op;
		 try {
		   op = new FileInputStream(name);
		   InputStreamReader read = new InputStreamReader(op); 
		   BufferedReader buff = new BufferedReader(read); 
		   String line = buff.readLine();
		   int nbc= Integer.parseInt(line);
		   System.out.println(nbc);
		   line = buff.readLine();
		   int nbl= Integer.parseInt(line);
		   System.out.println(nbl);
		   Terrain [][] t =new Terrain [nbl][nbc];
		   for(int i=0;i<nbl;i++){
			   String s = buff.readLine();
			   for(int j=0; j<s.length();j++){
				   t[i][j]= TerrainTools.terrainFromChar(s.charAt(j));
			   }
		   }
		   TerrainTools.imageFromCircuit(t);
		   buff.close(); 
		   return t;
		}catch (Exception e) {
			   e.printStackTrace();
			   System.err.println("Invalid Format :  ... Loading aborted");
			   return null;
		}
	}
	
}

