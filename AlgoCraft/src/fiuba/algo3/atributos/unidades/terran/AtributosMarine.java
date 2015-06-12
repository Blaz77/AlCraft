package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
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
		this.ataque = new AtributosAtaque(
				6, //Danio A Aire
				6,  //Danio A Tierra
				4,  //Rango a Aire
				4,  //Rango a Tierra
				1); //Ataques por Turno
	}
	

}
