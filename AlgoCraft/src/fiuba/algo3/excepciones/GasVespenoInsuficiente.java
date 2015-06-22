package fiuba.algo3.excepciones;

public class GasVespenoInsuficiente extends RuntimeException {

	public GasVespenoInsuficiente(int costo, int actual){
		super(String.format("Recurso Gas Vespeno Insuficiente.\nCosto: %d\nNecesita: %d", 
			costo, costo-actual));
	}
}
