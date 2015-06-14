package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.IEntrenadorUnidades;

public class AtributosFabrica extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosFabrica(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(200, 100, 12);
		this.vida = new AtributosVida(1250); 
		this.nombre = "Fabrica";
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,		//MaxEntrenamientosSimultaneos
				Arrays.asList(entrenables)); //lista de unidades entrenables);
	}
	
	@Override
	public IEntrenadorUnidades getEntrenadorUnidades() {
		return new EntrenadorUnidades(this.entrenador);
	}

}
