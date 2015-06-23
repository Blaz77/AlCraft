package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.Transporte;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class AtributosNaveDeTransporte extends AtributosUnidad {
	
	private AtributosTransporte transporte;
	
	public AtributosNaveDeTransporte() {
		// fields ObjetoVivo:
		this.tipo = Tipo.NAVE_DE_TRANSPORTE_TERRAN;
		
		this.costo = new AtributosCosto(
				100,	// costoMineral
				100,	// costoGasVespeno
				7,		// turnosConstruccion
				2);		// costoPoblacion
		
		this.vida = new AtributosVida(150);
		
		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				4,		// movPorTuno
				8,		// rangoVision
				0);		// costoAlmacenamiento
		
		// fields de UnidadTransporte:
		this.transporte = new AtributosTransporte(8, 1); //capac.Almac., rangoEntrada
	}
	
	@Override
	public ITransporte getTransporte(Unidad portador) {
		return new Transporte(this.transporte, portador);
	}
}
