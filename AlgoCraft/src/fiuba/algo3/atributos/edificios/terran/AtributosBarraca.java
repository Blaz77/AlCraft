package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoLiberandoConstruccionEdificio;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class AtributosBarraca extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosBarraca(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 0, 12);
		this.vida = new AtributosVida(1000);
		this.tipo = Tipo.BARRACA;
		
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
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoLiberandoConstruccionEdificio(Tipo.FABRICA));
	}
}
