package observerswing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import ui.thread.UpdateEventListener;


public class IHMSwing extends JPanel implements UpdateEventListener {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<ObserverSWING> list;
	private Iterator<ObserverSWING> ite;
	private ObserverSWING o;
	
	public IHMSwing(){
		list = new ArrayList<ObserverSWING>();
	}
	
	public void manageUpdate() {
        repaint(); // ordre de mise à jour  
        try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
    public void paint(Graphics g){
        super.paint(g);
        for(ObserverSWING o:list)
            o.print(g);
    }
    
    public void add(ObserverSWING vo){
    	list.add(vo);
    }
    
    public void delete(){
    	ite = list.iterator();
    	while(ite.hasNext()){
    		o = ite.next();
            if(o instanceof RadarObserver || o instanceof Radar3Observer || o instanceof LightObserver){
            	ite.remove();
            }
    	}
    }
    
	public void save() {} // save im
	public void save2() {} // save im

}
