package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class AtributosGolliat extends AtributosUnidad {
	
	private AtributosAtaque ataque;

	public AtributosGolliat() {
		// fields ObjetoVivo:
		this.tipo = Tipo.GOLLIAT;
		
		this.costo = new AtributosCosto(
				100,	// costoMineral
				50,		// costoGasVespeno
				6,		// turnosConstruccion
				2);		// costoPoblacion
		
		this.vida = new AtributosVida(125);
		
		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				1,		// movPorTuno
				8,		// rangoVision
				2);		// costoAlmacenamiento
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				10, //Danio A Aire
				12, //Danio A Tierra
				5,  //Rango a Aire
				6,  //Rango a Tierra
				1); //Ataques por Turno
		
	}
	
	@Override
	public IAtaque getAtaque(Unidad portador){
		return new Ataque(this.ataque, portador);
	}
	
}
