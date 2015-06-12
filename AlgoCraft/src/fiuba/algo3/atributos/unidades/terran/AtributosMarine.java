package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;

public class AtributosMarine extends AtributosUnidadAtaque {

	public AtributosMarine(){
		//fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 3);
		this.vida = new AtributosVida(40);
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
