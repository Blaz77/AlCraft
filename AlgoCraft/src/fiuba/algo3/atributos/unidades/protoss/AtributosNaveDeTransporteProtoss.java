package fiuba.algo3.atributos.unidades.protoss;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosNaveDeTransporteProtoss extends AtributosUnidadTransporte {
	
	public AtributosNaveDeTransporteProtoss() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				200,	// costoMineral
				0,		// costoGasVespeno
				8);		// turnosConstruccion
		this.vida = new AtributosVida(
				80,		// vidaMaxima
				60);	// escudoMaximo
		this.nombre = "Nave de transporte";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				4,	//movPorTuno
				7);	//rangoVision
		this.costoAlmacenamiento = 0; //tecnicamente no es almacenable
		
		// fields de UnidadTransporte:
		this.capacidadAlmacenamiento = 8;
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
