package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class AtributosPuertoEstelar extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosPuertoEstelar(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 10);
		this.vida = new AtributosVida(1300); 
		this.tipo = Tipo.PUERTO_ESTELAR_TERRAN;
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,				// MaxEntrenamientosSimultaneos
				entrenables); 	// Lista de unidades entrenables
	}
	
	@Override
	public IEntrenadorUnidades getEntrenadorUnidades(Edificio portador) {
		return new EntrenadorUnidades(this.entrenador, portador);
	}
	
	@Override
	public Tipo getEdificioRequerido() {
		return Tipo.FABRICA;
	}

	
}
