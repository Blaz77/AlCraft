package fiuba.algo3.atributos;

public class AtributosMarine extends AtributosUnidadAtaque {

	public AtributosMarine(){
		//fields de ObjetoVivo:
		this.vidaMaxima = 40;
		this.nombre = "Marine";
		
		//fields de Unidad:
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
