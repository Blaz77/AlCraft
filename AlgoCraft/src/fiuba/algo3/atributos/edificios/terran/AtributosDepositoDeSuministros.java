package fiuba.algo3.atributos.edificios.terran;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;
import fiuba.algo3.ocupantes.recurso.TipoObjetoVivo;

public class AtributosDepositoDeSuministros extends AtributosEdificio {
	
	private AtributosIncrementadorPoblacion incrPoblacion;
	
	public AtributosDepositoDeSuministros() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 6);
		this.vida = new AtributosVida(500);
		this.nombre = "Deposito De Suministros";
		this.especie = TipoObjetoVivo.DEPOSITO_SUMINISTRO;

		// fields EdificioIncrementadorPoblacion
		this.incrPoblacion = new AtributosIncrementadorPoblacion(5);
	}

	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoSuministrandoPoblacion(this.incrPoblacion));
	}
}
