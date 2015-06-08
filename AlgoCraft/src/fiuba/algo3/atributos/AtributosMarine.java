package fiuba.algo3.atributos;

public class AtributosMarine extends AtributosUnidadAtaque {

	public AtributosMarine(){
		//fields ObjetoVivo:
		this.costoMineral = 50;
		this.costoGasVespeno = 0;
		this.turnosConstruccion = 3;
		this.vidaMaxima = 40;
		this.nombre = "Marine";
		
		//fields de Unidad:
		this.costoPoblacion = 1;
		this.movPorTuno = 1; //?Inventado?!!!
		this.rangoVision = 7;
		
		//fields de UnidadAtaque:
		this.ataquesPorTurno = 1;//?Inventado?!!!
		this.danioAAire = 6; 
		this.danioATierra = 6;
		this.rangoAire = 4;
		this.rangoTierra = 4;
	}
	

}
