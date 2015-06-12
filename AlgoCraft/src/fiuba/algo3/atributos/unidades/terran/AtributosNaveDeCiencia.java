package fiuba.algo3.atributos.unidades.terran;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.magia.MagiaEMP;
import fiuba.algo3.magia.MagiaRadiacion;

public class AtributosNaveDeCiencia extends AtributosUnidadMagica {
	
	public AtributosNaveDeCiencia() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 225, 10);
		this.nombre = "Nave de ciencia";

		// fields de Unidad:
		this.costoPoblacion = 2;
		this.movPorTuno = 1; //?Inventado?!!!
		
		// fields de UnidadMagica
		this.energiaMax = 200;
		this.energiaARegenerarPorTurno = 10;
		this.magiaAoE = new MagiaEMP();
		this.magiaUnidad = new MagiaRadiacion();
	}

	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
}
