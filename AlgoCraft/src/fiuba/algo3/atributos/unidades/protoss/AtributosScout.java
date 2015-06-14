package fiuba.algo3.atributos.unidades.protoss;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosScout extends AtributosUnidad {
	
	private AtributosAtaque ataque;
	
	public AtributosScout() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				300,	// costoMineral
				150,	// costoGasVespeno
				9);		// turnosConstruccion
		this.vida = new AtributosVida(
				150,	// vidaMaxima
				100);	// escudoMaximo
		this.nombre = "Scout";

		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				3,		// costoPoblacion
				1,		// movPorTuno
				7);		// rangoVision

		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				14,		// Danio A Aire
				8,		// Danio A Tierra
				4,		// Rango a Aire
				4,		// Rango a Tierra
				1);		// Ataques por Turno
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	
	@Override
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
	}
	
}
