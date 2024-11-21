package Buscaminas;

public class Casilla {
	
	
	private boolean tieneMina;
	private boolean estaMarcada;
	private boolean estaOculta;
	private int numMinasCercanas;
	private boolean banderaCorrecta;
	private boolean descubresMina;
	
	public Casilla () {
		estaMarcada=false;	
		estaOculta=true;	
		numMinasCercanas=0;	
		tieneMina=false;
		banderaCorrecta=true;
		descubresMina = false;
	}
	
	public void setBanderaIncorrecta() {
		banderaCorrecta = false;
	}
	
	public boolean dameBandera() {
		return banderaCorrecta;
	}
	
	public void setDescubresMina() {
		descubresMina = true;
	}
	
	public boolean dameDescubresMina() {
		return descubresMina;
	}
	
	public boolean dimeSiTieneMina() {	
		return tieneMina;	
	}
	
	public int dameNumMinasCerca() {	
		return numMinasCercanas;	
	}
	
	public boolean dimeSiEstaMarcada() {	
		return estaMarcada;	
	}
	
	public boolean dimeSiEstaOculta() {	
		return estaOculta;	
	}
	
	public void aumentaMinas() {	
		numMinasCercanas++;	
	}
	
	public void setNumMinasCercanas(int n) {	
		numMinasCercanas=n;	
	}
	
	public void mostrarCasilla() {
		estaOculta=false;
	}
	
	public void marcarCasilla() {
		estaMarcada=true;
	}
	
	public void setTieneMina() {
		tieneMina = true;
	}
	
}
