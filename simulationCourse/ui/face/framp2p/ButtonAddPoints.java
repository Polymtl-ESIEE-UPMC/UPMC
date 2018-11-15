package ui.face.framp2p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.face.runtimemode.ToolDraw;

public class ButtonAddPoints implements ActionListener, ButtonCustom {

	private ToolDraw tool;
	
	public ButtonAddPoints(ToolDraw tool){
		this.tool = tool;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Lets choose some points");
		tool.reStart();
		tool.switchMode(2);
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
