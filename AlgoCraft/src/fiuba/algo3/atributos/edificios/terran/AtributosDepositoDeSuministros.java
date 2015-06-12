package fiuba.algo3.atributos.edificios.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;

public class AtributosDepositoDeSuministros extends AtributosEdificio {
	
	private AtributosIncrementadorPoblacion incrPoblacion;
	
	public AtributosDepositoDeSuministros() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(500);
		this.nombre = "Deposito De Suministros";

		// fields EdificioIncrementadorPoblacion
		this.incrPoblacion = new AtributosIncrementadorPoblacion(5);
		
		estadosIniciales.add(new EstadoSuministrandoPoblacion(this.incrPoblacion));
	}

}
