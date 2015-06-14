package fiuba.algo3.ocupantes.unidades.constructores;

import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.IEntrenadorUnidades;

public class Constructor {
	
	private AtributosUnidad atributos;
	private IEntrenadorUnidades entrenador;
	
	public Constructor(IEntrenadorUnidades entrenador, AtributosUnidad atributos) {
		this.atributos = atributos;
		this.entrenador = entrenador;
	}
	
	public boolean puedeCrear() { // puedeComprar / verificarCostos
		return this.entrenador.puedeEntrenar(this.atributos);
	}
	
	public void crear(){
		this.entrenador.entrenar(this.atributos);
	}

}
