package fiuba.algo3.atributos.unidades.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosMovimiento;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.VidaConEscudo;
import fiuba.algo3.ocupantes.Tipo;
import fiuba.algo3.ocupantes.unidades.Unidad;

public class AtributosScout extends AtributosUnidad {
	
	private AtributosAtaque ataque;
	
	public AtributosScout() {
		// fields ObjetoVivo:
		this.tipo = Tipo.SCOUT;
		
		this.costo = new AtributosCosto(
				300,	// costoMineral
				150,	// costoGasVespeno
				9,		// turnosConstruccion
				3);		// costoPoblacion
		
		this.vida = new AtributosVida(
				150,	// vidaMaxima
				100);	// escudoMaximo

		// fields de Unidad:
		this.movimiento = new AtributosMovimiento(
				3,		// movPorTuno
				7,		// rangoVision
				0);		// costoAlmacenamiento
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				14,		// Danio A Aire
				8,		// Danio A Tierra
				4,		// Rango a Aire
				4,		// Rango a Tierra
				1);		// Ataques por Turno
	}
	
	@Override
	public List<Estado> getEstadosIniciales(){
		return Arrays.asList((Estado)new EstadoRegenerandoEscudo());
	}
	
	@Override
	public IVida getVida() {
		return new VidaConEscudo(this.vida);
	}
	
	@Override
	public IAtaque getAtaque(Unidad portador){
		return new Ataque(this.ataque, portador);
	}
	
	@Override
	public boolean puedeOcuparEspacio() {
		return true;
	}
	
}
