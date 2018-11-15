package ui.face.runtimemode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

import model.terrain.Terrain;

public class ButtonModif implements ActionListener, ButtonCustom {

	private JComboBox<String> comboTer;
	private Terrain ter;
	private ToolDraw tool;
	private JSpinner spin;
	
	public ButtonModif(JComboBox<String> comboTer, ToolDraw tool, JSpinner spin){
		this.comboTer = comboTer;
		this.tool = tool;
		this.spin = spin;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		tool.reStart();
		double rayon = (double) spin.getValue();
		String filename3  = comboTer.getSelectedItem().toString();
        switch (filename3) {
            case "Route":  ter = Terrain.Route;
                     break;
            case "Herbe":  ter = Terrain.Herbe;
                     break;
            case "Eau":  ter = Terrain.Eau;
                     break;
            case "Obstacle":  ter = Terrain.Obstacle;
                     break;
            case "BandeRouge":  ter = Terrain.BandeRouge;
                     break;
            case "BandeBlanche":  ter = Terrain.BandeBlanche;
                     break;
            case "StartPoint":  ter = Terrain.StartPoint;
                     break;
            case "EndLine":  ter = Terrain.EndLine;
                     break;
            case "Boue":  ter = Terrain.Boue;
                     break;
        }
        System.out.println("let's draw it");
        tool.switchMode(1);
        tool.getT(ter, rayon);
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
