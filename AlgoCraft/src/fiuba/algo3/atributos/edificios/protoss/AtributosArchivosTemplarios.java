package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.edificios.Edificio;

public class AtributosArchivosTemplarios extends AtributosEdificio {
	
	private AtributosEntrenadorUnidades entrenador;

	public AtributosArchivosTemplarios(AtributosUnidad... entrenables) {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 200, 9);
		this.vida = new AtributosVida(500, 500);
		this.tipo = Tipo.ARCHIVOS_TEMPLARIOS;
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,				// MaxEntrenamientosSimultaneos
				entrenables); 	// Lista de unidades entrenables
	}
	
	@Override
	public Tipo getEdificioRequerido() {
		return Tipo.PUERTO_ESTELAR_PROTOSS;
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoRegenerandoEscudo());
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
