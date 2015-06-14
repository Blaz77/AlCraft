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

public class AtributosDragon extends AtributosUnidad {

	private AtributosAtaque ataque;
	
	public AtributosDragon(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				125,	// costoMineral
				50,		// costoGasVespeno
				6);		// turnosConstruccion
		this.vida = new AtributosVida(
				100,	// vidaMaxima
				80);	// escudoMaximo
		this.nombre = "Dragon";

		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,		// costoPoblacion
				1,		// movPorTuno
				8);		// rangoVision

		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				20,		// Danio A Aire
				20,		// Danio A Tierra
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
