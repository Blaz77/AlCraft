package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;

public class AtributosEspectro extends AtributosUnidadAtaque {
	
	public AtributosEspectro(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 100, 8);
		this.vida = new AtributosVida(120);
		this.nombre = "Espectro";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,	//costoPoblacion
				1,	//movPorTuno
				7);	//rangoVision
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				20, //Danio A Aire
				8,  //Danio A Tierra
				5,  //Rango a Aire
				5,  //Rango a Tierra
				1); //Ataques por Turno
	}
	
	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
	
	@Override
	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return danioAire;
	}
}
