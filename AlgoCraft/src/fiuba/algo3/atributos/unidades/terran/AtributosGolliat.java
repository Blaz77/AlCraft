package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;

public class AtributosGolliat extends AtributosUnidadAtaque {

	public AtributosGolliat() {
		// fields ObjetoVivo:
		this.costoGasVespeno = 50;
		this.costoMineral = 100;
		this.turnosConstruccion = 6;
		this.vidaMaxima = 125;
		this.nombre = "Golliat";
		
		// fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		this.rangoVision = 7;
		
		// fields de UnidadAtaque:
		this.ataquesPorTurno = 1;//?Inventado?!!!
		this.danioAAire = 10; 
		this.danioATierra = 12;
		this.rangoAire = 5;
		this.rangoTierra = 6;
		
	}
	
}
