package fiuba.algo3.atributos.unidades.protoss;

import java.util.Arrays;
import java.util.List;

import fiuba.algo3.atributos.AtributosAtaque;
import fiuba.algo3.atributos.AtributosCosto;
import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.atributos.AtributosVoluntadDelSer;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.componentes.Ataque;
import fiuba.algo3.componentes.Estado;
import fiuba.algo3.componentes.EstadoRegenerandoEscudo;
import fiuba.algo3.componentes.IAtaque;
import fiuba.algo3.componentes.IVida;
import fiuba.algo3.componentes.Vida;
import fiuba.algo3.componentes.VidaConEscudo;

public class AtributosZealot extends AtributosUnidad {

	private AtributosAtaque ataque;
	
	public AtributosZealot(){
		// fields ObjetoVivo:
		this.costo = new AtributosCosto(
				100,	// costoMineral
				0,		// costoGasVespeno
				4);		// turnosConstruccion
		this.vida = new AtributosVida(
				100,	// vidaMaxima
				60);	// escudoMaximo
		this.nombre = "Zealot";
		
		// fields de Unidad:
		this.voluntadDelSer = new AtributosVoluntadDelSer(
				2,		// costoPoblacion
				1,		// movPorTuno
				7);		// rangoVision
		
		// fields de UnidadAtaque:
		this.ataque = new AtributosAtaque(
				0,		// Danio A Aire
				8,		// Danio A Tierra
				1,		// Rango a Aire
				1,		// Rango a Tierra
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
	public IAtaque getAtaque(){
		return new Ataque(this.ataque);
	}
	
	

}
