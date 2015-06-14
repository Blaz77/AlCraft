package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosEntrenadorUnidades;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.EntrenadorUnidades;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IEntrenadorUnidades;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaLivianaTerrestre;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaTerrestre;

public class AtributosAcceso extends AtributosEdificio {
	
	private AtributosEntrenadorUnidades entrenador;

	public AtributosAcceso(AtributosUnidad... entrenables) {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 0, 8);
		this.vida = new AtributosVida(500, 500);
		this.nombre = "Acceso";
		
		// fields EdificioEntrenadorUnidades:
		this.entrenador = new AtributosEntrenadorUnidades(
				1,		//MaxEntrenamientosSimultaneos
				Arrays.asList(entrenables)); //lista de unidades entrenables
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(0, 0, this.vida);
	}
	
	@Override
	public IEntrenadorUnidades getEntrenadorUnidades() {
		return new EntrenadorUnidades(this.entrenador);
	}
	
}
