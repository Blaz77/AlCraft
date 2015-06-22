package fiuba.algo3.excepciones;

public class MineralInsuficiente extends RuntimeException {
	
	public MineralInsuficiente(int costo, int actual){
		super(String.format("Recurso Mineral Insuficiente.\nCosto: %d\nNecesita: %d", 
				costo, costo-actual));
	}

}
