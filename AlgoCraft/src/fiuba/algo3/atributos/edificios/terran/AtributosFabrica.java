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

public class AtributosFabrica extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosFabrica(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(200, 100, 12);
		this.vida = new AtributosVida(1250); 
		this.tipo = Tipo.FABRICA;
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,		//MaxEntrenamientosSimultaneos
				Arrays.asList(entrenables)); //lista de unidades entrenables);
	}
	
	@Override
	public IEntrenadorUnidades getEntrenadorUnidades() {
		return new EntrenadorUnidades(this.entrenador);
	}
	
	@Override
	public Tipo getEdificioRequerido() {
		return Tipo.BARRACA;
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoLiberandoConstruccionEdificio(Tipo.PUERTO_ESTELAR_TERRAN));
	}

}
