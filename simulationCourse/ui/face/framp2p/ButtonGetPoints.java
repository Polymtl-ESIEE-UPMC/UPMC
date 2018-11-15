package ui.face.framp2p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.face.runtimemode.ButtonCustom;
import ui.face.runtimemode.ToolDraw;
import ui.thread.TheBrain;

public class ButtonGetPoints implements ActionListener, ButtonCustom {

	private ToolDraw tool;
	private TheBrain t;
	
	public ButtonGetPoints(ToolDraw tool, TheBrain t){
		this.tool = tool;
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.clearPoints();
		System.out.println("Lets rechoose some points");
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
