package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;

public class AtributosNaveDeTransporte extends AtributosUnidadTransporte {
	
	public AtributosNaveDeTransporte() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 7);
		this.nombre = "Nave de transporte";
		
		// fields de Unidad:
		this.costoPoblacion = 2;
		this.costoAlmacenamiento = 0; //tecnicamente no es almacenable
		this.movPorTuno = 4; //?
		this.rangoVision = 8; 
		
		// fields de UnidadTransporte:
		this.capacidadAlmacenamiento = 8;
	}

}
