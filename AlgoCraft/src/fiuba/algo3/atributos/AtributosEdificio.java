package fiuba.algo3.atributos;

public abstract class AtributosEdificio extends AtributosObjetoVivo {
	
	//Por ahora todos metodos con el comportamiento mas usual.
	//Quizas hacer todo abstracto despues (no sabria bien porque)
	
	public boolean puedeEntrenarUnidades(){
		return false;
	}
	
	public boolean edificableEnTierra(){
		return true;
	}
	
	public boolean edificableEnEspacio(){
		return false;
	}
	
	// quizas sacar este y dejar solo los dos de abajo, pero por ahora no
	public boolean edificableSobreRecurso(){
		return false;
	}
	
	public boolean edificableSobreMineral(){
		return false;
	}
	
	public boolean edificableSobreGasVespeno(){
		return false;
	}

}
