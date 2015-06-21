package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.ocupantes.Tipo;

public class AtributosMarine extends AtributosUnidad {

	private AtributosAtaque ataque;
	
	public AtributosMarine(){
		// fields ObjetoVivo:
		this.tipo = Tipo.MARINE;
		
		this.costo = new AtributosCosto(
				50,		// costoMineral
				0,		// costoGasVespeno
				3,		// turnosConstruccion
				1);		// costoPoblacion
		
		this.vida = new AtributosVida(40);
		
		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				1,		// movPorTuno
				7,		// rangoVision
				1);		// costoAlmacenamiento
		
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
	
}
