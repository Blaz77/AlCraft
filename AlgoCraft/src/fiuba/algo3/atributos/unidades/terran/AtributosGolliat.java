package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;

public class AtributosGolliat extends AtributosUnidadAtaque {

	public AtributosGolliat() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 50, 6);
		this.vida = new AtributosVida(125);
		this.nombre = "Golliat";
		
		// fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		this.rangoVision = 7;
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				10, //Danio A Aire
				12,  //Danio A Tierra
				5,  //Rango a Aire
				6,  //Rango a Tierra
				1); //Ataques por Turno
		
	}
	
}
