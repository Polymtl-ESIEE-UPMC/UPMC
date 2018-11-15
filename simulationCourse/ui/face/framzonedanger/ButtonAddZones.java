package ui.face.framzonedanger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.face.runtimemode.ToolDraw;

public class ButtonAddZones implements ActionListener, ButtonCustom {

	private ToolDraw tool;
	
	public ButtonAddZones(ToolDraw tool){
		this.tool = tool;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Lets choose some zones");
		tool.reStart();
		tool.switchMode(3);
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
