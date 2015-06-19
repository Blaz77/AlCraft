package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.Transporte;

public class AtributosNaveDeTransporte extends AtributosUnidad {
	
	private AtributosTransporte transporte;
	
	public AtributosNaveDeTransporte() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				100,	// costoMineral
				100,	// costoGasVespeno
				7,		// turnosConstruccion
				2);		// costoPoblacion
		
		this.vida = new AtributosVida(150);
		this.nombre = "Nave de transporte";
		
		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				4,		// movPorTuno
				8,		// rangoVision
				0);		// costoAlmacenamiento
		
		// fields de UnidadTransporte:
		this.transporte = new AtributosTransporte(8); //capac.Almac.
	}
	
	public ITransporte getTransporte() {
		return new Transporte(this.transporte);
	}
}
