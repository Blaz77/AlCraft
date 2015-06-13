package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;

public class AtributosNaveDeTransporte extends AtributosUnidadTransporte {
	
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
		this.capacidadAlmacenamiento = 8;
	}

}
