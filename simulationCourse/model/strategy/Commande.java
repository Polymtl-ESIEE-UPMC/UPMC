package model.strategy;

public class Commande {
	
	private double acc, turn; 

	public Commande(double acc,double rot){
			this.acc = acc;
			this.turn = rot;
	}
	
	public double getAcc(){
		return acc;
	}
	
	public double getTurn(){
		return turn;
	}
	
}

