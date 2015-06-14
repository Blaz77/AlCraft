package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;

public class AtributosGolliat extends AtributosUnidad {
	
	private AtributosAtaque ataque;

	public AtributosGolliat() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 50, 6);
		this.vida = new AtributosVida(125);
		this.nombre = "Golliat";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				1,	//movPorTuno
				8);	//rangoVision
		this.costoAlmacenamiento = 2;
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				10, //Danio A Aire
				12,  //Danio A Tierra
				5,  //Rango a Aire
				6,  //Rango a Tierra
				1); //Ataques por Turno
		
	}
	
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
	}
	
	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return true;
	}
	
}
