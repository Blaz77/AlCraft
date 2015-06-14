package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.IEntrenadorUnidades;

public class AtributosPuertoEstelar extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosPuertoEstelar(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 10);
		this.vida = new AtributosVida(1300); 
		this.nombre = "Puerto Estelar";
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,		//MaxEntrenamientosSimultaneos
				Arrays.asList(entrenables)); //lista de unidades entrenables);
		
		// fields EdificioEntrenadorUnidades:
		//this.entrenador = new AtributosEntrenadorUnidades(1);
		//this.entrenador.agregarConstructor(new ConstructorInfanteriaPesadaAerea());
		//this.entrenador.agregarConstructor(new ConstructorInfanteriaMagica());
		//this.entrenador.agregarConstructor(new ConstructorTransporte());
	}
	
	@Override
	public IEntrenadorUnidades getEntrenadorUnidades() {
		return new EntrenadorUnidades(this.entrenador);
	}

	
}
