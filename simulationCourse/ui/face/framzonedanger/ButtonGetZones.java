package ui.face.framzonedanger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.face.runtimemode.ToolDraw;
import ui.thread.TheBrain;

public class ButtonGetZones implements ActionListener, ButtonCustom {

	private ToolDraw tool;
	private TheBrain t;
	
	public ButtonGetZones(ToolDraw tool, TheBrain t){
		this.tool = tool;
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.clearZones();
		System.out.println("Lets rechoose some zones");
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
