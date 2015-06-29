package fiuba.algo3.excepciones;

public class SuministroInsuficiente extends RuntimeException {

	public SuministroInsuficiente(String nombreIncrementador){
		super(String.format("Suministro Insuficiente.\nNecesita construir un %s", 
				nombreIncrementador));
	}
	
}
