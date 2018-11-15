package model.geometrie;

public class Vecteur {

	public final double x,y;
	
	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vecteur(Vecteur a, Vecteur b){
		this.x = b.x - a.x;
		this.y = b.y - a.y;
	}

	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	public Vecteur add(Vecteur v){
		Vecteur v2 = new Vecteur(x + v.x, y + v.y);
		return v2;
	}
	
	public double prodscal(Vecteur v){
		return this.x*v.x + this.y*v.y;
	}
	
	public double prodvec(Vecteur v){
		return x*v.y - v.x*y;
	}
	
	public Vecteur rotation(double d){
		double a,b;
		a = x*Math.cos(d) - y*Math.sin(d);
		b = x*Math.sin(d) + y*Math.cos(d);
		Vecteur v = new Vecteur(a,b);
		return v;
	}
	
	public Vecteur mult(double a){
		Vecteur v = new Vecteur(x * a, y * a);
		return v;
	}
	
	public Vecteur clone(Vecteur v){
		Vecteur v1 = new Vecteur(x,y);
		return v1;
	}
	
	public Vecteur unitVec(){
		return new Vecteur(this.x/norme(), this.y/norme());
	}
	
	public double norme(){
		return Math.sqrt(x*x + y*y);
	}
	
	public boolean equal(Vecteur v){
		if (this.x == v.x && this.y == v.y)
			return true;
		return false;
	}
	
	public double angle(Vecteur v){
		double d = this.prodscal(v)/(this.norme()*v.norme());
		if(d < -1.0)  d=-1;
		else if (d>1) d = 1;
		 
		return Math.acos(d) * Math.signum(this.prodvec(v));
	}	
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
