package model.voiture;

import model.geometrie.Vecteur;
import model.strategy.Commande;

public class VoitureImpl implements Voiture {
	// outils pour la gestion des limites de rotation en fonction de la vitesse
	public final double[] tabVitesse;    
	public final double[] tabTurn;   

	// capacit�s	
	public final double vmax, braquage;
	public final double alpha_c, alpha_f, beta_f;

	// �tat � l'instant t
	private double vitesse;
	private Vecteur position, direction;
	
	//private Strategy stra;
	
	public VoitureImpl(double vmax,double braquage,
			double alpha_c,double alpha_f,double beta_f,
			double vitesse,Vecteur position,Vecteur direction, /*Strategy s,*/ 
			double[] tabVitesse, double[] tabTurn ){
		this.vmax = vmax;
		this.braquage = braquage;
		this.alpha_c = alpha_c;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
		this.vitesse = vitesse;
		this.position = position;
		this.direction = direction;
		
		// DEVELOPPE
		this.tabVitesse = tabVitesse;
		this.tabTurn = tabTurn;
	}
	
	// pour le pilotage
	
	public void drive(Commande c){
		// VERIFICATIONS !!!
		double acc1 = c.getAcc();
		double rot1 = c.getTurn();
      
		/* 1) Est ce que la rotation et l'acc�l�ration sont entre -1 et 1, sinon, 
		throw new RuntimeException*/
        if(acc1<-1.0 || acc1>1.0 || rot1>1.0 || rot1<-1.0) throw new RuntimeException("erreur"); 
          
		/* 2) Est ce que la rotation demand�e est compatible avec la vitesse actuelle,
        sinon, throw new RuntimeException*/

        if( rot1 > getMaxTurn() || rot1 < -getMaxTurn() ) throw new RuntimeException("erreur rot") ;

		// approche normale
		// 1.1) gestion du volant
		direction = direction.rotation(rot1 * braquage); // modif de direction
		/* Note: on remarque bien que l'on tourne d'un pourcentage des capacit�s de 
		la voiture*/

		// 1.2) garanties, bornes...
		direction = direction.unitVec(); /* renormalisation pour �viter les 
		approximations*/

		// 2.1) gestion des frottements

		vitesse -= alpha_f;
		vitesse -= beta_f*vitesse;
		

		// 2.2) gestion de l'acceleration/freinage

		vitesse += acc1 * alpha_c;

		vitesse = Math.max(0.0, vitesse); /* pas de vitesse n�gative 
		(= pas de marche arriere)*/
		vitesse = Math.min(vmax, vitesse); // pas de d�passement des capacit�s

		// 3) mise � jour de la position
		position = position.add(direction.mult(vitesse));
		
		
	}
	public double getMaxTurn(){
		double rotmax = 0.0;
		for(int i=0; i<tabVitesse.length; i++){
			if( vitesse <= tabVitesse[i]*vmax ) {
				rotmax = tabTurn[i];
				break;
			}
		}
		return rotmax;
	}

	public double getVitesse(){
		return vitesse;
	}
	public Vecteur getPosition(){
		return position;
	}
	public Vecteur getDirection(){
		return direction;
	}
	public double getBraquage(){
		return braquage;
	}

	public double getC(){
		return alpha_c;
	}

	 public double getVitesseMustHave(double alpha){
        double vmusthave = 0.0;
		for(int i=0; i<tabTurn.length; i++){
			if(alpha == tabTurn[i]*braquage || i==tabTurn.length-1) {
				vmusthave = tabVitesse[i]*vmax;
				break;
			}
			else if(alpha > tabTurn[i]*braquage) {
				if(i==0){
					vmusthave = tabVitesse[0]*vmax;
					break;
				}else{
					vmusthave = tabVitesse[i-1]*vmax;
					break;
				}
			}
        }
      	return vmusthave;
    	}

	 public void reStart(Vecteur p, Vecteur d, double speed){
		 position = p;
		 direction = d;
		 vitesse = speed;
	 }
}