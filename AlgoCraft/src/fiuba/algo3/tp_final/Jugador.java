package fiuba.algo3.tp_final;

//import java.util.ArrayList;

public class Jugador {
	private Color color;
	private Raza raza;
	private int minerales;
	private int gasVespeno;
	private int poblacion;

	final int poblacionMaxima = 200;
	
	public Jugador(TipoRaza raza, Color color) {
		RazaFactory razaFactory = new RazaFactory();
		
		this.color = color;
		this.raza = razaFactory.getRaza(raza);
		
		this.minerales = 200;
		this.gasVespeno = 50;
		this.poblacion = 0;
	}

	public Color getColor() {
		return color;
	}

	public TipoRaza getRaza() {
		return raza.getTipoRaza();
	}
	
	public int getMinerales(){
		return this.minerales;
	}

	public void modificarMinerales(int cantidad) {
		//si cantidad es negativo levantar excepcion? No: con esto la "gastamos"
		this.minerales += cantidad;
	}
	
	public void modificarGasVespeno(int cantidad){
		//si cantidad es negativo levantar excepcion? Idem arriba. si queda negativo el recurso, en cambio...
		this.gasVespeno += cantidad;
	}

	public void modificarPoblacion(int i) { // Aca si es negativo es xq se murio gente.
		this.poblacion += i;		
	}
	
	public int getPoblacion(){
		return this.poblacion;
	}

}
