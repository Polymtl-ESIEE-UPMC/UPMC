package model.mains;

import ui.thread.UpdateEventListener;

public interface UpdateEventSender {

	public void add(UpdateEventListener listener);
	public void update();
	public void save();

}