package model.strategy;

import java.util.LinkedList;

public class StrategyListeCommande implements Strategy {
	
	private LinkedList<Commande> list;
	private int index;
	
	public StrategyListeCommande(LinkedList<Commande> list){
		this.list = list;
		index = 0;
	}

	public Commande getCommande(){
		if(index < list.size()){
			index++;
		}
		else{
			index = 0;
			getCommande();
		}
		return list.get(index--);	
	}
	
}
