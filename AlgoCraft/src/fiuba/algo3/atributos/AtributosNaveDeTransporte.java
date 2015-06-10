package fiuba.algo3.atributos;

public class AtributosNaveDeTransporte extends AtributosUnidadTransporte {
	
	public AtributosNaveDeTransporte() {
		// fields ObjetoVivo:
		this.costoGasVespeno = 100;
		this.costoMineral = 150;
		this.turnosConstruccion = 7;
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
