package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.AtributosTransporte;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.ITransporte;
import fiuba.algo3.componentes.Transporte;

public class AtributosNaveDeTransporte extends AtributosUnidad {
	
	private AtributosTransporte transporte;
	
	public AtributosNaveDeTransporte() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 7);
		this.vida = new AtributosVida(150);
		this.nombre = "Nave de transporte";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				4,	//movPorTuno
				8);	//rangoVision
		this.costoAlmacenamiento = 0; //tecnicamente no es almacenable
		
		// fields de UnidadTransporte:
		this.transporte = new AtributosTransporte(8); //capac.Almac.
	}
	
	public ITransporte getTransporte() {
		return new Transporte(this.transporte);
	}
}
