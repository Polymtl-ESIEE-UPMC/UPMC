package ui.face.runtimemode;

import java.util.ArrayList;

public class ListButton {
	private ArrayList<ButtonCustom> list;
	private boolean ok;
	
	public ListButton(){
		list = new ArrayList<ButtonCustom>();
		ok = false;
	}
	
	public void add(ButtonCustom b){
		list.add(b);
	}
	
	public void reStart(ButtonCustom b1){
		if(b1 != null) ok = true;
		for(ButtonCustom b: list){
			if(ok){
				if(!(b.equals(b1))) b.reStart();
			}else if(b instanceof ToolDraw) b.reStart();
		}
		ok = false;
	}
}
