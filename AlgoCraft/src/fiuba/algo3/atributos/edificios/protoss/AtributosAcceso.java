package fiuba.algo3.atributos.edificios.protoss;

import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaLivianaTerrestre;
import fiuba.algo3.ocupantes.unidades.constructores.ConstructorInfanteriaPesadaTerrestre;

public class AtributosAcceso extends AtributosEdificioEntrenadorUnidades {

	public AtributosAcceso() {
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(150, 0, 8);
		this.vida = new AtributosVida(500, 500);
		this.nombre = "Acceso";
		
		// fields EdificioEntrenadorUnidades:
		this.unidadesEntrenables.add(new ConstructorInfanteriaLivianaTerrestre());
		this.unidadesEntrenables.add(new ConstructorInfanteriaPesadaTerrestre());
		
		estadosIniciales.add(new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	
	@Override
	public boolean tieneEscudo() {
		return true;
	}
}
