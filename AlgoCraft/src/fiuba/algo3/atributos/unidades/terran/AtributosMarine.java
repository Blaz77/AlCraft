package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;

public class AtributosMarine extends AtributosUnidad {

	private AtributosAtaque ataque;
	
	public AtributosMarine(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(50, 0, 3);
		this.vida = new AtributosVida(40);
		this.nombre = "Marine";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				1,	//costoPoblacion
				1,	//movPorTuno
				7);	//rangoVision
		this.costoAlmacenamiento = 1;
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				6, //Danio A Aire
				6,  //Danio A Tierra
				4,  //Rango a Aire
				4,  //Rango a Tierra
				1); //Ataques por Turno
	}
	
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return true;
	}

}
