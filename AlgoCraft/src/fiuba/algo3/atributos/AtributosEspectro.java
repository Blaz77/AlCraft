package fiuba.algo3.atributos;

public class AtributosEspectro extends AtributosUnidadAtaque {
	
	public AtributosEspectro(){
		//fields ObjetoVivo:
		this.costoMineral = 150;
		this.costoGasVespeno = 100;
		this.turnosConstruccion = 8;
		this.vidaMaxima = 120;
		this.nombre = "Espectro";
		
		//fields de Unidad:
		this.costoPoblacion = 2;
		//this.movPorTuno = 1; //?Inventado?!!!
		//this.rangoVision = 7;
	}
}
