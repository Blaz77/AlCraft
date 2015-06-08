package fiuba.algo3.atributos;

public class AtributosScout extends AtributosUnidadAtaque {
	
	public AtributosScout(){
		// fields ObjetoVivo:
		this.costoMineral = 300;
		this.costoGasVespeno = 150;
		this.turnosConstruccion = 9;
		this.vidaMaxima = 150;
		// this.escudoMaximo = 100; ?
		this.nombre = "Scout";
		
		// fields de Unidad:
		this.costoPoblacion = 3;
		//this.movPorTuno = 1; //?Inventado?!!!
		//this.rangoVision = 7;
	}
}
