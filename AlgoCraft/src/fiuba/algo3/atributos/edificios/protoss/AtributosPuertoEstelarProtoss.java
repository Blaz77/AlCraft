package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.ocupantes.edificios.Edificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoLiberandoConstruccionEdificio;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.Tipo;

public class AtributosPuertoEstelarProtoss extends AtributosEdificio {

	private AtributosEntrenadorUnidades entrenador;
	
	public AtributosPuertoEstelarProtoss(AtributosUnidad... entrenables){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 150, 10);
		this.vida = new AtributosVida(600, 600);
		this.tipo = Tipo.PUERTO_ESTELAR_PROTOSS;
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,				// MaxEntrenamientosSimultaneos
				entrenables); 	// Lista de unidades entrenables
	}
	
	@Override
	public Tipo getEdificioRequerido() {
		return Tipo.ACCESO;
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList(new EstadoRegenerandoEscudo(),
						new EstadoLiberandoConstruccionEdificio(Tipo.ARCHIVOS_TEMPLARIOS));
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}

	@Override
	public IEntrenadorUnidades getEntrenadorUnidades(Edificio portador) {
		return new EntrenadorUnidades(this.entrenador, portador);
	}
}
