package fiuba.algo3.atributos.edificios.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosIncrementadorPoblacion;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.EstadoSuministrandoPoblacion;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.recurso.TipoObjetoVivo;

public class AtributosPilon extends AtributosEdificio {
	
	private AtributosIncrementadorPoblacion incrPoblacion;
	
	public AtributosPilon() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(100, 0, 5);
		this.vida = new AtributosVida(300, 300);
		this.nombre = "Pilon";
		this.especie = TipoObjetoVivo.PILON;

		// fields EdificioIncrementadorPoblacion
		this.incrPoblacion = new AtributosIncrementadorPoblacion(5);
	}
	

	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList(new EstadoRegenerandoEscudo(),
				new EstadoSuministrandoPoblacion(this.incrPoblacion));
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	
}
